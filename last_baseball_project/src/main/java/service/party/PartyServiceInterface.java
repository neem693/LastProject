package service.party;

import java.util.List;
import java.util.Map;

import vo.TeamVo;

public interface PartyServiceInterface {
	
	

int team_update(Object [] vo);
int match_update(Object [] vo,Object map);
String parsing_manager(String year,String month);
int check_parsing();


List take_play_list(String year,String month);
List take_play_list(String year, String month, String day);

Map getWeekday(String year, String month);
String selectStadium(String p_idx);





}
