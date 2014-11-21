# create a named pipe:
mknod exp.pipe p
# read the pipe - output to zip file in the background:
gzip < exp.pipe > from_schema.dmp.gz &
# feed the pipe:
exp from_schema/from_schema_pwd@from_schema_db owner=from_schema file=exp.pipe log=from_schema.dmp.exp.log statistics=none feedback=10000
#remove the pipe:
rm exp.pipe


# create a  name pipe
mknod imp.pipe p
# read the zip file and output to pipe
gunzip < from_schema.dmp.gz > imp.pipe &
# feed the pipe
imp to_schema/to_schema_pwd@to_schema_pwd fromuser=from_schema touser=to_schema file=imp.pipe log=from_schema.dmp.imp.log ignore=y feedback=10000
#remove the pipe and the file:
rm imp.pipe
rm from_schema.dmp.gz
