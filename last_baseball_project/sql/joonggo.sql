`bb`CREATE TABLE joonggo
(
	j_idx INT UNSIGNED AUTO_INCREMENT, -- �Ϸù�ȣ
	j_category VARCHAR(100) NOT NULL,    -- ī�װ�(�۷���/�����/��Ÿ)
	j_title VARCHAR(100) NOT NULL,     -- ����
	j_content VARCHAR(2000) NOT NULL,  -- ����
	j_price INT NOT NULL,              -- ����
	j_ip VARCHAR(100) NOT NULL,        -- IP�ּ�
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

INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '�۷���',
                                                                                                                   '����',
                                                                                                                   '010-1234-5678',
                                                                                                                   '�豤�� ���� �۷��� �˴ϴ�.',
                                                                                                                   '�豤�� ������ ����ϴ� �۷��� �Դϴ�. ������ �̳� ���� ~',
                                                                                                                    '1000000',
                                                                                                                   '192.126.123.1',
                                                                                                                   '1.png',
                                                                                                                   '1.png',
                                                                                                                   '0',
                                                                                                                   NOW());
                                                                                                                   
INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '�����',
                                                                                                                   '���μ�',
                                                                                                                   '010-5678-5678',
                                                                                                                   '�̽¿� Ȩ���� �˴ϴ�.',
                                                                                                                   '�̽��� 50ȣ Ȩ���� �Դϴ�.',
                                                                                                                    '5000000',
                                                                                                                   '192.126.123.5',
                                                                                                                   '5.png',
                                                                                                                   '5.png',
                                                                                                                   '0',
                                                                                                                   NOW());
                                                                                                                   
INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '�߱���',
                                                                                                                   '�������',
                                                                                                                   '010-7410-8520',
                                                                                                                   '������� ���� ���մϴ�.',
                                                                                                                   '���� ���� ���ؿ� ~~~',
                                                                                                                    '50000000',
                                                                                                                   '192.126.120.7',
                                                                                                                   '6.png',
                                                                                                                   '6.png',
                                                                                                                   '0',
                                                                                                                   NOW()); 
                                                                                                                   
INSERT INTO joonggo(j_category, j_name, j_tel, j_title, j_content, j_price, j_ip, j_image_s, j_image_l, j_readhits, j_date) VALUES(
                                                                                                                   '�����',
                                                                                                                   '�������~',
                                                                                                                   '010-7410-3690',
                                                                                                                   '������� ���ξ���  ���մϴ�.',
                                                                                                                   '���� ���ξ��� ���ؿ� ~~~',
                                                                                                                    '500000000',
                                                                                                                   '192.120.120.7',
                                                                                                                   '7.png',
                                                                                                                   '7.png',
                                                                                                                   '0',
                                                                                                                   NOW()); 