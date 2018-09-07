create table occupy
(
	o_idx int unsigned auto_increment,
	o_secondQ int(1) not null,
	t_name varchar(30) not null,
	s_idx int unsigned,
	constraint pk_occupy_idx primary key(o_idx),
	constraint fk_occupy_t_name foreign key(t_name)
	references team(t_name) on update cascade,
	constraint fk_occupy_s_idx foreign key(s_idx)
	references studium(s_idx) on update cascade
)