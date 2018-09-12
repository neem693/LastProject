create table comment
(
	c_idx int unsigned auto_increment,
	c_comment varchar(1000),
	c_regdate varchar(30),
	u_idx int unsigned,
	nc_idx int unsigned,
	constraint pk_comment_idx primary key(c_idx),
	constraint fk_comment_u_idx foreign key(u_idx)
	references used_buy(u_idx) on update cascade,
	constraint fk_comment_nc_idx foreign key(nc_idx)
	references normal_contents(nc_idx) on update cascade
)