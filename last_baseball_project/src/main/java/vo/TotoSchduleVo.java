package vo;

public class TotoSchduleVo {
		
	int    toto_idx;
	String home_team;
	String away_team;
	String winner_ratio;
	String lost_ratio;
	String toto_place;
	String toto_p_idx;
	
	
	public String getToto_p_idx() {
		return toto_p_idx;
	}


	public void setToto_p_idx(String toto_p_idx) {
		this.toto_p_idx = toto_p_idx;
	}


	public TotoSchduleVo(int toto_idx, String home_team, String away_team, String winner_ratio, String lost_ratio,
			String toto_place) {
		super();
		this.toto_idx = toto_idx;
		this.home_team = home_team;
		this.away_team = away_team;
		this.winner_ratio = winner_ratio;
		this.lost_ratio = lost_ratio;
		this.toto_place = toto_place;
	}


	public TotoSchduleVo() {
	}
	
	
	public int getToto_idx() {
		return toto_idx;
	}
	public void setToto_idx(int toto_idx) {
		this.toto_idx = toto_idx;
	}

	public String getHome_team() {
		return home_team;
	}
	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}
	public String getAway_team() {
		return away_team;
	}
	public void setAway_team(String away_team) {
		this.away_team = away_team;
	}
	public String getWinner_ratio() {
		return winner_ratio;
	}
	public void setWinner_ratio(String winner_ratio) {
		this.winner_ratio = winner_ratio;
	}
	public String getLost_ratio() {
		return lost_ratio;
	}
	public void setLost_ratio(String lost_ratio) {
		this.lost_ratio = lost_ratio;
	}
	public String getToto_place() {
		return toto_place;
	}
	public void setToto_place(String toto_place) {
		this.toto_place = toto_place;
	}
	
	
	
}
