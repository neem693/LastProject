package vo;

public class TotoValueVo {
		
	int    toto_idx;

	String winner_ratio;
	String lose_ratio;
	String p_idx;
	String toto_place;
	
	public String getToto_place() {
		return toto_place;
	}


	public void setToto_place(String toto_place) {
		this.toto_place = toto_place;
	}


	public String getP_idx() {
		return p_idx;
	}


	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}


	public TotoValueVo(int toto_idx, String home_team, String away_team, String winner_ratio, String lost_ratio,
			String toto_place) {
		super();
		this.toto_idx = toto_idx;
	
	
		this.winner_ratio = winner_ratio;
		this.lose_ratio = lost_ratio;
	
	}


	public TotoValueVo() {
	}
	
	
	public int getToto_idx() {
		return toto_idx;
	}
	public void setToto_idx(int toto_idx) {
		this.toto_idx = toto_idx;
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
	public void setLose_ratio(String lost_ratio) {
		this.lose_ratio = lost_ratio;
	}

	
	
}
