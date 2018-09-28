package vo;

public class NormalVo {

	int nc_idx;				//커뮤니티 게시글 번호
	String nc_title;		//커뮤니티 제목
	String nc_contents;		//커뮤니티 내용
	int nc_views;			//조회수
	String nc_ip;			//ip받아오기
	String nc_regdate;		//게시한 일자
	String m_nick;			//게시자 닉네임
	String m_id;			//게시자 아이디
	String m_idx;			//멤버 번호
	int nc_count;			//댓글 갯수
	
	public int getNc_count() {
		return nc_count;
	}
	public void setNc_count(int nc_count) {
		this.nc_count = nc_count;
	}
	public int getNc_idx() {
		return nc_idx;
	}
	public void setNc_idx(int nc_idx) {
		this.nc_idx = nc_idx;
	}
	public String getNc_title() {
		return nc_title;
	}
	public void setNc_title(String nc_title) {
		this.nc_title = nc_title;
	}
	public String getNc_contents() {
		return nc_contents;
	}
	public void setNc_contents(String nc_contents) {
		this.nc_contents = nc_contents;
	}
	public int getNc_views() {
		return nc_views;
	}
	public void setNc_views(int nc_views) {
		this.nc_views = nc_views;
	}
	public String getNc_ip() {
		return nc_ip;
	}
	public void setNc_ip(String nc_ip) {
		this.nc_ip = nc_ip;
	}
	public String getNc_regdate() {
		return nc_regdate;
	}
	public void setNc_regdate(String nc_regdate) {
		this.nc_regdate = nc_regdate;
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
	public String getM_idx() {
		return m_idx;
	}
	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}
	
}
