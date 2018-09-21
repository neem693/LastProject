package service.member;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import dao.member.MemberDaoInterface;
import vo.MemberVo;

public class MemberService implements MemberServiceInterface {
	//controller에서 호출해서 service단에서 데이터 처리및 연산 dao단에서 데이터에 접근 획득으로 
	//3개 구조로 분리해서 구축하는게 정석이다.
	
	MemberDaoInterface member_dao;


	public MemberDaoInterface getMember_dao() {
		return member_dao;
	}

	public void setMember_dao(MemberDaoInterface member_dao) {
		this.member_dao = member_dao;
	}

	@Override
	public List selectList() {

		List<MemberVo> list;

		list = member_dao.selectList();

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public String selectOne(String m_id) {
		
		String result="no";
		MemberVo vo=member_dao.selectOne(m_id);
		
		if(vo==null)result="yes"; //id가 일치하는 객체를 가져왔으나 일치하지 않을경우 null을 반환한다.
		
		String result_json = String.format("[{'result':'%s'}]",result); //제이슨 배열로 보내는것이 가장 안전하다.
		return result_json;

	}

	
	
	@Override
	public String photo_upload(MultipartHttpServletRequest multi) {
		
		//실제 파일명
		String real_filename="";
		
		//웹상의 사진이 올라갈 경로
		String root=multi.getSession().getServletContext().getRealPath("/");  
		String webPath =root+"resources/photo_image/"; 		
	   //C:\My_Study\WEB_Study\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\pickBaseball\
	
		//파일명이 중복되면 추상적인 이름을 가진 폴더를 생성해서 만든다.
		 File dir = new File(webPath);
	        if(!dir.isDirectory()){
	            dir.mkdir();
	        }

	        
	     Iterator<String> file_name = multi.getFileNames(); 
	     //해당파일의 네임을 가져온다. 해당방식의 변수로 받게되어있다.(그렇게되있음)

	     while(file_name.hasNext()){
	            String uploadFile = file_name.next();
	                         
	            MultipartFile mFile = multi.getFile(uploadFile);
	            //해당 객체를이용에 어플리케이션 영역에 올라간 이미지를 가져온다.
	            String fileName = mFile.getOriginalFilename();
	            System.out.println("실제 파일 이름 : " +fileName);
	            //실제 파일명을 가져온다.
	            
	       /*     real_filename = System.currentTimeMillis()+"."
	                    +fileName.substring(fileName.lastIndexOf(".")+1);*/    
	            real_filename = System.currentTimeMillis()+fileName;
	            //파일명에 저장된 시간과 함께 실제 저장될 파일명 생성	
	         
	            try {
	                mFile.transferTo(new File(webPath+real_filename));
	              //실제 경로에 파일을 생성한다. (저장될경로 + 실제 파일네임)
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	         
		//저장경로
		//System.out.println(webPath);
		
		String result= "/pickBaseball/resources/photo_image/" + real_filename;
		
		return result;
	}

	@Override
	public String selectOne(Map map) {
	
		String result="no";
		MemberVo vo=member_dao.selectOne(map);
		
		if(vo==null)result="yes"; //id가 일치하는 객체를 가져왔으나 일치하지 않을경우 null을 반환한다.
		
		String result_json = String.format("[{'result':'%s'}]",result); //제이슨 배열로 보내는것이 가장 안전하다.
		return result_json;
	}

	@Override
	public MemberVo selectOne(int m_idx) {
		
		return member_dao.selectOne(m_idx);
	}

	
	@Override
	public int update(MemberVo vo) {

		member_dao.update(vo);
		return 0;
	}

	@Override
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		int res;
	
	
		res=member_dao.insert(vo);
		
		return  res;
	}

	@Override
	public int delete(int m_idx) {
		
		int res;
		res=member_dao.delete(m_idx);	
		return res;
	}

	@Override
	public MemberVo login_action(MemberVo vo) {
		// TODO Auto-generated method stub
		
		
		MemberVo voo = member_dao.selectOne_login(vo);
		
		
		return voo;
	}

	
}
