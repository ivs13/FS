---------------------------------------------------------------
--  Script recompiles all invalid objects in current schema  --
---------------------------------------------------------------

SET SERVEROUTPUT ON SIZE 1000000

DECLARE
  CURSOR ivalid_objects IS
    SELECT object_name, object_type
    FROM user_objects
    WHERE status <> 'VALID'
    ORDER BY DECODE(object_type, 'JAVA SOURCE',1, 'JAVA CLASS',2, 'PACKAGE',3, 'PACKAGE BODY',4, 'PROCEDURE',5, 'FUNCTION',6, 'TRIGGER',7, 'VIEW',8, 'MATERIALIZED VIEW',9, 'TYPE',10, 'TYPE BODY',11, 999), object_name;

  TYPE UncompiledObj_rowType IS RECORD
  (
    Name VARCHAR2(30), 
    Type VARCHAR2(30),
    ErrMsg VARCHAR2(2000)
  );
  TYPE UncompiledObjs_tableType IS TABLE OF UncompiledObj_rowType;
  UncompiledObjs UncompiledObjs_tableType := UncompiledObjs_tableType();

  ddl_row VARCHAR2(4000);
  row_processed_count NUMBER := 0;
  start_invalid_obj_count NUMBER := NULL;
  prev_invalid_obj_count NUMBER := 0;
BEGIN

  LOOP
    row_processed_count := 0;
    UncompiledObjs.DELETE;
    -- Loops through all invalid objects:
    FOR c IN ivalid_objects LOOP
      -- Building SQL script to recompile object:
      ddl_row := 'ALTER ' ||
        CASE
          WHEN c.object_type IN('PACKAGE', 'PACKAGE BODY') THEN 'PACKAGE'
          WHEN c.object_type IN('JAVA CLASS', 'JAVA SOURCE') THEN 'JAVA CLASS'
          WHEN c.object_type IN('TYPE', 'TYPE BODY') THEN 'TYPE'
          ELSE c.object_type
        END ||
        ' ' || c.object_name || ' ' ||
        CASE
          WHEN c.object_type IN('JAVA CLASS', 'JAVA SOURCE') THEN 'RESOLVE'
          ELSE 'COMPILE'
        END || 
        CASE
          WHEN c.object_type = 'PACKAGE BODY' THEN ' BODY'
          WHEN c.object_type = 'TYPE' THEN ' SPECIFICATION'
          WHEN c.object_type = 'TYPE BODY' THEN ' BODY'
        END;
      -- Trying to execute builded script:
      BEGIN
        EXECUTE IMMEDIATE ddl_row;
        -- Reporting about successful compilation:
        DBMS_OUTPUT.PUT_LINE(c.object_type || ' ' || c.object_name || ' was recompiled.');
      EXCEPTION
        -- Do not pay attention on the mistakes.
        -- Maybe will be compiled in the next iteration of the loop.
        -- If will not be compiled, then will be printed to log.
        WHEN OTHERS THEN 
          UncompiledObjs.EXTEND;
          UncompiledObjs(UncompiledObjs.COUNT).Type := c.object_type;
          UncompiledObjs(UncompiledObjs.COUNT).Name := c.object_name;
          UncompiledObjs(UncompiledObjs.COUNT).ErrMsg := sqlerrm;
      END;
      -- Calculate row processed count for next log printing:
      row_processed_count := row_processed_count + 1;
    END LOOP;

    -- Remember row processed count only on first loop:
    IF start_invalid_obj_count IS NULL THEN
      start_invalid_obj_count := row_processed_count;
    END IF;

    -- Stop trying to compile as soon as the number of invalid objects stops decreasing.
    EXIT WHEN prev_invalid_obj_count = UncompiledObjs.COUNT;
    
    -- Remember invalid object count for comparison during next loop:
    prev_invalid_obj_count := UncompiledObjs.COUNT;
  END LOOP;

  -- Logs about successfully compiled objects:
  DBMS_OUTPUT.PUT_LINE('Has been compiled ' || start_invalid_obj_count || ' objects, remain ' || UncompiledObjs.COUNT || ' invalid objects' || CASE WHEN UncompiledObjs.COUNT = 0 THEN '.' ELSE ':' END);
    
  -- Logs about invalid objects:
  FOR i IN 1..UncompiledObjs.COUNT LOOP
    DBMS_OUTPUT.PUT_LINE(UncompiledObjs(i).Type || ' ' || UncompiledObjs(i).Name || ': ' || UncompiledObjs(i).ErrMsg);
    -- Clarify the compile error:
    FOR e IN 
    (
      SELECT 'Line ' || Line || ', position ' || Position || ': ' || Text err_msg 
      FROM user_errors
      WHERE Name = UncompiledObjs(i).Name
      ORDER BY Sequence ASC
    ) LOOP   
      DBMS_OUTPUT.PUT_LINE('  ' || e.err_msg);      
    END LOOP;
  END LOOP;
  
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE(' Recompilation stopped: ' || sqlerrm);
END;
/
