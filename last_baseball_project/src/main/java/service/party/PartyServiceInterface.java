package service.party;

import java.util.List;
import java.util.Map;

import vo.MemberVo;
import vo.PartyVo;
import vo.PlayVo;
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
int insert_party(PartyVo vo,String year,String month,String day);
PlayVo select_play_one(String p_idx);
boolean check_long_time_in_match(String year, String month, String day);
Map get_party_count(String year,String month,String team);
int insert_party_book(MemberVo member);




}
