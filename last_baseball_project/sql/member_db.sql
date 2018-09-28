--멤버테이블 생성
CREATE TABLE member
(
	m_idx int unsigned auto_increment,
	m_id varchar(60) not null,
	m_name varchar(60) not null,
	m_pwd varchar(60) not null,
	m_date varchar(60) not null,
	m_email varchar(60) not null,
	m_photo varchar(60) null,
	m_comment varchar(60) null,
	m_addr varchar(60) not null,
	m_zip_code varchar(60) not null,
	m_tel varchar(60) not null,
	t_name varchar(30) not null,
	m_nick varchar(60) not null,
	m_ip varchar(60) not null,
	constraint pk_member_idx primary key(m_idx),
	constraint fk_team_t_name foreign key(t_name)
	references team(t_name) on update cascade
)

select * from member

selsct * from normal
