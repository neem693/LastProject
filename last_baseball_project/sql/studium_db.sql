CREATE TABLE studium
(
	s_idx int unsigned auto_increment,
	s_name varchar(30) not null,
	s_addr varchar(60) not null,
	s_open int(4) not null,
	s_seat int not null,
	CONSTRAINT pk_studium_idx PRIMARY KEY(s_idx)
)

select * from studium
drop table studium


select s.*,o.o_secondQ
	from studium s left outer join occupy o
	on s.s_idx = o.s_idx;
	
	select s.*,o.o_secondQ from studium s right outer join occupy o on s.s_idx = o.s_idx where s.s_idx = 9




insert into studium(s_name,s_addr,s_open,s_seat) values(
							'대전한화생명이글스파크',
							'대전 중구 대종로 373',
							1964,
							13042
							);

insert into studium(s_name,s_addr,s_open,s_seat) values(
							'청주종합운동장 야구장',
							'충청북도 청주시 서원구 사직대로 229',
							1979,
							10500
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'대구삼성라이온즈파크',
							'대구광역시수성구 야구전설로 1 대구삼성라이온즈파크',
							2016,
							24000
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'포항야구장',
							'경상북도 포항시 남구 대도동',
							2012,
							10747
							);

insert into studium(s_name,s_addr,s_open,s_seat) values(
							'사직야구장',
							'부산광역시 동래구 사직로 45',
							1985,
							25000
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'울산문수야구장',
							'울산광역시 남구 문수로 44',
							2014,
							8100
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'광주기아챔피언스필드',
							'광주 광역시 북구 서림로 10 무등종합경기장',
							2014,
							20500
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'마산종합운동장야구장',
							'경상남도 창원시 마산회원구 삼호로 63',
							1982,
							11000
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'잠실야구장',
							'서울특별시 송파구 올림픽로 25',
							1982,
							25553
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'문학인천SK행복드림구장',
							'인천광역시 미추홀구 매소홀로 618',
							2002,
							25000
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'고척스카이돔',
							'서울특별시 구로구 경인로 430',
							2015,
							17000
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'수원KT위즈파크',
							'경기도 수원시 장안구 경수대로839 수원종합운동장 야구장',
							2015,
							20800
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'창원마산야구장',
							'경상남도 창원시 마산회원구 삼호로 63 (양덕2동 477번지)',
							2019,
							22011
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'군산월명종합운동장',
							'전라북도 군산시 번영로 281',
							1989,
						11000
							);	