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
					'1','��ȭ','1');
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','��ȭ','2');

insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','�Ｚ','3');
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','�Ｚ','4');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','�Ե�','5');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','�Ե�','6');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','KIA','7');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','NC','8');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','�λ�','9');
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','LG','9');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','SK','10');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','�ؼ�','11');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'1','KT','12');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'0',NULL,'13');					
					
insert into occupy(o_secondQ,t_name,s_idx) values(
					'2','KIA','14');		
