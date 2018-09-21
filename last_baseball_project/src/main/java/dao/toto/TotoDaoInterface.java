package dao.toto;

import java.util.List;
import java.util.Map;

import vo.TotoValueVo;

public interface TotoDaoInterface {

	
	String List_insert(Map map); 
	int delete_table();
	int create_table();
	int update(TotoValueVo vo);
	
	List select_gamelist();
	
	
}
