

create table play(

p_idx varchar(30),
p_date datetime,
p_score varchar(8),
p_rts varchar(10),
s_idx int unsigned,
t_home varchar(30) not null,
t_away varchar(30) not null,
constraint pk_idx_play primary key(p_idx),
constraint fk_s_idx_play foreign key(s_idx)
references studium(s_idx) on update cascade,
constraint fk_t_home_play foreign key(t_home)
references team(t_name) on update cascade,
constraint fk_t_home_away foreign key(t_away)
references team(t_name) on update cascade
)

drop table play;
select * from play;

