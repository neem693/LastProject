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
							'��ȭ�����̱۽���ũ',
							'���� �߱� ������ 373',
							'1964��',
							'13042��'
							);

insert into studium(s_name,s_addr,s_open,s_seat) values(
							'û�����տ�� �߱���',
							'��û�ϵ� û�ֽ� ������ ������� 229',
							'1979��',
							'10500��'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'�뱸�Ｚ���̿�����ũ',
							'�뱸�����ü����� �߱������� 1 �뱸�Ｚ���̿�����ũ',
							'2016��',
							'24000��'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'���׾߱���',
							'���ϵ� ���׽� ���� �뵵��',
							'2012��',
							'10747��(�ܾ� �ܵ��� ����)'
							);

insert into studium(s_name,s_addr,s_open,s_seat) values(
							'�����߱���',
							'�λ걤���� ������ ������ 45',
							'1985��',
							'25000��'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'��깮���߱���',
							'��걤���� ���� ������ 44',
							'2014��',
							'8100��(�ܾ� �ܵ� ����)'
							);
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'���� ��� è�Ǿ��ʵ�',
							'���� ������ �ϱ� ������ 10 �������հ����',
							'2014��',
							'20500��'
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'�������տ�� �߱���',
							'��󳲵� â���� ����ȸ���� ��ȣ�� 63',
							'1982��',
							'11000��'
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'��Ǿ߱���',
							'����Ư���� ���ı� �ø��ȷ� 25',
							'1982��',
							'25553��'
							);							
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'��õ SK�ູ�帲����',
							'��õ������ ����Ȧ�� �ż�Ȧ�� 618',
							'2002��',
							'25000��'
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'��ô��ī�̵�',
							'����Ư���� ���α� ���η� 430',
							'2015��',
							'17000��'
							);								
							
insert into studium(s_name,s_addr,s_open,s_seat) values(
							'���� KT���� ��ũ',
							'��⵵ ������ ��ȱ� ������839 �������տ�� �߱���',
							'2015��',
							'20800��'
							);								
							
							
							