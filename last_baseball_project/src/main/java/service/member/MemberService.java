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
	//controller���� ȣ���ؼ� service�ܿ��� ������ ó���� ���� dao�ܿ��� �����Ϳ� ���� ȹ������ 
	//3�� ������ �и��ؼ� �����ϴ°� �����̴�.
	
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
		
		if(vo==null)result="yes"; //id�� ��ġ�ϴ� ��ü�� ���������� ��ġ���� ������� null�� ��ȯ�Ѵ�.
		
		String result_json = String.format("[{'result':'%s'}]",result); //���̽� �迭�� �����°��� ���� �����ϴ�.
		return result_json;

	}

	
	
	@Override
	public String photo_upload(MultipartHttpServletRequest multi) {
		
		//���� ���ϸ�
		String real_filename="";
		
		//������ ������ �ö� ���
		String root=multi.getSession().getServletContext().getRealPath("/");  
		String webPath =root+"resources/photo_image/"; 		
	   //C:\My_Study\WEB_Study\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\pickBaseball\
	
		//���ϸ��� �ߺ��Ǹ� �߻����� �̸��� ���� ������ �����ؼ� �����.
		 File dir = new File(webPath);
	        if(!dir.isDirectory()){
	            dir.mkdir();
	        }

	        
	     Iterator<String> file_name = multi.getFileNames(); 
	     //�ش������� ������ �����´�. �ش����� ������ �ްԵǾ��ִ�.(�׷��Ե�����)

	     while(file_name.hasNext()){
	            String uploadFile = file_name.next();
	                         
	            MultipartFile mFile = multi.getFile(uploadFile);
	            //�ش� ��ü���̿뿡 ���ø����̼� ������ �ö� �̹����� �����´�.
	            String fileName = mFile.getOriginalFilename();
	            System.out.println("���� ���� �̸� : " +fileName);
	            //���� ���ϸ��� �����´�.
	            
	       /*     real_filename = System.currentTimeMillis()+"."
	                    +fileName.substring(fileName.lastIndexOf(".")+1);*/    
	            real_filename = System.currentTimeMillis()+fileName;
	            //���ϸ� ����� �ð��� �Բ� ���� ����� ���ϸ� ����	
	         
	            try {
	                mFile.transferTo(new File(webPath+real_filename));
	              //���� ��ο� ������ �����Ѵ�. (����ɰ�� + ���� ���ϳ���)
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	         
		//������
		//System.out.println(webPath);
		
		String result= "/pickBaseball/resources/photo_image/" + real_filename;
		
		return result;
	}

	@Override
	public String selectOne(Map map) {
	
		String result="no";
		MemberVo vo=member_dao.selectOne(map);
		
		if(vo==null)result="yes"; //id�� ��ġ�ϴ� ��ü�� ���������� ��ġ���� ������� null�� ��ȯ�Ѵ�.
		
		String result_json = String.format("[{'result':'%s'}]",result); //���̽� �迭�� �����°��� ���� �����ϴ�.
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
