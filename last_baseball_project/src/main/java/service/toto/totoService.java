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

		System.out.println("토토 파밍 시스템 작동");

		//jsoup lib를 사용해서 HTML 문서를 파밍해 온다.(maven에 디펜던시 추가함)
		//Document doc=null;;
		     
	      
	        	 //jsoup lib를 이용해 해당 url의 내용을doc로 가져온다     
	        	Document doc=Jsoup.connect(
	        	"https://www.betman.co.kr/gameSchedule.so?method=basic&gameId=G101&gameRound=180071&innerRound=180071&outerRound=71&saleYear=2018&searchType=S&searchDay=&searchLeague=KBO"
	        			).get();
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

	        	    int row=toto_dao.Select_list_row(); //테이블의 행수
    		        	    			//매 시간마다 테이블을 없애고 새로운 데이터를 가져옴
	        	    toto_dao.delete_table(); //원본 데이터중복입력이므로 초기화     	     
	        	    String result=toto_dao.List_insert(map); //데이터 재삽입

	        	    System.out.println("토토 스케쥴 업데이트 완료");
	

					// TODO Auto-generated method stub
					return "ok";
	}

	
	
	
	
}
