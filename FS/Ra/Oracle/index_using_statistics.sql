select * 
from v$segment_statistics 
where object_name :index_name
  and statistic_name = 'logical reads'
order by object_name, statistic_name;
