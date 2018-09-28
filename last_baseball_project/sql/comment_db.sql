create table comment
(
	c_idx int unsigned auto_increment,
	c_comment varchar(1000),
	c_regdate datetime,
	c_ref int,
	c_step int,
	c_depth int,
	m_idx int unsigned,
	j_idx int unsigned,
	nc_idx int unsigned,
	constraint pk_comment_idx primary key(c_idx),
	constraint fk_comment_m_idx foreign key(m_idx)
	references member(m_idx) on update cascade,
	constraint fk_comment_j_idx foreign key(j_idx)
	references joonggo(j_idx) on update cascade,
	constraint fk_comment_nc_idx foreign key(nc_idx)
	references normal(nc_idx) on update cascade
)

select * from comment

drop table comment

alter table comment add c_ref int;
alter table comment add c_step int;
alter table comment add c_depth int;

