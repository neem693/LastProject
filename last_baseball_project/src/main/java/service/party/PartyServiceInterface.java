package service.party;

import java.util.List;
import java.util.Map;

import vo.MemberVo;
import vo.PartyVo;
import vo.Party_bookVo;
import vo.PlayVo;
import vo.StadiumVo;
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
List take_party_list(String year, String month, String day, String team, int nowPage);
String return_party_paging(int nowPage,String day, int page_total_count);
int total_page_count(String year, String month, String day,String team);
PartyVo selectPartyOne(int pt_idx);
StadiumVo select_stadium_one(PlayVo vo);
Party_bookVo getPartyLeader(int pt_idx_int);
List getPartyMember(int pt_idx_int);
int getleaderCount(int m_idx);
public PartyVo setting_datetime(PartyVo vo);
int set_join_member_to_party(MemberVo member, String pt_idx) throws Exception;
int member_leave_from_party(MemberVo member, String pt_idx) throws Exception;
int member_joined_today(String ymd, MemberVo vo);


}
