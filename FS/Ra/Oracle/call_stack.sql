DBMS_UTILITY.FORMAT_CALL_STACK();


----- PL/SQL Call Stack -----
  object      line  object
  handle    number  name
0x1b1b0ea8         7  xxx.trigger_name
0x19e822f0     12731  package body xxx.yyy
0xe524fe98         1  anonymous block


SELECT * FROM V$SQL WHERE child_address = '00000000E524FE98';
