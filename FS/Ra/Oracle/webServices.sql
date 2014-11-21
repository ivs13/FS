declare
  service_           sys.utl_dbws.SERVICE;
  call_              sys.utl_dbws.CALL;
  service_qname      sys.utl_dbws.QNAME;
  port_qname         sys.utl_dbws.QNAME;
  xoperation_qname   sys.utl_dbws.QNAME;
  xstring_type_qname sys.utl_dbws.QNAME;
  response           sys.xmltype;
  request            sys.xmltype;
begin
  service_qname := sys.utl_dbws.to_qname(null, 'getJoke');
  service_ := sys.utl_dbws.create_service(service_qname);
  call_ := sys.utl_dbws.create_call(service_);
  sys.utl_dbws.set_target_endpoint_address(call_, 'http://interpressfact.net/webservices/getjoke.asmx');
  sys.utl_dbws.set_property( call_, 'SOAPACTION_USE', 'TRUE');
  sys.utl_dbws.set_property( call_, 'SOAPACTION_URI', 'http://interpressfact.net/webservices/getJoke');
  sys.utl_dbws.set_property( call_, 'OPERATION_STYLE', 'document');
  request := sys.xmltype(
    '<getJoke xmlns="http://interpressfact.net/webservices/">'||
    '<Category>Excuses-10</Category>'||
    '</getJoke>'
    );
  response :=sys. utl_dbws.invoke(call_, request);
  dbms_output.put_line( 
    response.extract('//getJokeResult/child::text()', 'xmlns="http://interpressfact.net/webservices/"').getstringval()
    ); 
end;
/
