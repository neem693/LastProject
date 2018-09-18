package service.normal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vo.NormalVo;

public interface NormalServiceInterface {

	List getList(Integer page,String nc_search,String nc_search_text);

	int insert(NormalVo vo,HttpServletRequest request,String editor);

	void file_up(HttpServletRequest request);

	
	
	
	

}
