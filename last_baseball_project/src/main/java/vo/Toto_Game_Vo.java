package vo;

public class Toto_Game_Vo {
	
	String m_id;
	String game_number;
	String bat_price;
    String ratio;
	String p_idx;
	String bat_win_lose;
	String game_result;
	
	public Toto_Game_Vo() {};
	
	
	public Toto_Game_Vo(String m_id, String game_number, String bat_price, String ratio, String p_idx,
			String bat_win_lose, String game_result) {
		super();
		this.m_id = m_id;
		this.game_number = game_number;
		this.bat_price = bat_price;
		this.ratio = ratio;
		this.p_idx = p_idx;
		this.bat_win_lose = bat_win_lose;
		this.game_result = game_result;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}


	public String getGame_number() {
		return game_number;
	}


	public void setGame_number(String game_number) {
		this.game_number = game_number;
	}


	public String getBat_price() {
		return bat_price;
	}


	public void setBat_price(String bat_price) {
		this.bat_price = bat_price;
	}


	public String getRatio() {
		return ratio;
	}


	public void setRatio(String ratio) {
		this.ratio = ratio;
	}


	public String getP_idx() {
		return p_idx;
	}


	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}




	public String getBat_win_lose() {
		return bat_win_lose;
	}


	public void setBat_win_lose(String bat_win_lose) {
		this.bat_win_lose = bat_win_lose;
	}


	public String getGame_result() {
		return game_result;
	}


	public void setGame_result(String game_result) {
		this.game_result = game_result;
	}
	
	

	
}
