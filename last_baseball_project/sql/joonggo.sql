`bb`CREATE TABLE joonggo
(
	j_idx INT UNSIGNED AUTO_INCREMENT, -- 일련번호
	j_category VARCHAR(100) NOT NULL,    -- 카테고리(글러브/방망이/기타)
	j_title VARCHAR(100) NOT NULL,     -- 제목
	j_content VARCHAR(2000) NOT NULL,  -- 내용
	j_price INT NOT NULL,              -- 가격
	j_ip VARCHAR(100) NOT NULL,        -- IP주소
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

INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '글러브',
                                                                                                                   '선미',
                                                                                                                   '010-1234-5678',
                                                                                                                   '김광현 사인 글러브 팝니다.',
                                                                                                                   '김광현 선수가 사용하던 글러브 입니다. 땀냄새 겁나 나요 ~',
                                                                                                                    '1000000',
                                                                                                                   '192.126.123.1',
                                                                                                                   '1.png',
                                                                                                                   '1.png',
                                                                                                                   '0',
                                                                                                                   NOW());
                                                                                                                   
INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '방망이',
                                                                                                                   '조인성',
                                                                                                                   '010-5678-5678',
                                                                                                                   '이승엽 홈런볼 팝니다.',
                                                                                                                   '이승협 50호 홈런볼 입니다.',
                                                                                                                    '5000000',
                                                                                                                   '192.126.123.5',
                                                                                                                   '5.png',
                                                                                                                   '5.png',
                                                                                                                   '0',
                                                                                                                   NOW());
                                                                                                                   
INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '야구공',
                                                                                                                   '라붐지엔',
                                                                                                                   '010-7410-8520',
                                                                                                                   '라붐지엔 사인 구합니다.',
                                                                                                                   '지엔 사인 구해요 ~~~',
                                                                                                                    '50000000',
                                                                                                                   '192.126.120.7',
                                                                                                                   '6.png',
                                                                                                                   '6.png',
                                                                                                                   '0',
                                                                                                                   NOW()); 
                                                                                                                   
INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '방망이',
                                                                                                                   '라붐지엔~',
                                                                                                                   '010-7410-3690',
                                                                                                                   '라붐지엔 사인씨디  구합니다.',
                                                                                                                   '지엔 사인씨대 구해요 ~~~',
                                                                                                                    '500000000',
                                                                                                                   '192.120.120.7',
                                                                                                                   '7.png',
                                                                                                                   '7.png',
                                                                                                                   '0',
                                                                                                                   NOW()); 