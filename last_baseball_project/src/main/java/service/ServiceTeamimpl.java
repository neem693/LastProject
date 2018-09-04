package service;

import java.util.List;

import dao.TeamDaoImpl;
import vo.TeamVo;

public class ServiceTeamimpl implements ServiceInterface {
	
	
	TeamDaoImpl dao;
	
	

	public TeamDaoImpl getDao() {
		return dao;
	}

	public void setDao(TeamDaoImpl dao) {
		this.dao = dao;
	}
	
	
	

	@Override
	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object [] ob) {
		// TODO Auto-generated method stub
		
		int count = dao.selectCount();
		System.out.println(count + "---");
		TeamVo teamvo = (TeamVo)ob[0];
		
		if(count ==0)
			dao.insert_all(ob);
		if(count==10)
			dao.update_all(ob);
		
		System.out.println(teamvo.getT_name());
		System.out.println(teamvo.getT_rank());
		System.out.println(teamvo.getT_nom());
		System.out.println(teamvo.getT_win());
		System.out.println(teamvo.getT_lose());
		System.out.println(teamvo.getT_draw());
		System.out.println(teamvo.getT_winpo());
		System.out.println(teamvo.getT_leading());
		System.out.println(teamvo.getT_recent10());
		System.out.println(teamvo.getT_contn());
		System.out.println(teamvo.getT_home());
		System.out.println(teamvo.getT_away());
		
		
		
		
		
		return 0;
	}

}
