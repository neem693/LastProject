create table party(
pt_idx int unsigned auto_increment,
pt_people int(3) unsigned ,
pt_day datetime,
pt_condition varchar(60) ,
pt_purpose varchar(60),
p_idx  varchar(30),
pt_maxPeople int(3) unsigned,
pt_location varchar(60),
pt_text mediumtext,
pt_date datetime,
constraint pk_party_pt_idx primary key(pt_idx),
constraint fk_p_idx_in_party foreign key(p_idx)
references play(p_idx) on delete cascade
)


drop table party
