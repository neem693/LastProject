package dao.toto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVo;
import vo.TotoValueVo;


public class TotoDaoImpl implements TotoDaoInterface {
	//���̹�Ƽ�� �������ִ� ��ü ����
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
		List list= session.selectList("toto.select_game_list");	//���� ������ ���̽��� ���� �ֳ� Ȯ��
	
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



	
/*	@Override
	public MemberVo selectOne(Map map) {
		// TODO Auto-generated method stub
		
		MemberVo vo=null;
		vo=session.selectOne("member.member_select_one_nick",map);
	
		return vo;
	}
*/
	
	
	
	
	

}
