package service;


import dao.PartyDaoInerface;
import vo.PlayVo;
import vo.TeamVo;

public class ServiceTeamimpl implements PartyServiceInterface {

	PartyDaoInerface team_dao,play_dao;
	
	
	
	

	public PartyDaoInerface getTeam_dao() {
		return team_dao;
	}

	public void setTeam_dao(PartyDaoInerface team_dao) {
		this.team_dao = team_dao;
	}

	public PartyDaoInerface getPlay_dao() {
		return play_dao;
	}

	public void setPlay_dao(PartyDaoInerface play_dao) {
		this.play_dao = play_dao;
	}

	@Override
	public int team_update(Object[] ob) {
		// TODO Auto-generated method stub

		int count = team_dao.selectCount();
		System.out.println(count + "---");
		TeamVo teamvo = (TeamVo) ob[0];

		if (count == 0)
			team_dao.insert_all(ob);
		if (count == 10)
			team_dao.update_all(ob);

		// System.out.println(teamvo.getT_name());
		// System.out.println(teamvo.getT_rank());
		// System.out.println(teamvo.getT_nom());
		// System.out.println(teamvo.getT_win());
		// System.out.println(teamvo.getT_lose());
		// System.out.println(teamvo.getT_draw());
		// System.out.println(teamvo.getT_winpo());
		// System.out.println(teamvo.getT_leading());
		// System.out.println(teamvo.getT_recent10());
		// System.out.println(teamvo.getT_contn());
		// System.out.println(teamvo.getT_home());
		// System.out.println(teamvo.getT_away());
		//

		return 0;
	}

	@Override
	public int match_update(Object[] ob, Object obb) {
		// TODO Auto-generated method stub
		// obb는 맵을 넣어주기 바란다.
		int res = 0;
		int count = play_dao.selectCount(obb);
		System.out.println(count + "---");
		PlayVo vo = (PlayVo) ob[0];
		
		
		System.out.println(vo.getP_idx());

		if (count > 0 && count == ob.length)
			res = play_dao.update_all(ob);
		else if (count == 0)
			res = play_dao.insert_all(ob);
		else
			return -1;

		return res;
	}

}
