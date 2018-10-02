package vo;

public class My_Game_Result_Vo {

	
	String total_money;
	String final_ratio;
	String game_number;
	String m_id;
	
	public My_Game_Result_Vo() {};
	
	
	public My_Game_Result_Vo(String total_money, String final_ratio, String game_number) {
		super();
		this.total_money = total_money;
		this.final_ratio = final_ratio;
		this.game_number = game_number;
	}
	public String getTotal_money() {
		return total_money;
	}
	public void setTotal_money(String total_money) {
		this.total_money = total_money;
	}
	public String getFinal_ratio() {
		return final_ratio;
	}
	public void setFinal_ratio(String final_ratio) {
		this.final_ratio = final_ratio;
	}
	public String getGame_number() {
		return game_number;
	}
	public void setGame_number(String game_number) {
		this.game_number = game_number;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	
	
	
	
	
	
	
}
