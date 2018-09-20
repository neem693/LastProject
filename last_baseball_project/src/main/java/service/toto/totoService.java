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
	        	"https://www.betman.co.kr/gameSchedule.so?method=basic&gameId=G101&gameRound=180073&innerRound=180073&outerRound=73&saleYear=2018&searchType=S&searchDay=&searchLeague=KBO"
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
  	                     
	     	            System.out.println(date_arr[k].toString().trim());
	     	        
	     	            
	     	            
	     	           String[] date_buffer=date_arr[k].trim().split(" ");
	     	          
	     	            for(int i=0; i<date_buffer.length;i++) {
	     	            	
	     	            	System.out.println(date_buffer[i].toString());
	     	            }
	     	
	
	     	           //JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC
	     	           switch(date_buffer[1]) {     
	     	          case "Jan":  date_buffer[1] = "01"; break;
		     	      case "Feb":  date_buffer[1] = "02"; break;
		     	      case "Mar":  date_buffer[1] = "03"; break;
		     	      case "Apr":  date_buffer[1] = "04"; break;
		     	      case "May":  date_buffer[1] = "05"; break;
		     	      case "Jun":  date_buffer[1] = "06"; break;
		     	      case "Jul":  date_buffer[1] = "07"; break;
		     	      case "Aug":  date_buffer[1] = "08"; break;
		     	      case "Sep":  date_buffer[1] = "09"; break;
	     	          case "Oct":  date_buffer[1] = "10"; break;
	     	          case "Nov":  date_buffer[1] = "11"; break;
	     	          case "Dec":  date_buffer[1] = "12"; break;
	                    default : date_buffer[1] =  "00"; break;
	     	           }
	     	           //play_vo���̺�� p_idx�� ��ġ��Ű������2018			�� 				��			����� 		Ȩ�� 
	     	        
	     	          String buf_date= date_buffer[5]+date_buffer[1]+date_buffer[2]+'_'+away_arr[k]+home_arr[k];
	   
	     	       	  vo.setToto_p_idx(buf_date);     	 
	     	         
	     	       	  vo.setToto_place(date_buffer[8]);        
	        	    	       	    	
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
