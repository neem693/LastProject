--�ش� ���̺��� �ƹ��͵� ������� ���� ������ ���̺�.
--ERD������Ʈ�� �� ����.
--�뵵�� �Ľ��� �⺻������ �ϴµ�, �� ������ 1�ð� �������� �ϰڴٴ� ���� 
--�ش� �����Ϳ��� int������ ���� �и������尡 ����,
--1�ð� ���� ������ ������ �� ��ȸ�ؼ� ���� 1�ð� �����̻��� ���ٸ� �Ľ��� �Ѵٴ� ��


create table parsing_update(
parsing_key varchar(10),
parsing_second bigint unsigned,
constraint pk_parsing_update primary key(parsing_key)
);

drop table parsing_update;


insert into parsing_update(parsing_key,parsing_second) 
					values("second",0);
					
					
					
select * from parsing_update;