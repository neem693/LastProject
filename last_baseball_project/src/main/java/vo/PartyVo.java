package vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import myconst.Myconst;

public class PartyVo {

	public SimpleDateFormat out_put_format_p_date = new SimpleDateFormat("yyyy/MM/dd[HH:mm]");
	public SimpleDateFormat day_format = new SimpleDateFormat("(MM/dd)");
	public SimpleDateFormat Hour_format = new SimpleDateFormat("(HH:mm)");

	int pt_idx;
	String pt_condition;
	String pt_name;
	String date;// 날짜로 할지, 아니면 시간으로 할지를 정하는 것
	String pt_day;
	Date pt_day_date;
	String pt_day_str; //pt_day의 데이트형을 다시 변환시켜 깔끔하게 만들어주는 역할을 한다.
	
	String datetime;// 리스트를 출력하는 용도로만 사용하는 pt_day전용의 datetime이다.//리스트 아니더라도 쓴다.
	String pt_date; //파티 등록 일자
	Date pt_date_date;//파티 등록일자의 date

	String pt_purpose; // 인서트 받을 때 이건 처음에 숫자로 나오니 주의
	int pt_people;
	int pt_maxPeople; // 이것도 숫자로 나오니 주의 하라
	String p_idx;
	String pt_location; // 인서트 할떄 이 값을 수정해야 한다. 이 값은 월래 mysql전용의 데이트time값이 들어가 있어야 한다.
	String pt_text;
	String t_name;
	String day;
	int match_count;

	/// list를 뽑아오기 위해서 해야 할것//
	String m_nick;
	String p_date;
	Date p_date_date;
	String s_name;
	String addr1; // 모임장소 출력하기 위한 것
	////// 해당leader가 얼마나 지금까지 파티를 만들었는지에 대한 카운트///
	int leader_count;

	String m_idx;

	////// party_book도 여기서 관리한다.
	int b_leader;

	////// 좌표 x,y축을 정한다.
	double x;
	double y;

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

		try {
			this.setP_date_date(Myconst.DateCheck.DATE_FORMAT.parse(p_date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.p_date = out_put_format_p_date.format(this.getP_date_date());

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

	public int getPt_people() {
		return pt_people;
	}

	public void setPt_people(int pt_people) {
		this.pt_people = pt_people;
	}

	public Date getPt_day_date() {
		return pt_day_date;
	}

	public void setPt_day_date(Date pt_day_date) {
		this.pt_day_date = pt_day_date;
	}

	public Date getP_date_date() {
		return p_date_date;
	}

	public void setP_date_date(Date p_date_date) {
		this.p_date_date = p_date_date;
	}

	public String getDatetime() {

		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	

	public String getAddr1() {
		this.setAddr1(this.getPt_location().split("/")[0]);
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public double getX() {
		this.x = Double.parseDouble(this.getPt_location().split("/")[1].trim());
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		this.y = Double.parseDouble(this.getPt_location().split("/")[2].trim());
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getPt_date() {
		return out_put_format_p_date.format(this.pt_date_date);
	}

	public void setPt_date(String pt_date) {
		try {
			setPt_date_date(Myconst.DateCheck.DATE_FORMAT.parse(pt_date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pt_date = pt_date;
	}

	public Date getPt_date_date() {
		return pt_date_date;
	}

	public void setPt_date_date(Date pt_date_date) {
		this.pt_date_date = pt_date_date;
	}

	public String getPt_day_str() {
	
		return pt_day_str;
	}

	public void setPt_day_str(String pt_day_str) {
		this.pt_day_str = pt_day_str;
	}


	
	

}
