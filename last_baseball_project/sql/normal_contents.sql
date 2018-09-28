create table normal
(
	nc_idx int unsigned auto_increment,
	nc_title varchar(60),
	nc_contents varchar(1000),
	nc_regdate datetime,
	nc_views int,
	m_idx int unsigned,
	nc_ip varchar(30),
	constraint pk_normal_contents_idx primary key(nc_idx),
	constraint fk_normal_m_idx foreign key(m_idx)
	references member(m_idx) on update cascade
)

alter table normal drop foreign key m_idx;


drop table normal;

select * from normal

select * from member;

insert into normal(nc_title,nc_contents,nc_regdate,nc_views,nc_likeno,nc_badno,m_idx,nc_ip)
values(
'우리집이다',
'여기는 우리집이다.',
now(),
0,
0,
0,
1,
'192.168.0.5'
)


select n.*, m.* from
normal n inner join member m
on n.m_idx = m.m_idx

select n.*,m.m_id,m.m_nick 
from normal n inner join member m
on n.m_idx  = m.m_idx;

commit;



select n.*,m.m_nick,c.*
from  (normal n inner join member m on n.m_idx = m.m_idx)
       inner join comment c on n.nc_idx = c.nc_idx
where nc_idx=1

select n.*,m.m_nick
		from normal n inner join member m
		on n.m_idx = m.m_idx
		where nc_idx=1
