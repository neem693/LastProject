package vo;

import org.springframework.web.multipart.MultipartFile;

public class JoonggoVo {
	
    //callback URL
    private String callback;
    
    //콜백함수??
    private String callback_func;
    
   //Spring에서 파일업로드 처리하는 객체
  	MultipartFile Filedata;
  	
   /*중고 vo*/
    int j_idx;
    String j_category;
    String j_title;
    String j_content;
    int j_price;
    String j_ip;
    String j_image;
    int j_readhits;
    String j_date;
    
    /*회원vo*/
    int m_idx;
    String m_name;
    String m_tel;
    String m_nick;
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getCallback_func() {
		return callback_func;
	}
	public void setCallback_func(String callback_func) {
		this.callback_func = callback_func;
	}
	public MultipartFile getFiledata() {
		return Filedata;
	}
	public void setFiledata(MultipartFile filedata) {
		Filedata = filedata;
	}
	public int getJ_idx() {
		return j_idx;
	}
	public void setJ_idx(int j_idx) {
		this.j_idx = j_idx;
	}
	public String getJ_category() {
		return j_category;
	}
	public void setJ_category(String j_category) {
		this.j_category = j_category;
	}
	public String getJ_title() {
		return j_title;
	}
	public void setJ_title(String j_title) {
		this.j_title = j_title;
	}
	public String getJ_content() {
		return j_content;
	}
	public void setJ_content(String j_content) {
		this.j_content = j_content;
	}
	public int getJ_price() {
		return j_price;
	}
	public void setJ_price(int j_price) {
		this.j_price = j_price;
	}
	public String getJ_ip() {
		return j_ip;
	}
	public void setJ_ip(String j_ip) {
		this.j_ip = j_ip;
	}
	public String getJ_image() {
		return j_image;
	}
	public void setJ_image(String j_image) {
		this.j_image = j_image;
	}
	public int getJ_readhits() {
		return j_readhits;
	}
	public void setJ_readhits(int j_readhits) {
		this.j_readhits = j_readhits;
	}
	public String getJ_date() {
		return j_date;
	}
	public void setJ_date(String j_date) {
		this.j_date = j_date;
	}
	public int getM_idx() {
		return m_idx;
	}
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_tel() {
		return m_tel;
	}
	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
    
	
    
  
    


}
