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




