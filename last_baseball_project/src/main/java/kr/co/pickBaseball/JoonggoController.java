package kr.co.pickBaseball;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64.Decoder;
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

import dao.CommentDao;
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

	//DispatcherServlet���� �ڵ� ����
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServletContext application;
	
	@Autowired
	HttpSession session;



	

/*�Է� ��*/
@RequestMapping("/joonggo/insert_form.do")
public String insert_form()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_insert_form.jsp";
}

/*�ǸſϷ�*/
@RequestMapping("/joonggo/sell.do")
public String sell(JoonggoVo vo)
{
	/*int idx = Integer.parseInt(request.getParameter("idx"));
	String c_pwd = request.getParameter("c_pwd");// Ȯ�� ���
*/	/*System.out.println("["+ c_pwd + "]");*/
	
	//idx�� �ش�Ǵ� �Խù� ���� ���
//	JoonggoVo vo = joonggo_dao.selectOne(j_idx);
	
	String result = "n";//
	
	// �Ǹ� üũ
	// üũ��ġ : yes, ����ġ : no
//	if(vo.getJ_sell_yn().equals(result)==true)
//	{
//		result = "y";
//	}
//	
	
	int res = joonggo_dao.sell(vo);
	if(res !=1)
		return "redirect:/joonggo/view.do?j_idx="+vo.getJ_idx() + "&fail=update_sellFail";
	
	// ��û�� Ajax���� ��� ����
/*	response.getWriter().println(result);*/
	
	return "redirect:/joonggo/view.do?j_idx="+vo.getJ_idx();

}
/*����*/ 
@RequestMapping("/joonggo/view.do")
public String view(int j_idx, Model model)
{
	JoonggoVo vo = joonggo_dao.selectOne(j_idx);
	
	session = request.getSession();
	if(session.getAttribute("show")==null)
	{
		int res = joonggo_dao.readhits(j_idx);
	}
	vo.setC_count(joonggo_dao.commentDaoTotal(j_idx));
	
	model.addAttribute("vo", vo);
	model.addAttribute("show", true);
/*	
	try {
		System.out.println( URLDecoder.decode(vo.getJ_content(),"utf-8") );
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_view.jsp";
}

/*����*/
@RequestMapping("/joonggo/delete.do")
public String delete(int j_idx, Integer page)
{
	int res = joonggo_dao.delete(j_idx);
	
	return "redirect:list.do?page=" + page;
}

/*��ȸ*/
@RequestMapping("/joonggo/list.do")
public String list(String search, String search_text, Integer page, Model model)
{
	String web_path = "/resources/photo_upload/";
	JoonggoVo vo = new JoonggoVo();
	
	int nowPage = 1;
	
	if(page!=null)
		nowPage = page;
	
	int start = (nowPage-1) * myconst.Myconst.JoonggoPage.BLOCK_LIST;
	int end  = myconst.Myconst.JoonggoPage.BLOCK_LIST;
	
	Map map = new HashMap();
	map.put("start", start);
	map.put("end", end);

	//����� ��� Ȯ��
	System.out.println(application.getRealPath(web_path));
	System.out.println(   request.getRequestURI());
    System.out.println(   request.getRequestURL());

	
	if(search!=null && !search.equals("all"))
	{
		if(search.equals("title_content"))// ���� + ����
		{
			map.put("title", search_text);
			map.put("content", search_text);
		}
		else if(search.equals("content")) //����
		{
		   map.put("content", search_text);
		}
		else if(search.equals("title")) //����
		{
		   map.put("title", search_text);
		}
		else if(search.equals("nick")) // �г���
		{
			map.put("nick", search_text);
	}
	}
	//�Խ��� ��ϰ�������
	List<JoonggoVo> list = joonggo_dao.selectList(map);
	//���ǿ� �Խù��� �óĿ� ��������(show)����
	session.removeAttribute("show");
	
	//������ �޴� ����
	int rowTotal = joonggo_dao.selectRowTotal(map); // ��ü���ڵ��
	
	//paging���� �˻����� �־ �ۼ��ؾߵ�.
	String pageMenu = Paging.getPaging("list.do", 
			nowPage, 
			rowTotal, 
			search, 
			search_text, 
			myconst.Myconst.JoonggoPage.BLOCK_LIST, 
			myconst.Myconst.JoonggoPage.BLOCK_PAGE
			);
	
	//��������� request binding
	model.addAttribute("list", list);
	model.addAttribute("pageMenu", pageMenu);
	
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_list.jsp";
}

/*�Է�*/
@RequestMapping("/joonggo/insert.do")
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
@RequestMapping("/joonggo/update_form.do")
public String update_form(int j_idx, Model model)
{

	 JoonggoVo vo = joonggo_dao.selectOne(j_idx);
	 
	 model.addAttribute("vo", vo);
	 
	return myconst.Myconst.Joonggo.UPDATA_VIEW_PATH + "joonggo_update_form.jsp";	
	
}

/*����*/
@RequestMapping("/joonggo/update.do")
public String updata(JoonggoVo vo, Model model) throws IllegalStateException, IOException
{
	// ip ����
	String ip = request.getRemoteAddr();
	vo.setJ_ip(ip);
	
	//���ε�� ���� ���� üũ
		String web_path = "/resources/photo_upload/";
		String abs_path = application.getRealPath(web_path);
		
		//System.out.println(abs_path);
		
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
		
	 /*  // <br> => \n ���� : ���� => textarea���� ����ó���Ϸ���..  
	  	String content = vo.getJ_content();
		content = content.replaceAll("<br>", "\n");
		vo.setJ_content(content);*/
		
		// model���ؼ� DisptcherServlet���� �����͸� �ѱ��.
		model.addAttribute("vo", vo); // ��������� request binding
		
		//DB insert
	    int res = joonggo_dao.update(vo);
		
		//return MyConstant.PhotoGalleryController.VIEW_PATH + "photo_list.jsp";
		//��Ϻ����̵�
		//response.sendRedirect("list.do");
	    return "redirect:list.do";
	    		
	    		/*"redirect:(String.format(\"view.do?j_idx=%d\", j_idx))";// DispatcherServlet�� �����ϸ� response.sendRedirect("list.do"); ó���Ѵ�.
*/	
}
/*����Ʈ������ ����*/
@RequestMapping("/submit")
public void submit(HttpServletRequest request){
System.out.println("������ ��������:" + request.getParameter("editor"));
}


}
