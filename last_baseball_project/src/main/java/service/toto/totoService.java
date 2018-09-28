package service.toto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.springframework.ui.Model;

import dao.toto.TotoDaoInterface;
import vo.TotoValueVo;
import vo.Toto_Game_Vo;

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
			
			int num=(Integer.parseInt(round_arr[3]));		//���ּҴ� ��ȸ���̹Ƿ� �������� ���Ϸ��� +1ȸ�� ���ش�.
			String r_number=Integer.toString(num);
			round_num= Integer.toString((Integer.parseInt(round_num)));

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
	     	            vo.setWinner_ratio(win_arr[k].trim());
		        	    vo.setLose_ratio(lose_arr[k].trim());   
 	   
	     	           String[] date_buffer=date_arr[k].trim().split(" ");
	     	           //String month_value=date_buffer[1];
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
		
		@Override
		public String Make_game(HttpServletRequest request) {
			
			Enumeration param = request.getParameterNames();
		
			String m_id = request.getParameter(param.nextElement().toString());
			String game_number=System.currentTimeMillis()+m_id;
			
			String bat_price= request.getParameter(param.nextElement().toString());
			//ù��°�� m_id �ι�°�� bat_price ����°���� ���õ� ����� ���� �����´�.(�Ѿ�ö� ���ɿ� ^^)
				
			//������ ���� ���� ��ȣ
			while(param.hasMoreElements()){
				Toto_Game_Vo vo= new Toto_Game_Vo();
				vo.setM_id(m_id);
				vo.setGame_number(game_number);
				vo.setBat_price(bat_price);
				
				String p_idx=param.nextElement().toString();
				vo.setP_idx(p_idx);	//p_idx�� Ű���̸� getparameter�� ���� �����´�
					
				String[] game=request.getParameter(p_idx).split(",");			
				
				//System.out.println(game[0]);
				//System.out.println(game[1]);
			
				String ratio = game[0]; //����
				String Bat_win_lose=game[1];//�¸� or �й� �Ȱ�			
				vo.setRatio(ratio);
				vo.setBat_win_lose(Bat_win_lose);		
				vo.setGame_result("T");
				toto_dao.insert_totogame(vo);// ������ ��� ���� ������ �����Ѵ�.
		
			}

			// TODO Auto-generated method stub
			return "ok";
		}
	
		
		
		@Override
		public String[] Game_Result(String m_id) {
			//���� ó������ ���� game�� key���� �ҷ���
		String[] result_key=toto_dao.select_toto_game_key(m_id);
			
	
		for(int y=0; y<result_key.length; y++) {		//����Ű ���
		System.out.println(result_key[y]);
		}
			//��� ó�� ��ƾ 
		
		
		for(int i=0; i<result_key.length;i++) {
			
			String game_num=result_key[i];
			//�ش� key���� ������ ���Ӹ���Ʈ�� �ҷ���(list vo)
			List game_list=toto_dao.select_pick_gamelist(game_num);
				
			//away �������� �¹��и� ���Ѵ�  ���� L�ΰ�� Ȩ�� �¸� W�ΰ�� Ȩ�� �й��Ѵ�
			System.out.println(game_list.size());
				
					for(int k=0; k<game_list.size(); k++) {
					
							//��ó���� ������ �ϳ��� vo�� �����´� ---
						Toto_Game_Vo vo=(Toto_Game_Vo)game_list.get(k);
						
						//������Ʈ�� ��� ����� �÷��� ���̺��� �����´�.p_idx�� ����Ű�̹Ƿ� ��⵵ ���ϳ��� ����
						String game_result = toto_dao.Game_Information(vo.getP_idx());
							  if(!game_result.equals('-')) {		// �����ϰ�� - �׿��� ��� �� �� ��õ���
								  vo.setGame_result(game_result);
								  toto_dao.game_result_update(vo);					  
							  }
		
						System.out.print(vo.getGame_result());
						System.out.println(game_result);
						
						}
			
					//���� ���� �� ���� ��� 
						
					double final_ratio=1;
					int bat_money=0;
						
					for(int j=0; j<game_list.size(); j++) {
						String player_pick;
						String game_pick;
						//����� ���ð� Ȯ��
						Toto_Game_Vo vo=(Toto_Game_Vo)game_list.get(j);
						bat_money=Integer.parseInt(vo.getBat_price());
						if(vo.getBat_win_lose().equals("win")) {
							 player_pick="home_win";
						}
						else  player_pick="home_lose";
						
						//��� ��� �� Ȯ��
					    if(vo.getGame_result().equals("W")) {
					    	 game_pick = "home_lose"; 
					    }
					    else if(vo.getGame_result().equals("��õ���")) {
					    	 game_pick="��õ ���";
					    }
					    
					    else game_pick = "home_win";			
						
					    System.out.print(player_pick);
					    System.out.println(game_pick);
					    
					    //��� �˻� 
						if(player_pick.equals(game_pick)) {	
						final_ratio*=Double.parseDouble(vo.getRatio());
						}
						else {
						System.out.println("���߽���..");	
						final_ratio=0;	
						}
						
					
					
					}
			
					 
				 double total_money=(double)bat_money*final_ratio;
				
				 System.out.printf("���� �����մϴ�.�ѱݾ� %d ���� %d �Դϴ� \n",(int)total_money,(int)final_ratio);
				 //�ش�ݾ��� �ɹ� ���̵� ������Ʈ �ϸ� ����(�̱���) 
			
			
			}
		
		
		return result_key;
		}
		
		
		
		
		
		
		
		
		
	
	
	
}
