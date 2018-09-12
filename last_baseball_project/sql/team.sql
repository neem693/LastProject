//



create table team(
t_name varchar(30) not null,
t_rank int(2) unsigned not null,
t_nom int(4) unsigned not null,
t_win int(4) unsigned not null,
t_lose int(4) unsigned not null,
t_draw int(4) unsigned not null,
t_winpo double(5,4) unsigned not null,
t_leading double(4,1) unsigned not null,
t_recent10 varchar(10) not null,
t_contn varchar(5) not null,
t_home varchar(10) not null,
t_away varchar(10) not null
)


alter table team
add constraint pk_t_name primary key(t_name);

--팀 테이블 인서트 예시
insert into team(t_name,t_rank,t_nom,t_win,t_lose,t_draw,t_winpo,t_leading,t_recent10,t_contn,t_home,t_away)
valuse(
'NC',
10,
116,
47,
68,
1,
0.409,
27,
'5승0무5패',
'1패',
'25-0-34',
'22-1-34'
)

select * from team
order by t_rank



--올스타는 여기있다.

insert into team(t_name,t_rank,t_nom,t_win,t_lose,t_draw,t_winpo,t_leading,t_recent10,t_contn,t_home,t_away)
values(
'나눔',
100,
1,
1,
0,
0,
0,
100,
'1승0무0패',
'1승',
'-',
'-'
)


insert into team(t_name,t_rank,t_nom,t_win,t_lose,t_draw,t_winpo,t_leading,t_recent10,t_contn,t_home,t_away)
values(
'드림',
100,
1,
0,
1,
0,
0,
100,
'0승0무1패',
'1패',
'-',
'-'
)

--