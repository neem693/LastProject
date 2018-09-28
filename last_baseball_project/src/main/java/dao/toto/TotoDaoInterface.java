package dao.toto;

import java.util.List;
import java.util.Map;

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
	
	String Game_Information(String p_idx);
	int game_result_update(Toto_Game_Vo vo);
	
	
	
}
