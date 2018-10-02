package service.toto;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import vo.MemberVo;
import vo.My_Game_Result_Vo;
import vo.Toto_Game_Vo;

public interface TotoServiceInterface {

	String MakeToToScore() throws IOException;
	List Select_gamelist();
	List my_bat_gamelist(MemberVo vo);
	
	String Make_game(HttpServletRequest request);
	
	String[] Game_Result(String m_id);
	
	int update_money(MemberVo vo);
	MemberVo my_money_read(MemberVo vo);
	
	List my_game_result(String m_id);
	
}
