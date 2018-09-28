create table party_book(
b_idx int unsigned auto_increment,
m_idx int unsigned,
b_leader int(2),
pt_idx int unsigned,
constraint pk_party_book_b_idx primary key(b_idx),
constraint fk_m_idx_in_party_book foreign key(m_idx)
references member(m_idx) on delete cascade,
constraint fk_pt_idx_in_party_book foreign key(pt_idx)
references party(pt_idx) on delete cascade
)

select * from party_book



drop table party_book

select *
from member m inner join party_book b
on m.m_idx = b.m_idx



	select b.*, m.m_id,m.m_nick,m.m_tel,m.m_photo,m.m_comment
	from party_book b inner join member m
	on b.m_idx = m.m_idx
	where b.pt_idx = 23 and
	b.b_leader = 10;
	
	
	
	select b.*,pt.*
	from party_book b inner join party pt
	on b.pt_idx = pt.pt_idx
	where pt.p_idx like "20180925%"
	and b.m_idx = 41


