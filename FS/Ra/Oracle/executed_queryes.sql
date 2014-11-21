
SELECT * FROM v$sqlarea WHERE UPPER(sql_text) LIKE UPPER('%update%cptymnem%');

SELECT * FROM v$sql_bind_capture WHERE sql_id='71fjnh5bmu660';
