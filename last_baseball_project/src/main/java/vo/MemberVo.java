package vo;

public class MemberVo {

	int m_idx;
	String m_id;
	String m_name; 
	String m_pwd; 
	String m_date;
	String m_email; 
	String m_photo;
	String m_comment; 
	String m_addr; 
	String m_zip_code;
	String m_tel;
	String t_name;
	String m_nick; 
	String m_ip; 
	String m_money; 
	
	
	public String getM_money() {
		return m_money;
	}


	public void setM_money(String m_money) {
		this.m_money = m_money;
	}


	public MemberVo(int m_idx, String m_id, String m_name, String m_pwd, String m_date, String m_email, String m_photo,
			String m_comment, String m_money,String m_addr, String m_zip_code, String m_tel, String t_name, String m_nick,
			String m_ip) {
		super();
		this.m_idx = m_idx;
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_pwd = m_pwd;
		this.m_date = m_date;
		this.m_email = m_email;
		this.m_photo = m_photo;
		this.m_comment = m_comment;
		this.m_addr = m_addr;
		this.m_zip_code = m_zip_code;
		this.m_tel = m_tel;
		this.t_name = t_name;
		this.m_nick = m_nick;
		this.m_ip = m_ip;
		this.m_money =m_money;
	}


	public int getM_idx() {
		return m_idx;
	}


	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}


	public String getM_name() {
		return m_name;
	}


	public void setM_name(String m_name) {
		this.m_name = m_name;
	}


	public String getM_pwd() {
		return m_pwd;
	}


	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}


	public String getM_date() {
		return m_date;
	}


	public void setM_date(String m_date) {
		this.m_date = m_date;
	}


	public String getM_email() {
		return m_email;
	}


	public void setM_email(String m_email) {
		this.m_email = m_email;
	}


	public String getM_photo() {
		return m_photo;
	}


	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}


	public String getM_comment() {
		return m_comment;
	}


	public void setM_comment(String m_comment) {
		this.m_comment = m_comment;
	}


	public String getM_addr() {
		return m_addr;
	}


	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}


	public String getM_zip_code() {
		return m_zip_code;
	}


	public void setM_zip_code(String m_zip_code) {
		this.m_zip_code = m_zip_code;
	}


	public String getM_tel() {
		return m_tel;
	}


	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}


	public String getT_name() {
		return t_name;
	}


	public void setT_name(String t_name) {
		this.t_name = t_name;
	}


	public String getM_nick() {
		return m_nick;
	}


	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}


	public String getM_ip() {
		return m_ip;
	}


	public void setM_ip(String m_ip) {
		this.m_ip = m_ip;
	}


	public MemberVo() {
		
	}
	
	
	
	


	

}
