package vo;

public class TeamVo {
	String t_name;
	int t_rank;
	int t_nom; // Number of match
	int t_win;
	int t_lose;
	int t_draw; //¹«½ÂºÎ
	double t_winpo; //½Â·ü
	double t_leading; //1À§¿ÍÀÇ °ÔÀÓÂ÷
	String t_recent10; //ÃÖ±Ù 10°æ±â
	String t_contn;//¿¬¼Ó ½Â? or ÆÐ
	String t_home;
	String t_away;
	
	
	
	
	
	
	public TeamVo() {
		
	}
	public TeamVo(String t_name, int t_rank, int t_nom, int t_win, int t_lose, int t_draw, double t_winpo,
			int t_leading, String t_recent10, String t_contn, String t_home, String t_away) {
		super();
		this.t_name = t_name;
		this.t_rank = t_rank;
		this.t_nom = t_nom;
		this.t_win = t_win;
		this.t_lose = t_lose;
		this.t_draw = t_draw;
		this.t_winpo = t_winpo;
		this.t_leading = t_leading;
		this.t_recent10 = t_recent10;
		this.t_contn = t_contn;
		this.t_home = t_home;
		this.t_away = t_away;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public int getT_rank() {
		return t_rank;
	}
	public void setT_rank(int t_rank) {
		this.t_rank = t_rank;
	}
	public int getT_nom() {
		return t_nom;
	}
	public void setT_nom(int t_nom) {
		this.t_nom = t_nom;
	}
	public int getT_win() {
		return t_win;
	}
	public void setT_win(int t_win) {
		this.t_win = t_win;
	}
	public int getT_lose() {
		return t_lose;
	}
	public void setT_lose(int t_lose) {
		this.t_lose = t_lose;
	}
	public int getT_draw() {
		return t_draw;
	}
	public void setT_draw(int t_draw) {
		this.t_draw = t_draw;
	}
	public double getT_winpo() {
		return t_winpo;
	}
	public void setT_winpo(double t_winpo) {
		this.t_winpo = t_winpo;
	}

	public String getT_recent10() {
		return t_recent10;
	}
	public void setT_recent10(String t_recent10) {
		this.t_recent10 = t_recent10;
	}
	public String getT_contn() {
		return t_contn;
	}
	public void setT_contn(String t_contn) {
		this.t_contn = t_contn;
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
	public double getT_leading() {
		return t_leading;
	}
	public void setT_leading(double t_leading) {
		this.t_leading = t_leading;
	}
	
	
	
	
	
	

}
