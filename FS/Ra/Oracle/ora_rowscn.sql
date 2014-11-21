create table test_scn
(
  id number,
  str varchar2(100),
  constraint test_scn_pk primary key (id)
) rowdependencies;

insert into test_scn(id, str) values (1, '111');
commit;
insert into test_scn(id, str) values (2, '222');
commit;

select ora_rowscn, t.* from test_scn t;

update test_scn set str = '222' where id = 2; 

select ora_rowscn, t.* from test_scn t;

commit;

select ora_rowscn, t.* from test_scn t;

drop table test_scn;
