-- Using Oracle Virtual Private Database to Control Data Access.
-- doc: http://docs.oracle.com/cd/B28359_01/network.111/b28531/vpd.htm#DBSEG007


-- if "exempt access policy" is granted then VPD will not work!

-- Create a Policy Function:
CREATE OR REPLACE FUNCTION gen_object_policy(i_schema IN VARCHAR2, i_table IN VARCHAR2) RETURN VARCHAR2
IS
  return_val VARCHAR2 (1000);
BEGIN
  return_val := 'field_name = some_value';
  RETURN return_val;
END gen_object_policy;
/

-- Create the Policy Group:
BEGIN
  DBMS_RLS.CREATE_POLICY_GROUP(
  OBJECT_SCHEMA   => 'schema_name',
  OBJECT_NAME     => 'table_name',
  POLICY_GROUP    => 'policy_group_name');
END;
/

-- Create the Oracle Virtual Private Database Policy:
BEGIN
  DBMS_RLS.ADD_GROUPED_POLICY(
    object_schema    => 'schema_name',
    object_name      => 'table_name',
    policy_group     => 'policy_group_name',
    policy_name      => 'policy_name',
    function_schema  => 'schema_name',
    policy_function  => 'gen_object_policy',
    statement_types  => 'select,insert,update,delete'
   );
END;
/

-- Show exisiting policies:
select * from DBA_POLICIES where object_owner = SYS_CONTEXT('USERENV', 'SESSION_USER');
select * from DBA_POLICY_GROUPS;
select * from DBA_POLICY_CONTEXTS;
select * from DBA_SEC_RELEVANT_COLS;



-- Remove the components:
DROP FUNCTION gen_object_policy;
CALL DBMS_RLS.DROP_POLICY('schema_name','table_name','policy_name');
CALL DBMS_RLS.DELETE_POLICY_GROUP('schema_name','table_name','policy_group_name');

