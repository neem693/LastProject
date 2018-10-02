package dao.toto;

import java.util.List;
import java.util.Map;

import vo.MemberVo;
import vo.My_Game_Result_Vo;
import vo.TotoValueVo;
import vo.Toto_Game_Vo;

public interface TotoDaoInterface {

	
	String List_insert(Map map); 
	int delete_table();
	int create_table();
	int update(TotoValueVo vo);
	
	List select_gamelist();
//---------------------------------------------
	int insert_totogame(Toto_Game_Vo vo);
	
	List select_pick_gamelist(String p_idx);
	String[] select_toto_game_key(String m_id);
	String[] select_toto_game_id();
	
	String Game_Information(String p_idx);
	int game_result_update(Toto_Game_Vo vo);
	int remove_game_result(Toto_Game_Vo vo);
	int toto_member_money_update(MemberVo vo);
	
	List my_bat_gamelist(MemberVo vo);
	
	MemberVo my_money_read(MemberVo vo);
	//---------------------------------------------
	
	int my_game_result_insert(My_Game_Result_Vo vo);
	
	List my_game_list(String id);
	
	
	
	
}
