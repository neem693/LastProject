package vo;

public class CommentVo {

	int no; // 게시물 순서
	int c_idx; // 댓글 일련번호
	String m_nick; // 댓글 닉네임
	String m_id; // 댓글 아이디
	String c_content; // 댓글 내용
	String c_ip; // 댓글 ip
	String c_date; // 댓글 작성 일자
	
	
	int j_idx; // 중고 일련번호
	int m_idx; // 맴버 일련번호
	int nc_idx;
	
	public CommentVo() {
		// TODO Auto-generated constructor stub
	}

	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getC_idx() {
		return c_idx;
	}

	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public String getM_nick() {
		return m_nick;
	}

	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getC_ip() {
		return c_ip;
	}

	public void setC_ip(String c_ip) {
		this.c_ip = c_ip;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public int getJ_idx() {
		return j_idx;
	}

	public void setJ_idx(int j_idx) {
		this.j_idx = j_idx;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public int getNc_idx() {
		return nc_idx;
	}

	public void setNc_idx(int nc_idx) {
		this.nc_idx = nc_idx;
	}




}
