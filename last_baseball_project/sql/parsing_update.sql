--해당 테이블은 아무것도 연결되지 않은 별도의 테이블.
--ERD업데이트를 꼭 하자.
--용도는 파싱을 기본적으로 하는데, 그 간격을 1시간 간격으로 하겠다는 취지 
--해당 데이터에는 int형으로 현재 밀리세컨드가 들어가며,
--1시간 마다 누군가 들어왔을 시 조회해서 만약 1시간 간격이상이 난다면 파싱을 한다는 것


create table parsing_update(
parsing_key varchar(10),
parsing_second bigint unsigned,
constraint pk_parsing_update primary key(parsing_key)
);

drop table parsing_update;


insert into parsing_update(parsing_key,parsing_second) 
					values("second",0);
					
					
					
select * from parsing_update;