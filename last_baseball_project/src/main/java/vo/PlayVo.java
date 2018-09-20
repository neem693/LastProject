package vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayVo {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat day_format = new SimpleDateFormat("dd");
	
	int day;
	String p_idx;
	String p_date;
	Date date;
	String p_score; //"����" "T" "C" ��� ���� ���´�. ������ ���״�� ����(���� ���� ���� �������� �ƴϸ� ����), T�� ������ , C�� cancle �Ѵٴ� �������ǹ� 
					//Ư���� ��Ȳ�� �ƴ϶�� "��������-Ȩ����" �� �Ǿ��ִ�. ex) "13-5"
	String p_rts;//��� ������������ �� �ϋ� W �� �϶� L ���º��� �� D ��õ���
	int s_idx;
	String t_home;
	String t_away;
	
	public PlayVo() {
		// TODO Auto-generated constructor stub
	}

	public PlayVo(String p_idx, String p_date, Date date, String p_score, String p_rts, int s_idx, String t_home,
			String t_away) {
		super();
		this.p_idx = p_idx;
		this.p_date = p_date;
		this.date = date;
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
		try {
			setDate(this.format.parse(p_date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		setDay(Integer.parseInt(day_format.format(this.date)));
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getS_idx() {
		return s_idx;
	}

	public void setS_idx(int s_idx) {
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

	
	
	
	
	public int getDay() {
		
		
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	
	
	








	

}
