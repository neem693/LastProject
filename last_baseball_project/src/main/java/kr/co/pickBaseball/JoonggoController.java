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

	private char[] j_title;
	

/*�Է� ��*/
@RequestMapping("/insert_form.do")
public String insert_form()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_insert_form.jsp";
}

/*����*/ 
@RequestMapping("/view.do")
public String view(int j_idx, Model model)
{
	JoonggoVo vo = joonggo_dao.selectOne(j_idx);
	
	model.addAttribute("vo", vo);
	
	
	return myconst.Myconst.Joonggo.VIEW_PATH + "j_view_form.jsp";
}

/*����*/
@RequestMapping("/delete.do")
public String delete(int j_idx)
{
	int res = joonggo_dao.delete(j_idx);
	
	return "redirect:list.do";
}

/*��ȸ*/
@RequestMapping("/list.do")
public String list(String search, String search_text, Model model)
{
	String web_path = "/resources/photo_upload/";
	JoonggoVo vo = new JoonggoVo();
	
	
	//����� ��� Ȯ��
	System.out.println(application.getRealPath(web_path));
	System.out.println(   request.getRequestURI());
    System.out.println(   request.getRequestURL());
	//
	
	
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

/*�Է�*/
@RequestMapping("/insert.do")
public String insert(JoonggoVo vo, Model model) throws IllegalStateException, IOException
{

	//IP ����
	String ip = request.getRemoteAddr();
	vo.setJ_ip(ip);

	
	//���ε�� ���� ���� üũ
	String web_path = "/resources/photo_upload/";
	String abs_path = application.getRealPath(web_path);
	
	System.out.println(abs_path);
	
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
    return "redirect:list.do";
}

/*���� ��*/
@RequestMapping("/update_form.do")
public String update_form(int j_idx, Model model)
{

	 JoonggoVo vo = joonggo_dao.selectOne(j_idx);
	 
	 model.addAttribute("vo", vo);
	 
	return myconst.Myconst.Joonggo.UPDATA_VIEW_PATH + "j_update_form.jsp";	
	
}

/*����*/
@RequestMapping("/update.do")
public String updata(JoonggoVo vo, Model model) throws IllegalStateException, IOException
{
	// ip ����
	String ip = request.getRemoteAddr();
	vo.setJ_ip(ip);
	
	//���ε�� ���� ���� üũ
		String web_path = "/resources/photo_upload/";
		String abs_path = application.getRealPath(web_path);
		
		System.out.println(abs_path);
		
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
		
	   // <br> => \n ���� : ���� => textarea���� ����ó���Ϸ���..  
	  	String content = vo.getJ_content();
		content = content.replaceAll("<br>", "\n");
		vo.setJ_content(content);
		
		// model���ؼ� DisptcherServlet���� �����͸� �ѱ��.
		model.addAttribute("vo", vo); // ��������� request binding
		
		//DB insert
	    int res = joonggo_dao.update(vo);
		
		//return MyConstant.PhotoGalleryController.VIEW_PATH + "photo_list.jsp";
		//��Ϻ����̵�
		//response.sendRedirect("list.do");
	    return "redirect:view.do";
	
}
/*����Ʈ������ ����*/
@RequestMapping("/submit")
public void submit(HttpServletRequest request){
System.out.println("������ ��������:" + request.getParameter("editor"));
}

/*
@RequestMapping("/form")
public String form(){
    return "form";
}*/
     
/**
 * form submit ���ϰ�� �ޱ�
 * @param file
 *//*
@RequestMapping("/getFiledata")
public void getFile(JoonggoVo file){
    System.out.println(file.getFiledata().getOriginalFilename());
}
*/

}
