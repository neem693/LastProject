package kr.co.pickBaseball;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import myconst.Myconst;
import vo.JoonggoVo;



@Controller
public class FileUploadController {
	

	

	//�������Ͼ��ε�
	@RequestMapping("/photoUpload")
	public String photoUpload(HttpServletRequest request, JoonggoVo vo){
	    String callback = vo.getCallback();
	    String callback_func = vo.getCallback_func();
	    String file_result = "";
	    try {
	        if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null && !vo.getFiledata().getOriginalFilename().equals("")){
	            //������ �����ϸ�
	            String original_name = vo.getFiledata().getOriginalFilename();
	            String ext = original_name.substring(original_name.lastIndexOf(".")+1);
	            //���� �⺻���
	            String defaultPath = request.getSession().getServletContext().getRealPath("/");
	            //���� �⺻��� _ �󼼰��        ��� : resources\photo_upload 
	            String path = defaultPath + "resources" + File.separator + "photo_upload" + File.separator;             
	            File file = new File(path);
	            System.out.println("path:"+path);
	            //���丮 �������� ������� ���丮 ����
	            if(!file.exists()) {
	                file.mkdirs();
	            }
	            //������ ���ε� �� ���ϸ�(�ѱ۹����� ���� ���������� �ø��� �ʴ°��� ����)
	            String realname = UUID.randomUUID().toString() + "." + ext;
	        ///////////////// ������ ���Ͼ��� /////////////////
	            vo.getFiledata().transferTo(new File(path+realname));
	            /*���� ��ο� �̹����� �����ؾ� �̹����� ��� .�׷���  "http://localhost:9090/pickBaseball/resources/photo_upload/" �䷸�� ������.*/
	            file_result += "&bNewLine=true&sFileName="+original_name+"&sFileURL=http://localhost:9090/pickBaseball/resources/photo_upload/"+realname;
	        	System.out.println(   request.getRequestURI());
	            System.out.println(   request.getRequestURL());
	        } else {
	            file_result += "&errstr=error";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "redirect:" + callback + "?callback_func="+callback_func+file_result;
	}

		
	//�������Ͼ��ε�
	@RequestMapping("/multiplePhotoUpload")
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response){
	    try {
	         //��������
	         String sFileInfo = "";
	         //���ϸ��� �޴´� - �Ϲ� �������ϸ�
	         String filename = request.getHeader("file-name");
	         //���� Ȯ����
	         String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
	         //Ȯ���ڸ��ҹ��ڷ� ����
	         filename_ext = filename_ext.toLowerCase();
	         //���� �⺻���
	         String dftFilePath = request.getSession().getServletContext().getRealPath("/");
	         //���� �⺻��� _ �󼼰�� ��� : resources\photo_upload 
	         String filePath = dftFilePath + "resources" + File.separator + "photo_upload" + File.separator;
	         File file = new File(filePath);
	         if(!file.exists()) {
	            file.mkdirs();
	         }
	         String realFileNm = "";
	         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	         String today= formatter.format(new java.util.Date());
	         realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
	         String rlFileNm = filePath + realFileNm;
	         ///////////////// ������ ���Ͼ��� /////////////////
	         InputStream is = request.getInputStream();
	         OutputStream os=new FileOutputStream(rlFileNm);
	         int numRead;
	         byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	         while((numRead = is.read(b,0,b.length)) != -1){
	            os.write(b,0,numRead);
	         }
	         if(is != null) {
	            is.close();
	         }
	         os.flush();
	         os.close();
	         ///////////////// ������ ���Ͼ��� /////////////////
	         // ���� ���
	         sFileInfo += "&bNewLine=true";
	         // img �±��� title �Ӽ��� �������ϸ����� ��������ֱ� ����
	         sFileInfo += "&sFileName="+ filename;
	         
	         request.getRequestURL();
	      
	         /*���� ��ο� �̹����� �����ؾ� �̹����� ��� .�׷���  "http://localhost:9090/pickBaseball/resources/photo_upload/" �䷸�� ������.*/
	         sFileInfo += "&sFileURL=" + request.getContextPath() + realFileNm;
	         PrintWriter print = response.getWriter();
	         print.print(sFileInfo);
	         print.flush();
	         print.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
