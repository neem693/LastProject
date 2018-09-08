create table normal_contents
(
	nc_idx int unsigned auto_increment,
	nc_title varchar(60),
	nc_contents varchar(1000),
	nc_regdate varchar(30),
	nc_views int,
	nc_like int,
	nc_bad int,
	nc_likeno int,
	nc_badno int,
	m_idx int unsigned,
	nc_ip varchar(30),
	constraint pk_normal_contents_idx primary key(nc_idx),
	constraint fk_normal_m_idx foreign key(m_idx)
	references member(m_idx) on update cascade 
)