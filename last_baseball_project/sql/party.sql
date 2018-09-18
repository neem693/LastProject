create table party(
pt_idx int unsigned auto_increment,
pt_people int(3) unsigned ,
pt_day datetime,
pt_condition varchar(60) ,
pt_purpose varchar(60),
p_idx  varchar(30),
pt_maxPeople int(3) unsigned,
pt_location varchar(250),
pt_text mediumtext,
pt_date datetime,
t_name varchar(30),
constraint pk_party_pt_idx primary key(pt_idx),
constraint fk_p_idx_in_party foreign key(p_idx)
references play(p_idx) on delete cascade,
constraint fk_t_name_in_party foreign key(t_name)
references team(t_name) on delete cascade
)


drop table party



select * from party



select * 
from party pt right outer join play p
on pt.p_idx = p.p_idx
where p.p_date like "2018-09%"





select  date(p_date) as p_date
from play
group by p_date

select * from party;


select day(p.p_date), count(*) as match_count
from play p right outer join party pt
on p.p_idx =pt.p_idx
where p.p_idx like "201809%" and
pt.t_name = "³Ø¼¾"
group by day(p.p_date)


select concat(t_home,t_away,s_idx)
from play








