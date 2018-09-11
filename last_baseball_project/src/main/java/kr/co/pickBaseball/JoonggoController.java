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

	//DispatcherServlet���� �ڵ� ����
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
		if(search.equals("title_content"))// �̸� + ����
		{
			vo.setJ_title(search_text);
			vo.setJ_content(search_text);
		}
		else if(search.equals("title")) //�̸�
		{
			vo.setJ_title(search_text);
		}
		else if(search.equals("nick")) // ����
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

	//IP ����
	String ip = request.getRemoteAddr();
	vo.setJ_ip(ip);
	
	//���ε�� ���� ���� üũ
	String web_path = "${pageContext.request.contextPath}/resources/photo_upload/";
	String abs_path = application.getRealPath(web_path);
	
	String filename = "no_file";
	MultipartFile Filedata = vo.getFiledata();
	if(Filedata.isEmpty()==false)//���ε� ȭ���� �ִ� ���
	{
	    //���ε�� ȭ�ϸ��� ������
		filename = Filedata.getOriginalFilename();
		
		// ������ġ�� ȭ�ϸ� ����
		File f = new File(abs_path, filename);
		
		// ���� ��ο� ���� �̸��� �̹� �����ϳ�?(�ߺ� üũ)
		while(f.exists())
		{
			long tm = System.currentTimeMillis(); //���� �ý��� �ð�
			filename = String.format("%d_%s", tm,filename);
			f = new File(abs_path, filename);
			
		}
		// �ӽ�ȭ�� => ������ ��ġ�� ����
		Filedata.transferTo(f);

	}
	// ���ε�� ȭ�ϸ��� vo�־��ش�.
	vo.setJ_filename(filename);
	
	// model���ؼ� DisptcherServlet���� �����͸� �ѱ��.
	model.addAttribute("vo", vo); // ��������� request binding
	
	//DB insert
    int res = joonggo_dao.insert(vo);
	
	//return MyConstant.PhotoGalleryController.VIEW_PATH + "photo_list.jsp";
	//��Ϻ����̵�
	//response.sendRedirect("list.do");
    return "redirect : list.do";
	
	
}

}
