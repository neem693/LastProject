package vo;

import java.util.Date;

public class Toto_sch_Vo {

	int toto_idx;
	public int getToto_idx() {
		return toto_idx;
	}

	public void setToto_idx(int toto_idx) {
		this.toto_idx = toto_idx;
	}

	String t_home;
	String t_away;
	String p_score;
	String rts;
	String p_idx;
	String winner_ratio;
	String lose_ratio;
	Date p_date;
	String toto_place;
	
	public String getToto_place() {
		return toto_place;
	}

	public void setToto_place(String toto_place) {
		this.toto_place = toto_place;
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

	public String getP_score() {
		return p_score;
	}

	public void setP_score(String p_score) {
		this.p_score = p_score;
	}

	public String getRts() {
		return rts;
	}

	public void setRts(String rts) {
		this.rts = rts;
	}

	public String getP_idx() {
		return p_idx;
	}

	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}

	public String getWinner_ratio() {
		return winner_ratio;
	}

	public void setWinner_ratio(String winner_ratio) {
		this.winner_ratio = winner_ratio;
	}

	public String getLose_ratio() {
		return lose_ratio;
	}

	public void setLose_ratio(String lose_ratio) {
		this.lose_ratio = lose_ratio;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public Toto_sch_Vo() {};
	
	public Toto_sch_Vo(String t_home, String t_away, String p_score, String rts, String p_idx, String winner_ratio,
			String lose_ratio, Date p_date) {
		super();
		this.t_home = t_home;
		this.t_away = t_away;
		this.p_score = p_score;
		this.rts = rts;
		this.p_idx = p_idx;
		this.winner_ratio = winner_ratio;
		this.lose_ratio = lose_ratio;
		this.p_date = p_date;
	}

	
	
}


