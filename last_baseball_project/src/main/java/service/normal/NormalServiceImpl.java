package service.normal;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import dao.normal.NormalDaoInterface;
import myconst.Myconst;
import myconst.Paging;
import vo.NormalVo;

public class NormalServiceImpl implements NormalServiceInterface{
	
	NormalDaoInterface normal_dao;
	
	
	public NormalDaoInterface getNormal_dao() {
		return normal_dao;
	}
	public void setNormal_dao(NormalDaoInterface normal_dao) {
		this.normal_dao = normal_dao;
	}
	
	@Autowired
	HttpSession session;
	
	@Override
	public List getList(Integer page,String nc_search,String nc_search_text) {
		// TODO Auto-generated method stub
		
		int nowPage = 1;
		if(page!= null)
			nowPage = page;
		
		int start = (nowPage-1) * Myconst.NormalPageing.BLOCK_LIST+ 1;
		int display = start + Myconst.NormalPageing.BLOCK_LIST -1;
		
		Map map = new HashMap();
		
		map.put("start", start);
		map.put("display", display);
		
		if(nc_search!=null && !nc_search.equals("all")) {
			if(nc_search.equals("title_nick_contents")) {
				map.put("nc_title", nc_search_text);
				map.put("m_nick", nc_search_text);
				map.put("nc_contents", nc_search_text);
			}else if(nc_search.equals("nc_title")) {
				map.put("nc_title", nc_search_text);
			}else if(nc_search.equals("m_nick")) {
				map.put("m_nick", nc_search_text);
			}else if(nc_search.equals("nc_contents")) {
				map.put("nc_contents", nc_search_text);
			}
		}
		
		List<NormalVo> list = normal_dao.selectList();
		
		//session.removeAttribute("show");
		
		//int rowTotal = normal_dao.selctRowTotal(map);
		
		//String pageMenu = Paging.getNormalPaging("list.do", nowPage, rowTotal, Myconst.NormalPageing.BLOCK_LIST, Myconst.NormalPageing.BLOCK_PAGE);
		
		return list;
	}
	@Override
	public int insert(NormalVo vo,HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String nc_ip = request.getRemoteAddr();//요청자 IP
		
		vo.setNc_ip(nc_ip);
		
		int res = normal_dao.insert(vo);
		
		return res;
	}
	
	@Override
	public void file_up(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		StringBuffer sb = new StringBuffer();
		try {
			//파일명을 받는다 - 일반 원본 파일명
			String oldName = request.getHeader("file-name");
			//파일 기본경로_상세경로
			String filePath = "C:\\My_study\\mywork\\LastProject\\last_baseball_project\\src\\main\\webapp\\resources\\editor\\photoUp\\";
			String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss")
					.format(System.currentTimeMillis()))
					.append(UUID.randomUUID().toString())
					.append(oldName.substring(oldName.lastIndexOf("."))).toString();
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(filePath + saveName);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while ((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			os.flush();
			os.close();
			//정보출력
			sb = new StringBuffer();
			sb.append("&bNewLine=true")
            .append("&sFileName=").append(oldName)
            .append("&sFileURL=").append("http://localhost:8090/Spring/resources/photoUpload/")
      .append(saveName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@Override
	public NormalVo normal_view(Integer nc_idx,HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		NormalVo vo = normal_dao.selectOne(nc_idx);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("show")==null) {
			int res = normal_dao.update_views(nc_idx);
			session.setAttribute("show", true);
		}
		
		return vo;
	}
	
	@Override
	public int normal_delete(int nc_idx) {
		// TODO Auto-generated method stub
		
		int res = normal_dao.delete(nc_idx);
		
		return res;
	}
	
	@Override
	public NormalVo normal_modify_form(int nc_idx,NormalVo vo) {
		// TODO Auto-generated method stub
		
		vo = normal_dao.selectOne(nc_idx);
		
		return vo;
	}
	@Override
	public int normal_modify(String nc_title, String nc_contents, NormalVo vo) {
		// TODO Auto-generated method stub
		
		int res = normal_dao.update(vo);
		
		return res;
	}
	
	
}
