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
constraint pk_party_pt_idx primary key(pt_idx),
constraint fk_p_idx_in_party foreign key(p_idx)
references play(p_idx) on delete cascade
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




select day(p.p_date), count(*)
from play p right outer join party pt
on p.p_idx =pt.p_idx
where date(p.p_date) like "2018-09%" and
p.t_home = "³Ø¼¾" or p.t_away = "³Ø¼¾"
group by day(p.p_date)


select concat(t_home,t_away,s_idx)
from play








