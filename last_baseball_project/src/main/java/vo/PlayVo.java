package vo;

import java.util.Date;

public class PlayVo {
	
	String p_idx;
	String p_date;
	Date date;
	String p_score;
	String p_rts;//��� ������������ �� �ϋ� W �� �϶� L
	String s_idx;
	String t_home;
	String t_away;
	
	public PlayVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	




	public PlayVo(String p_idx, String p_date, String p_score, String p_rts, String s_idx, String t_home,
			String t_away) {
		super();
		this.p_idx = p_idx;
		this.p_date = p_date;
		this.p_score = p_score;
		this.p_rts = p_rts;
		this.s_idx = s_idx;
		this.t_home = t_home;
		this.t_away = t_away;
	}








	public String getP_idx() {
		return p_idx;
	}
	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public String getP_score() {
		return p_score;
	}
	public void setP_score(String p_score) {
		this.p_score = p_score;
	}
	public String getP_rts() {
		return p_rts;
	}
	public void setP_rts(String p_rts) {
		this.p_rts = p_rts;
	}
	public String getS_idx() {
		return s_idx;
	}
	public void setS_idx(String s_idx) {
		this.s_idx = s_idx;
	}
	public String getT_home() {
		return t_home;
	}
	public void setT_home(String t_home) {
		this.t_home = t_home;
	}
	public String getT_away() {
		return t_away;
	}
	public void setT_away(String t_away) {
		this.t_away = t_away;
	}
	
	
	

}