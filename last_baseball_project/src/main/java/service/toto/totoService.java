package service.toto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.springframework.ui.Model;

import dao.toto.TotoDaoInterface;
import vo.TotoValueVo;

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
	        	
			Document game_round=Jsoup.connect("http://www.betman.co.kr/main.so").get();
		   
			String year=game_round.select("#win_0_round").text();	//site���� year������ ������
			String round_num=game_round.select("#outerRound_0").text(); //���� ȸ����ȣ�� 
			String round=game_round.select("td#win_0_name a").attr("href"); //����ȸ����ȣ�� ������	
			String[] round_arr=round.split("=");	
			
			int num=(Integer.parseInt(round_arr[3]))+1;		//���ּҴ� ��ȸ���̹Ƿ� �������� ���Ϸ��� +1ȸ�� ���ش�.
			String r_number=Integer.toString(num);
			round_num= Integer.toString((Integer.parseInt(round_num)+1));
			
			String toto_url="https://www.betman.co.kr/gameSchedule.so?method=basic&gameId=G101&gameRound="+
						     r_number+"&innerRound="+r_number+"&outerRound="+round_num+"&saleYear="+year+
							"&searchType=S&searchDay=&searchLeague=KBO";
			
			Document doc=Jsoup.connect(toto_url).get();
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
	        	    
	        	    List<TotoValueVo> list= new ArrayList<TotoValueVo>();        	    
     	
	        	    for(int k=0; k<win_arr.length; k++) {
	        	    	
	        	    	TotoValueVo vo=new TotoValueVo();
	        	   		vo.setToto_idx(k+1);   	        	   	
	     	            vo.setWinner_ratio(win_arr[k]);
		        	    vo.setLose_ratio(lose_arr[k]);   

	     	           String[] date_buffer=date_arr[k].trim().split(" ");
	     	           		String month_value=date_buffer[1];
	     	           //JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC
	     	          
	     	          if(date_buffer[1].equals("Jan"))date_buffer[1]="01";
	     	          if(date_buffer[1].equals("Feb"))date_buffer[1]="02";
	     	          if(date_buffer[1].equals("Mar"))date_buffer[1]="03";
	     	          if(date_buffer[1].equals("Apr"))date_buffer[1]="04";
	     	          if(date_buffer[1].equals("May"))date_buffer[1]="05";
	     	          if(date_buffer[1].equals("JUN"))date_buffer[1]="06";
	     	          if(date_buffer[1].equals("JUL"))date_buffer[1]="07";
	     	          if(date_buffer[1].equals("Aug"))date_buffer[1]="08";
	     	          if(date_buffer[1].equals("Sep"))date_buffer[1]="09";
	     	          if(date_buffer[1].equals("Oct"))date_buffer[1]="10";
	     	          if(date_buffer[1].equals("Nov"))date_buffer[1]="11";
	     	          if(date_buffer[1].equals("Dec"))date_buffer[1]="12";
	     	        //play_vo���̺�� p_idx�� ��ġ��Ű������2018			�� 				��			����� 		Ȩ�� 
	     	        
	     	          String buf_date= date_buffer[5]+date_buffer[1]+date_buffer[2]+'_'+away_arr[k]+home_arr[k];	   
	     	       	  vo.setP_idx(buf_date);     	        	    	       	    	
	 	  
	     	       	  vo.setToto_place(date_buffer[8]); 	  
	     	       	  list.add(vo);
	 
	     	       	 // System.out.println(date_buffer[8]);
	 	  
	        	    }
	            	  	
	        	    Map map = new HashMap<String, List<TotoValueVo>>();
	        	   
	        	    map.put("list",list);
	        	    
	        	    toto_dao.delete_table(); //���� �������ߺ��Է��̹Ƿ� �ʱ�ȭ     	     
	        	    String result=toto_dao.List_insert(map); //������ �����

	        	    System.out.println("���� ������ ������Ʈ �Ϸ�");
					// TODO Auto-generated method stub
					return "ok";
	}
		@Override
		public List Select_gamelist() {

			List list=toto_dao.select_gamelist();
			return list;
		}
	
		
	
	
	
}
