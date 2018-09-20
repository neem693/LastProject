package vo;

public class PartyVo {
	
	int pt_idx;
	String pt_condition;
	String pt_name;
	String date;//날짜로 할지, 아니면 시간으로 할지를 정하는 것
	String pt_day;
	String pt_purpose; //인서트 받을 때 이건 처음에 숫자로 나오니 주의
	int pt_maxPeople; // 이것도 숫자로 나오니 주의 하라
	String p_idx;
	String pt_location; //인서트 할떄 이 값을 수정해야 한다. 이 값은 월래 mysql전용의 데이트time값이 들어가 있어야 한다.
	String pt_text;
	String t_name;
	String day;
	int match_count;
	
	
	
	
	///list를 뽑아오기 위해서 해야 할것//
	String m_nick;
	String p_date;
	String s_name;
	//////해당leader가 얼마나 지금까지 파티를 만들었는지에 대한 카운트///
	int leader_count;
	
	String m_idx;
	
	
	//////party_book도 여기서 관리한다.
	int b_leader;
	
	
	
	
	
	
	
	public PartyVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getPt_name() {
		return pt_name;
	}
	public void setPt_name(String pt_name) {
		this.pt_name = pt_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPt_day() {
		return pt_day;
	}
	public void setPt_day(String pt_day) {
		this.pt_day = pt_day;
	}
	public String getPt_purpose() {
		return pt_purpose;
	}
	public void setPt_purpose(String pt_purpose) {
		this.pt_purpose = pt_purpose;
	}

	public String getP_idx() {
		return p_idx;
	}
	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}
	public String getPt_location() {
		return pt_location;
	}
	public void setPt_location(String pt_location) {
		this.pt_location = pt_location;
	}




	public int getPt_maxPeople() {
		return pt_maxPeople;
	}




	public void setPt_maxPeople(int pt_maxPeople) {
		this.pt_maxPeople = pt_maxPeople;
	}




	public String getPt_text() {
		return pt_text;
	}




	public void setPt_text(String pt_text) {
		this.pt_text = pt_text;
	}




	public int getPt_idx() {
		return pt_idx;
	}




	public void setPt_idx(int pt_idx) {
		this.pt_idx = pt_idx;
	}




	public String getPt_condition() {
		return pt_condition;
	}




	public void setPt_condition(String pt_condition) {
		this.pt_condition = pt_condition;
	}




	public String getT_name() {
		return t_name;
	}




	public void setT_name(String t_name) {
		this.t_name = t_name;
	}




	public String getDay() {
		return day;
	}




	public void setDay(String day) {
		this.day = day;
	}




	public int getMatch_count() {
		return match_count;
	}




	public void setMatch_count(int match_count) {
		this.match_count = match_count;
	}




	public String getM_nick() {
		return m_nick;
	}




	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}




	public String getP_date() {
		return p_date;
	}




	public void setP_date(String p_date) {
		this.p_date = p_date;
	}




	public String getS_name() {
		return s_name;
	}




	public void setS_name(String s_name) {
		this.s_name = s_name;
	}




	public int getB_leader() {
		return b_leader;
	}




	public void setB_leader(int b_leader) {
		this.b_leader = b_leader;
	}




	public int getLeader_count() {
		return leader_count;
	}




	public void setLeader_count(int leader_count) {
		this.leader_count = leader_count;
	}




	public String getM_idx() {
		return m_idx;
	}




	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}
	
	
	
	
	
	
	
 
	
	

}
