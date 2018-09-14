package dao.party;

import java.util.List;

public interface PartyDaoInerface {
	
	List selectList(Object map);
	Object selectOne();	
	Object selectOne(Object map);
	int selectCount();
	int selectCount(Object ob);
	int update(Object ob);
	int insert(Object ob);
	int update_all(Object [] ob);
	int insert_all(Object [] ob);
	int delete();
	
	
	

}
