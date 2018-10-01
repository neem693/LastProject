package vo;

public class Toto_batting_Vo {

	int to_bt_idx;
	int m_idx;
	String to_bt_result;
	String p_idx;
	String winner_ratio;
	String lose_ratio;
	String game_result;
	
	
	
	public int getTo_bt_idx() {
		return to_bt_idx;
	}
	public void setTo_bt_idx(int to_bt_idx) {
		this.to_bt_idx = to_bt_idx;
	}
	public int getM_idx() {
		return m_idx;
	}
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	public String getTo_bt_result() {
		return to_bt_result;
	}
	public void setTo_bt_result(String to_bt_result) {
		this.to_bt_result = to_bt_result;
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
	public String getGame_result() {
		return game_result;
	}
	public void setGame_result(String game_result) {
		this.game_result = game_result;
	}
	public Toto_batting_Vo() {}
	public Toto_batting_Vo(int to_bt_idx, int m_idx, String to_bt_result, String p_idx, String winner_ratio,
			String lose_ratio, String game_result) {
		super();
		this.to_bt_idx = to_bt_idx;
		this.m_idx = m_idx;
		this.to_bt_result = to_bt_result;
		this.p_idx = p_idx;
		this.winner_ratio = winner_ratio;
		this.lose_ratio = lose_ratio;
		this.game_result = game_result;
	}
	
	
}
