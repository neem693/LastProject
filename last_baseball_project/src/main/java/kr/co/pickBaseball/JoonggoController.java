package kr.co.pickBaseball;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dao.JoonggoDao;
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
	
	return "redirect:list.do";
}

/*조회*/
@RequestMapping("/list.do")
public String list(String search, String search_text, Model model)
{
	String web_path = "/resources/photo_upload/";
	JoonggoVo vo = new JoonggoVo();
	
	
	//디버깅 경로 확인
	System.out.println(application.getRealPath(web_path));
	System.out.println(   request.getRequestURI());
    System.out.println(   request.getRequestURL());
	//
	
	
	if(search!=null && search.equals("all"))
	{
		if(search.equals("title_content"))// 이름 + 내용
		{
			vo.setJ_title(search_text);
			vo.setJ_content(search_text);
		}
		else if(search.equals("title")) //이름
		{
			vo.setJ_title(search_text);
		}
		else if(search.equals("nick")) // 내용
		{
			vo.setM_nick(search_text);
		}
	}
	
	List<JoonggoVo> list = joonggo_dao.selectList();
	
	model.addAttribute("list", list);
	
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
		
	   // <br> => \n 수정 : 이유 => textarea에서 엔터처리하려고..  
	  	String content = vo.getJ_content();
		content = content.replaceAll("<br>", "\n");
		vo.setJ_content(content);
		
		// model통해서 DisptcherServlet에게 데이터를 넘긴다.
		model.addAttribute("vo", vo); // 결과적으로 request binding
		
		//DB insert
	    int res = joonggo_dao.update(vo);
		
		//return MyConstant.PhotoGalleryController.VIEW_PATH + "photo_list.jsp";
		//목록보기이동
		//response.sendRedirect("list.do");
	    return "redirect:view.do";
	
}
/*스마트에디터 전송*/
@RequestMapping("/submit")
public void submit(HttpServletRequest request){
System.out.println("에디터 컨텐츠값:" + request.getParameter("editor"));
}

/*
@RequestMapping("/form")
public String form(){
    return "form";
}*/
     
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
