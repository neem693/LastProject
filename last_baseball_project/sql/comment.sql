CREATE TABLE COMMENT
(
c_idx INT UNSIGNED AUTO_INCREMENT,  -- 댓글일련번호
m_nick VARCHAR(100) NOT NULL,
m_id VARCHAR(100) NOT NULL,
c_content VARCHAR(2000) NOT NULL,   -- 내용      
c_ip VARCHAR(100) NOT NULL,         -- 작성자IP
c_date DATETIME,                 -- 작성일시            
j_idx INT,                 
m_idx INT UNSIGNED,     
nc_idx INT UNSIGNED,                  
CONSTRAINT pk_comment_idx PRIMARY KEY(c_idx),
CONSTRAINT fk_m_idx FOREIGN KEY(m_idx)
REFERENCES member(m_idx) ON DELETE CASCADE,
CONSTRAINT fk_nc_idx FOREIGN KEY(nc_idx)
REFERENCES normal(nc_idx) ON DELETE CASCADE
)

CONSTRAINT fk_j_idx FOREIGN KEY(j_idx)
REFERENCES joonggo(j_idx) ON DELETE CASCADE


DROP TABLE COMMENT;