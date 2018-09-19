package service.toto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import dao.toto.TotoDaoInterface;
import vo.TotoSchduleVo;

public class totoService implements TotoServiceInterface {

	TotoDaoInterface toto_dao;

	public TotoDaoInterface getToto_dao() {
		return toto_dao;
	}
	public void setToto_dao(TotoDaoInterface toto_dao) {
		this.toto_dao = toto_dao;
	}

	@Override
	public String MakeToToScore() throws IOException {

		System.out.println("���� �Ĺ� �ý��� �۵�");

		//jsoup lib�� ����ؼ� HTML ������ �Ĺ��� �´�.(maven�� ������� �߰���)
		//Document doc=null;;
		     
	      
	        	 //jsoup lib�� �̿��� �ش� url�� ������doc�� �����´�     
	        	Document doc=Jsoup.connect(
	        	"https://www.betman.co.kr/gameSchedule.so?method=basic&gameId=G101&gameRound=180071&innerRound=180071&outerRound=71&saleYear=2018&searchType=S&searchDay=&searchLeague=KBO"
	        			).get();
	        	//�ش��ּҴ� ũ�� �����ڸ�忡�� ������������ ȣ��� �ּҸ� network�������� Ȯ���ؼ� �Է��Ѵ�.(�ּ�â�� ����ȵ�)   	
	        	//System.out.println(doc);//	
	        	//https://m.blog.naver.com/occidere/220851125347 <---�Ľ� ���� ����Ʈ
	        	//���� �ش系�� �ִ� <td> �±� �ؿ� �ִ� <class="home">�±� ���ο� �ִ� <div> �±� ���ο� �ִ� <a>�±װ� 
	        	//�������ִ� text���� ��ȯ�Ѵ�.    	
       	
	        	/*//����
	        	Elements test = doc.select("td.home div a");    
	  	        test.removeAttr("href");   							
	      	    href �Ӽ� �±׸� �����ϴ� �����̴�. �ش��±� ������ �Ӽ��� �����Ѵ�.  
	  	        # id ���� test�� �׸� �����Ͷ�
	        	Elements items = doc.select("#test");
	  	        */
		
	        		doc.select("img").remove(); // doc ���� ���� img �±׸� ��������(������) *����Ʈ���� img�� ������ ��������
	        	
	            	String home = doc.select("td.home div a").text(); //home team
	            	String home_arr[] =home.split(" "); //home team split
	               	     	
	    
	            	String away = doc.select("td.away div a").text(); //away team
	            	String away_arr[] = away.split(" ");  //away split
	            	
	            	
	            	
	            	Elements date_buf = doc.select("td.date");	//split �ϱ� ��ٷο���� �±׿�� �״�� �޾���
	            	date_buf.removeAttr("class");               //split�� ���� class �±� ���� 
	        	    String date=date_buf.toString().replaceAll("<td>"," ").replaceAll("<br>"," ").trim(); 
	        	    String[] date_arr=date.split("</td>"); //��¥ ������ split</td>����
	            	
	        	    String win =  doc.select("td.win").text().replaceAll("-","  0").replaceAll("��"," "); //�����͸� ������ �� ����(split�� �ϱ����� ��Ģ����) 

	        	    String win_arr[] = win.split("   ");  //�����͸� �ڸ��� ���� ������ ����
 	        
	        	    String lose =  doc.select("td.lose").text().replaceAll("-","  0").replaceAll("��"," "); 
	        	    String lose_arr[] = lose.split("   ");
	        	    
	        	    List<TotoSchduleVo> list= new ArrayList<TotoSchduleVo>();        	    
	        	    
	        	
	        	
	        	    for(int k=0; k<win_arr.length; k++) {
	        	    	
	        	   	TotoSchduleVo vo=new TotoSchduleVo();
	        	   		vo.setToto_idx(k+1);
	     	    	   
	        	   		vo.setHome_team(home_arr[k]);
	     	            vo.setHome_team(home_arr[k]);
	     	            vo.setWinner_ratio(win_arr[k]);
		        	    vo.setLost_ratio(lose_arr[k]);   
	     	            vo.setAway_team(away_arr[k]);    	    	
	        	    	vo.setToto_place(date_arr[k].trim());    	        
	        	    	list.add(vo);
	 
	            	}
	        	    	
	        	    Map map = new HashMap<String, List<TotoSchduleVo>>();
	        	   
	        	    map.put("list",list);

	        	    int row=toto_dao.Select_list_row(); //���̺��� ���
    		        	    			//�� �ð����� ���̺��� ���ְ� ���ο� �����͸� ������
	        	    toto_dao.delete_table(); //���� �������ߺ��Է��̹Ƿ� �ʱ�ȭ     	     
	        	    String result=toto_dao.List_insert(map); //������ �����

	        	    System.out.println("���� ������ ������Ʈ �Ϸ�");
	

					// TODO Auto-generated method stub
					return "ok";
	}

	
	
	
	
}
