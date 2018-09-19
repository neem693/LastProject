package service;

import java.util.List;

import vo.TeamVo;

public interface PartyServiceInterface {
	
	

int team_update(Object [] vo);
int match_update(Object [] vo,Object map);
String parsing_manager(String year,String month);
int check_parsing();





}
