CREATE TABLE studium
(
	s_idx int unsigned auto_increment,
	s_name varchar(150) not null,
	s_addr varchar(150) not null,
	s_open varchar(60) not null,
	s_seat varchar(60) not null,
	CONSTRAINT pk_studium_idx PRIMARY KEY(s_idx)
)

select * from studium
drop table studium

insert into studium(s_name,s_addr,s_open,s_seat) values(
							'한화생명이글스파크',
							'대전 중구 대종로 373',
							'1964년',
							'13042석'
							);

insert into studium(s_name,s_addr,s_open,s_seat) values(
							'청주종합운동장 야구장',
							'충청북도 청주시 서원구 사직대로 229',
							'1979년',
							'10500석'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'대구삼성라이온즈파크',
							'대구광역시수성구 야구전설로 1 대구삼성라이온즈파크',
							'2016년',
							'24000석'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'포항야구장',
							'경상북도 포항시 남구 대도동',
							'2012년',
							'10747석(외야 잔디석은 제외)'
							);

insert into studium(s_name,s_addr,s_open,s_seat) values(
							'사직야구장',
							'부산광역시 동래구 사직로 45',
							'1985년',
							'25000석'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'울산문수야구장',
							'울산광역시 남구 문수로 44',
							'2014년',
							'8100석(외야 잔디석 제외)'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'광주 기아 챔피언스필드',
							'광주 광역시 북구 서림로 10 무등종합경기장',
							'2014년',
							'20500석'
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'마산종합운동장 야구장',
							'경상남도 창원시 마산회원구 삼호로 63',
							'1982년',
							'11000석'
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'잠실야구장',
							'서울특별시 송파구 올림픽로 25',
							'1982년',
							'25553석'
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'인천 SK행복드림구장',
							'인천광역시 미추홀구 매소홀로 618',
							'2002년',
							'25000석'
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'고척스카이돔',
							'서울특별시 구로구 경인로 430',
							'2015년',
							'17000석'
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'수원 KT위즈 파크',
							'경기도 수원시 장안구 경수대로839 수원종합운동장 야구장',
							'2015년',
							'20800석'
							);								
							
							
							