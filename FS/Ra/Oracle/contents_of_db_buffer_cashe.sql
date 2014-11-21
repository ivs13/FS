SELECT
  s.owner owner,
  object_name obj_name,
  subobject_name subobj_name,
  substr(object_type,1,10) obj_type,
  t.block_size/1024 block_size_kb,
  s.blocks total_blocks_count,
  b.blocks blocks_in_buff_count,
  ROUND(((b.blocks*t.block_size)/(1024 * 1024)),2) memory_in_buff_mb,
  ROUND((b.blocks/decode(s.blocks, 0, .001, s.blocks))*100,2) buffer_percent
FROM
  (
    select o.owner, o.object_name, o.subobject_name, o.object_type object_type, count(*) blocks
    from dba_objects o, v$bh bh
    where o.object_id = bh.objd and o.owner not in ('SYS','SYSTEM')
    group by o.owner, o.object_name, o.subobject_name, o.object_type
  ) b,
  dba_segments s,
  dba_tablespaces t
WHERE s.tablespace_name = t.tablespace_name
  AND s.owner = b.owner
  AND s.segment_name = b.object_name
  AND s.SEGMENT_TYPE = b.object_type
  AND (s.PARTITION_NAME = b.subobject_name or b.subobject_name is null)
ORDER BY buffer_percent DESC;
