package service.normal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import vo.NormalVo;

public interface NormalServiceInterface {

	List getList(Integer page,String nc_search,String nc_search_text);

	int insert(NormalVo vo,HttpServletRequest request,String editor);

	void file_up(HttpServletRequest request);

	NormalVo normal_view(Integer nc_idx, HttpServletRequest request, Model model);
	
	int normal_delete(int nc_idx);
	
	NormalVo normal_modify_form(int nc_idx,NormalVo vo);

	int normal_modify(String nc_title, String nc_contents, NormalVo vo);
}
