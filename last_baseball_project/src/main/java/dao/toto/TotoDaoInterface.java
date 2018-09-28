package dao.toto;

import java.util.List;
import java.util.Map;

import vo.TotoSchduleVo;

public interface TotoDaoInterface {

	
	String List_insert(Map map); 
	int Select_list_row();
	int delete_table();
	int create_table();
	int update(TotoSchduleVo vo);
	
}
