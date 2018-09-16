package vo;

public class CommentVo {

	int no; // 게시물 순서
	int c_idx; // 댓글 일련번호
	String c_comment; // 댓글 내용
	String c_ip; // 댓글 ip
	String c_regdate; // 댓글 작성 일자
	
	
	int j_idx; // 중고 일련번호
	int m_idx; // 맴버 일련번호
	int nc_idx;
	
	public CommentVo() {
		// TODO Auto-generated constructor stub
	}

	public CommentVo(int no, int c_idx, String c_comment, String c_ip, String c_regdate, int j_idx, int m_idx,
			int nc_idx) {
		super();
		this.no = no;
		this.c_idx = c_idx;
		this.c_comment = c_comment;
		this.c_ip = c_ip;
		this.c_regdate = c_regdate;
		this.j_idx = j_idx;
		this.m_idx = m_idx;
		this.nc_idx = nc_idx;
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

	public String getC_comment() {
		return c_comment;
	}

	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}

	public String getC_ip() {
		return c_ip;
	}

	public void setC_ip(String c_ip) {
		this.c_ip = c_ip;
	}

	public String getC_regdate() {
		return c_regdate;
	}

	public void setC_regdate(String c_regdate) {
		this.c_regdate = c_regdate;
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
