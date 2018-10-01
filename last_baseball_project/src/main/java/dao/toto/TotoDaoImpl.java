package dao.toto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVo;
import vo.TotoValueVo;
import vo.Toto_Game_Vo;


public class TotoDaoImpl implements TotoDaoInterface {
	//마이바티스 연결해주는 객체 생성
	SqlSession session;
	
	public TotoDaoImpl() {}
	
	
	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}


	
	
	@Override
	public List select_gamelist() {
		// TODO Auto-generated method stub	
		List list= session.selectList("toto.select_game_list");	//현재 데이터 베이스에 값이 있나 확인
	
		return list;
	}


	@Override
	public String List_insert(Map map) {
		
		int result = session.insert("toto.insert_toto_schdule",map);
		
		return "update_toto_schdule";
	
	
	}


	@Override
	public int delete_table() {
		// TODO Auto-generated method stub
	
		int result = session.delete("toto.delete_table");
		
		return 0;
	}


	@Override
	public int create_table() {
		// TODO Auto-generated method stub
		
		int result = session.selectOne("toto.create_table");
		
		return result;
	}


	@Override
	public int update(TotoValueVo vo) {
	
		int result =session.update("toto.update_table",vo);
		
		return 0;
	
	
	}

	//여기까지가 파밍을 통한 일정생성 파트
//---------------------------------------------------------------------------------------------------------
	//아래부터는 플레이어 생성 게임    파트
	

	@Override
	public int insert_totogame(Toto_Game_Vo vo) {
		
		int result =session.insert("toto.insert_game",vo);
		
		return result;
	}
	
	@Override
	public String[] select_toto_game_id() {
		//최종처리된 행의 결과값을 처리 완료로 변경 T값일때 미처리 X값일때 처리완료
		
		List list=session.selectList("toto_select_game_id");
		String[] game_id= new String[list.size()];
		
		for(int i=0; i< list.size();i++) {	
			game_id[i]=(String)list.get(i);
			//System.out.println(result[i].toString());
		}
		
		return game_id;
	}
	
	
	@Override
	public String[] select_toto_game_key(String m_id) {
		
		List list=session.selectList("toto_select_game_key",m_id);
		String[] result= new String[list.size()];
	
		for(int i=0; i< list.size();i++) {	
			result[i]=(String)list.get(i);
			//System.out.println(result[i].toString());
		}
		// TODO Auto-generated method stub
		return result;
	}


	@Override
	public List select_pick_gamelist(String game_num) {
	
		List list=session.selectList("toto_select_pick_game",game_num);
		
		
		// TODO Auto-generated method stub
		return list;
	}


	@Override
	public String Game_Information(String p_idx) {
		// TODO Auto-generated method stub
		
		String game_result =session.selectOne("toto_select_game_result_info",p_idx);
		
		return game_result;
	}


	@Override
	public int game_result_update(Toto_Game_Vo vo) {
		
	   session.update("toto_game_result_update",vo);
		
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int remove_game_result(Toto_Game_Vo vo) {
		//최종처리된 행의 결과값을 처리 완료로 변경 T값일때 미처리 X값일때 처리완료
		session.update("toto_game_result_remove",vo);
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int toto_member_money_update(MemberVo vo) {
		
		session.update("toto_game_money",vo);
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List my_bat_gamelist(MemberVo vo) {

	List list =	session.selectList("toto_my_bating_list",vo);
		return list;

	
	}


	@Override
	public MemberVo my_money_read(MemberVo vo) {

		MemberVo voo = session.selectOne("member_select_vo_id",vo);
		
		return voo;
	}
	

	
	
	
	
	
	
	
	
	

}
