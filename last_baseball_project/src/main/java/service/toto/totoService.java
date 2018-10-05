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
import vo.MemberVo;
import vo.My_Game_Result_Vo;
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

		System.out.println("토토 파밍 시스템 작동");

		//jsoup lib를 사용해서 HTML 문서를 파밍해 온다.(maven에 디펜던시 추가함)
		//Document doc=null;;

	    //jsoup lib를 이용해 해당 url의 내용을doc로 가져온다     
	        	
			Document game_round=Jsoup.connect("http://www.betman.co.kr/main.so").get();
		   
			String year=game_round.select("#win_0_round").text();	//site에서 year정보를 가져옴
			String round_num=game_round.select("#outerRound_0").text(); //게임 회차번호를 
			String round=game_round.select("td#win_0_name a").attr("href"); //고유회차번호를 가져옴	
			String[] round_arr=round.split("=");	
			
			int num=(Integer.parseInt(round_arr[3]));		//위주소는 전회차이므로 현재차를 구하려면 +1회차 해준다.
			String r_number=Integer.toString(num);
			round_num= Integer.toString((Integer.parseInt(round_num)));

			String toto_url="https://www.betman.co.kr/gameSchedule.so?method=basic&gameId=G101&gameRound="+
						     r_number+"&innerRound="+r_number+"&outerRound="+round_num+"&saleYear="+year+
							"&searchType=S&searchDay=&searchLeague=KBO";
			
			Document doc=Jsoup.connect(toto_url).get();
	        	//해당주소는 크롬 개발자모드에서 웹페이지상의 호출된 주소를 network영역에서 확인해서 입력한다.(주소창엔 노출안됨)   	
	        	//System.out.println(doc);//	
	        	//https://m.blog.naver.com/occidere/220851125347 <---파싱 참고 사이트
	        	//사용법 해당내에 있는 <td> 태그 밑에 있는 <class="home">태그 내부에 있는 <div> 태그 내부에 있는 <a>태그가 
	        	//가지고있는 text값을 반환한다.    	
       	
	        	/*//예제
	        	Elements test = doc.select("td.home div a");    
	  	        test.removeAttr("href");   							
	      	    href 속성 태그를 제거하는 예제이다. 해당태그 내부의 속성을 제거한다.  
	  	        # id 명이 test인 항목만 가져와라
	        	Elements items = doc.select("#test");
	  	        */
		
	        		doc.select("img").remove(); // doc 문서 내에 img 태그를 전부제거(사용안함) *데이트값에 img가 가끔씩 섞여들어옴
	        	
	            	String home = doc.select("td.home div a").text(); //home team
	            	String home_arr[] =home.split(" "); //home team split
	               	     	
	    
	            	String away = doc.select("td.away div a").text(); //away team
	            	String away_arr[] = away.split(" ");  //away split
	            	
	            	
	            	
	            	Elements date_buf = doc.select("td.date");	//split 하기 까다로운구조라 태그요소 그대로 받았음
	            
	            	date_buf.removeAttr("class");               //split을 위해 class 태그 제거 
	        	    String date=date_buf.toString().replaceAll("<td>"," ").replaceAll("<br>"," ").trim(); 
	        	    String[] date_arr=date.split("</td>"); //날짜 데이터 split</td>기준	      
	        	    String win =  doc.select("td.win").text().replaceAll("-","  0").replaceAll("승"," "); //데이터를 제외한 값 제거(split을 하기위한 규칙생성) 
	        	    String win_arr[] = win.split("   ");  //데이터를 자르기 위해 간격을 조절
 	      	        String lose =  doc.select("td.lose").text().replaceAll("-","  0").replaceAll("패"," "); 
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
	     	        //play_vo테이블과 p_idx를 일치시키기위해2018			월 				일			어웨이 		홈팀 
	     	        
	     	          String buf_date= date_buffer[5]+date_buffer[1]+date_buffer[2]+'_'+away_arr[k]+home_arr[k];	   
	     	       	  vo.setP_idx(buf_date);     	        	    	       	    	
	 	  
	     	       	  vo.setToto_place(date_buffer[8]); 	  
	     	       	  list.add(vo);
	 	    }
	            	  	
	        	    Map map = new HashMap<String, List<TotoValueVo>>();
	        	   
	        	    map.put("list",list);
	        	    
	        	    toto_dao.delete_table(); //원본 데이터중복입력이므로 초기화     	     
	        	    String result=toto_dao.List_insert(map); //데이터 재삽입
  	  
					// TODO Auto-generated method stub
					return "토토 게임정보 업데이트 완료";
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
			
			param.nextElement().toString();//m_idx 값 통과
			
			String bat_price= request.getParameter(param.nextElement().toString());
			//첫번째는 m_id 두번째는 bat_price 세번째부터 배팅된 경기의 값을 가져온다.(넘어올때 글케옴 ^^)
				
			//생성된 게임 고유 번호
			while(param.hasMoreElements()){
				
				
				Toto_Game_Vo vo= new Toto_Game_Vo();
				vo.setM_id(m_id);
				vo.setGame_number(game_number);
				vo.setBat_price(bat_price);
				
				String p_idx=param.nextElement().toString();
				vo.setP_idx(p_idx);	//p_idx가 키값이며 getparameter로 값을 가져온다
					
				String[] game=request.getParameter(p_idx).split(",");			
				
			
			
				String ratio = game[0]; //배당률
				String Bat_win_lose=game[1];//승리 or 패배 픽값			
				vo.setRatio(ratio);
				vo.setBat_win_lose(Bat_win_lose);		
				vo.setGame_result("T");
				toto_dao.insert_totogame(vo);// 배팅한 모든 게임 정보를 저장한다.
		
			}
			
			// TODO Auto-generated method stub
			return "ok";
		}

		
		
		
		
		
		
		
		@Override
		public String[] Game_Result(String m_id) {
			//아직 처리되지 않은 game의 key값을 불러옴
			
		String[] game_id = toto_dao.select_toto_game_id();
			
		for(int id=0; id<game_id.length; id++) {	
					
					String[] result_key=toto_dao.select_toto_game_key(game_id[id]);
					for(int y=0; y<result_key.length; y++) {		//게임키 출력
					System.out.println(result_key[y]);
					}
						//결과 처리 루틴 
		
					for(int i=0; i<result_key.length;i++) {
						
						String game_num=result_key[i];
						//해당 key값을 가지는 게임리스트를 불러옴(list vo)
						List game_list=toto_dao.select_pick_gamelist(game_num);
							
						//away 기준으로 승무패를 정한다  값이 L인경우 홈이 승리 W인경우 홈이 패배한다
						System.out.printf("현재게임의 행수:%d\n",game_list.size());
							
								for(int k=0; k<game_list.size(); k++) {
								
										//미처리된 게임의 하나의 vo를 가져온다 ---
									Toto_Game_Vo vo=(Toto_Game_Vo)game_list.get(k);
									
									//업데이트된 경기 결과를 플레이 테이블에서 가져온다.p_idx가 유일키이므로 경기도 단하나만 존재
									String game_result = toto_dao.Game_Information(vo.getP_idx());
									
									if(!game_result.equals('-')) {		// 미정이나 예정일경우 - 그외의 경우 승 패 우천취소
											  vo.setGame_result(game_result);
											  toto_dao.game_result_update(vo);					  
										  }
									
									}
					
							//게임 적중 및 실패 계산 
								
							double final_ratio=1;
							int bat_money=0;
								
							for(int j=0; j<game_list.size(); j++) {
								String player_pick;
								String game_pick;
								//사용자 배팅값 확인
								Toto_Game_Vo vo=(Toto_Game_Vo)game_list.get(j);
								if(!vo.getGame_result().equals("-")) {
										
										bat_money=Integer.parseInt(vo.getBat_price());
										if(vo.getBat_win_lose().equals("win")) {
											 player_pick="home_win";
										}
										else  player_pick="home_lose";
										
										//경기 결과 값 확인
									    if(vo.getGame_result().equals("W")) {
									    	 game_pick = "home_lose"; 
									    }
									    else if(vo.getGame_result().equals("우천취소")) {
									    	 game_pick="우천 취소";
									    }
									    else if(vo.getGame_result().equals("D")) {
									    	 game_pick="DROW";
									    } 
									    else game_pick = "home_win";			
										
									    
									    System.out.printf("플레이어 배팅:%s",player_pick);
									    System.out.printf("게임 결과%s\n",game_pick);
									    
									    //존나 희박한 확률로 비겻을경우
									    if(vo.getGame_result().equals("D")) {
									    	 game_pick = "home_lose"; 
									    	 final_ratio*=1;
									    }
									    //결과 검사 
										if(player_pick.equals(game_pick)) {	
										final_ratio*=Double.parseDouble(vo.getRatio());
										}
										else{
											
											if(game_pick.equals("우천취소")|game_pick.equals("D")) {
											final_ratio=1;
											System.out.println("무승부 및 무효처리");	
											}
											else { final_ratio=0;	
											System.out.println("적중실패..");	
											}
										}
										//해당게임은 계산이 완료되었으므로 X표시해준다.	
										
										if(!vo.getGame_result().equals("-")) {//-표시값은 아직 정해진 경기가 아니므로 상태값을 기존의값로 유지한다.
										vo.setGame_result("X");
										toto_dao.remove_game_result(vo);
										}
										
									}
					
						}	 
						 double total_money=(double)bat_money*final_ratio;
						
						 System.out.printf("적중 축하합니다.총금액 %d 배당률 %f 입니다 \n",(int)total_money,final_ratio);
						 //해당금액을 맴버 아이디에 업데이트 하면 종료(미구현) 
						 
						 My_Game_Result_Vo my_game_vo=new My_Game_Result_Vo();
						 my_game_vo.setFinal_ratio(Double.toString(final_ratio));
						 my_game_vo.setGame_number(game_num);
						 my_game_vo.setTotal_money(Double.toString(total_money));						 
						 my_game_vo.setM_id(game_id[id]);
						 //지난 게임결과 등록  
						 
						 toto_dao.my_game_result_insert(my_game_vo);
						 
					}
		
			
		}
			
		return null;
	
		
	}
		
		@Override
		public int update_money(MemberVo vo) {
			
			toto_dao.toto_member_money_update(vo);
		
			return 0;
		}
		

		@Override
		public List my_bat_gamelist(MemberVo vo) {
			//내가 배팅한 게임 정보 갯
			List list=toto_dao.my_bat_gamelist(vo);

			return list;
		}
		@Override
		
		public MemberVo my_money_read(MemberVo vo) {
			// TODO Auto-generated method stub
			
			MemberVo voo = toto_dao.my_money_read(vo);
			
			return voo;
		}
		
		@Override
		public List my_game_result(String m_id) {
			
			List list= toto_dao.my_game_list(m_id);
			
			// TODO Auto-generated method stub
			return list;
		}
		
		
		
		

	
}
