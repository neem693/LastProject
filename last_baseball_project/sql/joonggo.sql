`bb`CREATE TABLE joonggo
(
	j_idx INT UNSIGNED AUTO_INCREMENT, -- 일련번호
	j_category VARCHAR(100) NOT NULL,    -- 카테고리(글러브/방망이/기타)
	j_title VARCHAR(100) NOT NULL,     -- 제목
	j_content VARCHAR(2000) NOT NULL,  -- 내용
	j_price INT NOT NULL,              -- 가격
	j_ip VARCHAR(100) NOT NULL,        -- IP주소
	j_filename VARCHAR(255) NOT NULL,  -- 대표사진 파일 이름
	j_image VARCHAR(100) NOT NULL,     -- 대표사진 
	j_readhits INT,                    -- 조회수
	j_date DATETIME,                   -- 등록일자
	m_idx INT UNSIGNED,
	CONSTRAINT pk_used_buy_idx PRIMARY KEY(j_idx),
	CONSTRAINT fk_used_buy_m_idx FOREIGN KEY(m_idx)
	REFERENCES member(m_idx) ON UPDATE CASCADE 
)
DROP TABLE joonggo
SELECT *FROM joonggo

INSERT INTO joonggo(j_category, j_title, j_content, j_price, j_ip, j_filename, j_image, j_readhits, j_date) VALUES(
                                                                                                                   '점퍼',
                                                                                                                   '라분 지엔 LoveGame 좋아~~~제목',
                                                                                                                   'LoveGame내용',
                                                                                                                    '5000',
                                                                                                                   '192.120.120.10',
                                                                                                                   '두산',
                                                                                                                   '8.png',
                                                                                                                   '0',
                                                                                                                   NOW()); 