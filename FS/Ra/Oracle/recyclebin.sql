desc recyclebin

SELECT SUBSTR(object_name,1,50),object_type,owner
FROM dba_objects
WHERE object_name LIKE '%RECYCLEBIN%';



SELECT object_name,original_name,operation,type,dropscn,droptime FROM user_recyclebin;
SELECT owner,original_name,operation,type FROM dba_recyclebin;



PURGE TABLE t1;
PURGE INDEX ind1;
PURGE recyclebin;  -- (Purge all objects in Recyclebin)
PURGE dba_recyclebin;  -- (Purge all objects / only SYSDBA can)
PURGE TABLESPACE users;  -- (Purge all objects of the tablespace)
PURGE TABLESPACE users USER bh;  -- (Purge all objects of the tablspace belonging to BH)


DROP TABLE t1 PURGE;


ALTER SYSTEM SET "_recyclebin"=FALSE SCOPE = BOTH;