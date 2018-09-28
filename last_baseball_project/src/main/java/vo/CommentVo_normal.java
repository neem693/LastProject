package vo;

public class CommentVo_normal {

	int c_idx;
	String c_comment;
	String c_regdate;
	int c_ref;
	int c_step;
	int c_depth;
	int j_idx;
	int nc_idx;
	String c_ip;
	
	public CommentVo_normal() {
		// TODO Auto-generated constructor stub
	}
	
	public CommentVo_normal(int c_idx, String c_comment, String c_regdate, int j_idx, int nc_idx, String c_ip) {
		super();
		this.c_idx = c_idx;
		this.c_comment = c_comment;
		this.c_regdate = c_regdate;
		this.j_idx = j_idx;
		this.nc_idx = nc_idx;
		this.c_ip = c_ip;
	}

	public CommentVo_normal(int c_idx, String c_comment, String c_regdate, int c_ref, int c_step, int c_depth, int j_idx,
			int nc_idx, String c_ip) {
		super();
		this.c_idx = c_idx;
		this.c_comment = c_comment;
		this.c_regdate = c_regdate;
		this.c_ref = c_ref;
		this.c_step = c_step;
		this.c_depth = c_depth;
		this.j_idx = j_idx;
		this.nc_idx = nc_idx;
		this.c_ip = c_ip;
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
	public String getC_regdate() {
		return c_regdate;
	}
	public void setC_regdate(String c_regdate) {
		this.c_regdate = c_regdate;
	}
	public int getC_ref() {
		return c_ref;
	}
	public void setC_ref(int c_ref) {
		this.c_ref = c_ref;
	}
	public int getC_step() {
		return c_step;
	}
	public void setC_step(int c_step) {
		this.c_step = c_step;
	}
	public int getC_depth() {
		return c_depth;
	}
	public void setC_depth(int c_depth) {
		this.c_depth = c_depth;
	}
	public int getJ_idx() {
		return j_idx;
	}
	public void setJ_idx(int j_idx) {
		this.j_idx = j_idx;
	}
	public int getNc_idx() {
		return nc_idx;
	}
	public void setNc_idx(int nc_idx) {
		this.nc_idx = nc_idx;
	}
	public String getC_ip() {
		return c_ip;
	}
	public void setC_ip(String c_ip) {
		this.c_ip = c_ip;
	}
	
	
}
