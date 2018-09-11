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
	
@RequestMapping("/insert_form.do")
public String insert_form()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_insert_form.jsp";
}


@RequestMapping("/file.do")
public String file()
{
return myconst.Myconst.Joonggo.VIEW_PATH + "NewFile.jsp";	
}

@RequestMapping("/list.do")
public String list(String search, String search_text, Model model)
{
	JoonggoVo vo = new JoonggoVo();
	
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

@RequestMapping("/insert.do")
public String insert(JoonggoVo vo, Model model) throws IllegalStateException, IOException
{

	//IP 수신
	String ip = request.getRemoteAddr();
	vo.setJ_ip(ip);
	
	//업로드된 파일 정보 체크
	String web_path = "${pageContext.request.contextPath}/resources/photo_upload/";
	String abs_path = application.getRealPath(web_path);
	
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
    return "redirect : list.do";
	
	
}

}
