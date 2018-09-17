package kr.co.pickBaseball;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dao.JoonggoDao;
import util.Paging;
import vo.JoonggoVo;

@Controller
public class JoonggoController {
	
	JoonggoDao joonggo_dao;

public JoonggoDao getJoonggo_dao() {
		return joonggo_dao;
	}

	public void setJoonggo_dao(JoonggoDao joonggo_dao) {
		this.joonggo_dao = joonggo_dao;
	}

	//DispatcherServlet에서 자동 연결
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServletContext application;
	
	@Autowired
	HttpSession session;

	private char[] j_title;
	

/*입력 폼*/
@RequestMapping("/insert_form.do")
public String insert_form()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_insert_form.jsp";
}

/*보기*/ 
@RequestMapping("/view.do")
public String view(int j_idx, Model model)
{
	JoonggoVo vo = joonggo_dao.selectOne(j_idx);
	
	model.addAttribute("vo", vo);
	
	
	return myconst.Myconst.Joonggo.VIEW_PATH + "j_view_form.jsp";
}

/*삭제*/
@RequestMapping("/delete.do")
public String delete(int j_idx)
{
	int res = joonggo_dao.delete(j_idx);
	
	return "redirect:list.do?page=\" + page";
}

/*조회*/
@RequestMapping("/list.do")
public String list(String search, String search_text, Integer page, Model model)
{
	String web_path = "/resources/photo_upload/";
	JoonggoVo vo = new JoonggoVo();
	
	int nowPage = 1;
	
	if(page!=null)
		nowPage = page;
	
	//int start = (nowPage-1) * myconst.Myconst.JoonggoPage.BLOCK_LIST + 1;
	//int end = start + myconst.Myconst.JoonggoPage.BLOCK_LIST -1;
	int start = (nowPage-1) * myconst.Myconst.JoonggoPage.BLOCK_LIST;
	int end  = myconst.Myconst.JoonggoPage.BLOCK_LIST;
	
	Map map = new HashMap();
	map.put("start", start);
	map.put("end", end);
	
	//map.put("start", start);
	//map.put("display", display);
	
	//디버깅 경로 확인
	System.out.println(application.getRealPath(web_path));
	System.out.println(   request.getRequestURI());
    System.out.println(   request.getRequestURL());
	//
	
	
	if(search!=null && !search.equals("all"))
	{
		if(search.equals("title_content"))// 제목 + 내용
		{
			map.put("title", search_text);
			map.put("content", search_text);
		}
		else if(search.equals("title")) //제목
		{
		   map.put("title", search_text);
		}
		else if(search.equals("nick")) // 내용
		{
			map.put("nick", search_text);
	}
	}
	//게시판 목록가져오기
	List<JoonggoVo> list = joonggo_dao.selectList(map);
	//세션에 게시물을 봤냐에 대한정보(show)삭제
	session.removeAttribute("show");
	
	//페이지 메뉴 생성
	int rowTotal = joonggo_dao.selectRowTotal(map); // 전체레코드수
	
	//paging에도 검색조건 넣어서 작성해야됨.
	String pageMenu = Paging.getPaging("list.do", 
			nowPage, 
			rowTotal, 
			search, 
			search_text, 
			myconst.Myconst.JoonggoPage.BLOCK_LIST, 
			myconst.Myconst.JoonggoPage.BLOCK_PAGE);
	
	model.addAttribute("list", list);
	model.addAttribute("pageMenu", pageMenu);
	
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_list.jsp";
}

/*입력*/
@RequestMapping("/insert.do")
public String insert(JoonggoVo vo, Model model) throws IllegalStateException, IOException
{

	//IP 수신
	String ip = request.getRemoteAddr();
	vo.setJ_ip(ip);

	
	//업로드된 파일 정보 체크
	String web_path = "/resources/photo_upload/";
	String abs_path = application.getRealPath(web_path);
	
	System.out.println(abs_path);
	
	String filename = "no_file";
	MultipartFile Filedata = vo.getFiledata();
	if(Filedata.isEmpty()==false)//업로드 화일이 있는 경우
	{
	    //업로드된 화일명을 얻어오기
		filename = Filedata.getOriginalFilename();
		
		// 저장위치및 화일명 설정
		File f = new File(abs_path, filename);
		
		// 설정 경로에 파일 이름이 이미 존재하냐?(중복 체크)
		while(f.exists())
		{
			long tm = System.currentTimeMillis(); //현재 시스템 시간
			filename = String.format("%d_%s", tm,filename);
			f = new File(abs_path, filename);
			
		}
		// 임시화일 => 지정된 위치로 복사
		Filedata.transferTo(f);

	}
	// 업로드된 화일명을 vo넣어준다.
      vo.setJ_filename(filename);
	
	// model통해서 DisptcherServlet에게 데이터를 넘긴다.
	model.addAttribute("vo", vo); // 결과적으로 request binding
	
	//DB insert
    int res = joonggo_dao.insert(vo);
	
	//return MyConstant.PhotoGalleryController.VIEW_PATH + "photo_list.jsp";
	//목록보기이동
	//response.sendRedirect("list.do");
    return "redirect:list.do";
}

/*수정 폼*/
@RequestMapping("/update_form.do")
public String update_form(int j_idx, Model model)
{

	 JoonggoVo vo = joonggo_dao.selectOne(j_idx);
	 
	 model.addAttribute("vo", vo);
	 
	return myconst.Myconst.Joonggo.UPDATA_VIEW_PATH + "j_update_form.jsp";	
	
}

/*수정*/
@RequestMapping("/update.do")
public String updata(JoonggoVo vo, Model model) throws IllegalStateException, IOException
{
	// ip 수신
	String ip = request.getRemoteAddr();
	vo.setJ_ip(ip);
	
	//업로드된 파일 정보 체크
		String web_path = "/resources/photo_upload/";
		String abs_path = application.getRealPath(web_path);
		
		System.out.println(abs_path);
		
		String filename = "no_file";
		MultipartFile Filedata = vo.getFiledata();
		if(Filedata.isEmpty()==false)//업로드 화일이 있는 경우
		{
		    //업로드된 화일명을 얻어오기
			filename = Filedata.getOriginalFilename();
			
			// 저장위치및 화일명 설정
			File f = new File(abs_path, filename);
			
			// 설정 경로에 파일 이름이 이미 존재하냐?(중복 체크)
			while(f.exists())
			{
				long tm = System.currentTimeMillis(); //현재 시스템 시간
				filename = String.format("%d_%s", tm,filename);
				f = new File(abs_path, filename);
				
			}
			// 임시화일 => 지정된 위치로 복사
			Filedata.transferTo(f);

		}
		// 업로드된 화일명을 vo넣어준다.
	      vo.setJ_filename(filename);
		
	 /*  // <br> => \n 수정 : 이유 => textarea에서 엔터처리하려고..  
	  	String content = vo.getJ_content();
		content = content.replaceAll("<br>", "\n");
		vo.setJ_content(content);*/
		
		// model통해서 DisptcherServlet에게 데이터를 넘긴다.
		model.addAttribute("vo", vo); // 결과적으로 request binding
		
		//DB insert
	    int res = joonggo_dao.update(vo);
		
		//return MyConstant.PhotoGalleryController.VIEW_PATH + "photo_list.jsp";
		//목록보기이동
		//response.sendRedirect("list.do");
	    return "redirect:list.do";
	    		
	    		/*"redirect:(String.format(\"view.do?j_idx=%d\", j_idx))";// DispatcherServlet에 전달하면 response.sendRedirect("list.do"); 처리한다.
*/	
}
/*스마트에디터 전송*/
@RequestMapping("/submit")
public void submit(HttpServletRequest request){
System.out.println("에디터 컨텐츠값:" + request.getParameter("editor"));
}


@RequestMapping("/form")
public String form(){
    return "form";
}
     
/**
 * form submit 파일결과 받기
 * @param file
 *//*
@RequestMapping("/getFiledata")
public void getFile(JoonggoVo file){
    System.out.println(file.getFiledata().getOriginalFilename());
}
*/

}
