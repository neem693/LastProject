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


select * from occupy

insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','ÇÑÈ­','1');
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','ÇÑÈ­','2');

insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','»ï¼º','3');
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','»ï¼º','4');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','·Ôµ¥','5');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','·Ôµ¥','6');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','KIA','7');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','NC','8');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','µÎ»ê','9');
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','LG','9');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','SK','10');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','³Ø¼¾','11');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','KT','12');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'0',NULL,'13');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','KIA','14');		
