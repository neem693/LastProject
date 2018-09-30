

create table play(

p_idx varchar(30),
p_date datetime,
p_score varchar(8),
p_rts varchar(10),
s_idx int unsigned,
t_home varchar(30) not null, //이건 널로 바꿔야 할듯 하다.
t_away varchar(30) not null, //이것도 널로 바꿔야 할듯 하다.
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




--7월에 올스타가 있으므로, 다시 넣기 위해서 7월달 내용은 다 지움
delete from play
where p_idx like '201807%';



--여기서부터 저장할 sql이 아닌 필요한 sql에 대한 정리
select p.*,s.s_name 
from play p inner join studium s
on p.s_idx = s.s_idx
where p.p_idx like '201809%';



 
select *
from play
where p_score = "0-0"



insert into play(p_idx,
p_date,
p_score,
p_rts,
s_idx,
t_home, 
t_away)

values(
'20181010_KT롯데_(DH)',
'2018-10-10 18:00:00',
'예정',
'-',
5,
'KT',
'롯데'
)


