`bb`CREATE TABLE joonggo
(
	j_idx INT UNSIGNED AUTO_INCREMENT, -- �Ϸù�ȣ
	j_category VARCHAR(100) NOT NULL,    -- ī�װ�(�۷���/�����/��Ÿ)
	j_title VARCHAR(100) NOT NULL,     -- ����
	j_content VARCHAR(2000) NOT NULL,  -- ����
	j_price INT NOT NULL,              -- ����
	j_ip VARCHAR(100) NOT NULL,        -- IP�ּ�
	j_filename VARCHAR(255) NOT NULL,  -- ��ǥ���� ���� �̸�
	j_image VARCHAR(100) NOT NULL,     -- ��ǥ���� 
	j_readhits INT,                    -- ��ȸ��
	j_date DATETIME,                   -- �������
	m_idx INT UNSIGNED,
	CONSTRAINT pk_used_buy_idx PRIMARY KEY(j_idx),
	CONSTRAINT fk_used_buy_m_idx FOREIGN KEY(m_idx)
	REFERENCES member(m_idx) ON UPDATE CASCADE 
)
DROP TABLE joonggo
SELECT *FROM joonggo

INSERT INTO joonggo(j_category, j_title, j_content, j_price, j_ip, j_filename, j_image, j_readhits, j_date) VALUES(
                                                                                                                   '����',
                                                                                                                   '��� ���� LoveGame ����~~~����',
                                                                                                                   'LoveGame����',
                                                                                                                    '5000',
                                                                                                                   '192.120.120.10',
                                                                                                                   '�λ�',
                                                                                                                   '8.png',
                                                                                                                   '0',
                                                                                                                   NOW()); 