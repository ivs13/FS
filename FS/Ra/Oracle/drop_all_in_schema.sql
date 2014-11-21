SELECT 'PROMPT '||ROWNUM||'. '||scrpt||CHR(13)||CHR(10)||scrpt scrpt
FROM
( 
  SELECT 'DROP '||Object_Type||' '||Object_Name||
    CASE WHEN Object_Type = 'TABLE' THEN ' CASCADE CONSTRAINTS PURGE' END ||
    CASE WHEN Object_Type = 'TYPE' THEN ' FORCE' END || 
    ';' scrpt 
  FROM user_objects
  WHERE NOT (Object_Type = 'TYPE BODY')
    AND NOT (Object_Type = 'PACKAGE BODY')
    AND NOT (Object_Type = 'LOB')
    AND NOT (Object_Type = 'INDEX')
    AND NOT (Object_Type = 'TRIGGER')
  ORDER BY 
    CASE 
      when Object_Type = 'PROCEDURE' then '01' 
      when Object_Type = 'FUNCTION' then '02' 
      when Object_Type = 'PACKAGE' then '03' 
      when Object_Type = 'TYPE' then '04' 
      when Object_Type = 'SEQUENCE' then '05'
      when Object_Type = 'SYNONYM' then '06' 
      when Object_Type = 'VIEW' then '07' 
      when Object_Type = 'MATERIALIZED VIEW' then '08' 
      when Object_Type = 'TABLE' then '09' 
      else Object_Type
    END
)