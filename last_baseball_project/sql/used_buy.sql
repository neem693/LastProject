create table used_buy
(
	u_idx int unsigned auto_increment,
	u_name varchar(60) not null,
	u_tel varchar(60) not null,
	u_simage varchar(60) not null,
	u_contents varchar(1000) not null,
	u_pay varchar(60) not null,
	u_ip varchar(60) not null,
	m_idx int unsigned,
	constraint pk_used_buy_idx primary key(u_idx),
	constraint fk_used_buy_m_idx foreign key(m_idx)
	references member(m_idx) on update cascade 
)