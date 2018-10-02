
package util;
/*
        pageURL:?럹?씠吏뺤쿂由ы븷 由ъ뒪?듃?럹?씠吏?      
        nowPage:?쁽?옱?럹?씠吏?
        rowTotal:?쟾泥대뜲?씠?꽣媛??닔
        blockList:?븳?럹?씠吏??떦 寃뚯떆臾쇱닔
        blockPage:?븳?솕硫댁뿉 ?굹???궪 ?럹?씠吏? 紐⑸줉?닔
 */
public class Paging {
	public static String getPaging(
			String pageURL, // /board/list.do
			int nowPage, 
			int rowTotal,
			int blockList, 
			int blockPage){
		
		int totalPage/*?쟾泥댄럹?씠吏??닔*/,
            startPage/*?떆?옉?럹?씠吏?踰덊샇*/,
            endPage;/*留덉?留됲럹?씠吏?踰덊샇*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //紐⑤뱺 ?긽?솴?쓣 ?뙋?떒?븯?뿬 HTML肄붾뱶瑜? ???옣?븷 怨?
		
		
		isPrevPage=isNextPage=false;
		//?엯?젰?맂 ?쟾泥? ?옄?썝?쓣 ?넻?빐 ?쟾泥? ?럹?씠吏? ?닔瑜? 援ы븳?떎..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//留뚯빟 ?옒紐삳맂 ?뿰?궛怨? ??吏곸엫?쑝濡? ?씤?븯?뿬 ?쁽?옱 ?럹?씠吏? ?닔媛? ?쟾泥? ?럹?씠吏? ?닔瑜?
		//?꽆?쓣 寃쎌슦 媛뺤젣濡? ?쁽?옱?럹?씠吏? 媛믪쓣 ?쟾泥? ?럹?씠吏? 媛믪쑝濡? 蹂?寃?
		if(nowPage > totalPage)nowPage = totalPage;
		

		//?떆?옉 ?럹?씠吏??? 留덉?留? ?럹?씠吏?瑜? 援ы븿.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//留덉?留? ?럹?씠吏? ?닔媛? ?쟾泥댄럹?씠吏??닔蹂대떎 ?겕硫? 留덉?留됲럹?씠吏? 媛믪쓣 蹂?寃?
		if(endPage > totalPage)endPage = totalPage;
		
		//留덉?留됲럹?씠吏?媛? ?쟾泥댄럹?씠吏?蹂대떎 ?옉?쓣 寃쎌슦 ?떎?쓬 ?럹?씠吏뺤씠 ?쟻?슜?븷 ?닔 ?엳?룄濡?
		//boolean?삎 蹂??닔?쓽 媛믪쓣 ?꽕?젙
		if(endPage < totalPage) isNextPage = true;
		//?떆?옉?럹?씠吏??쓽 媛믪씠 1蹂대떎 ?옉?쑝硫? ?씠?쟾?럹?씠吏? ?쟻?슜?븷 ?닔 ?엳?룄濡? 媛믪꽕?젙
		if(startPage > 1)isPrevPage = true;
		
		//HTML肄붾뱶瑜? ???옣?븷 StringBuffer?깮?꽦=>肄붾뱶?깮?꽦
		sb = new StringBuffer();
//-----洹몃９?럹?씠吏?泥섎━ ?씠?쟾 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a href ='"+pageURL+"?page=");
			sb.append(startPage-1); // nowPage-blockPage
			sb.append("'>??</a>");
		}
		else
			sb.append("??");
		
//------?럹?씠吏? 紐⑸줉 異쒕젰 -------------------------------------------------------------------------------------------------
		sb.append("|");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //?쁽?옱 ?엳?뒗 ?럹?씠吏?
				sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");
			}
			else{//?쁽?옱 ?럹?씠吏?媛? ?븘?땲硫?
				sb.append("&nbsp;<a href='"+pageURL+"?page=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}// end for
		
		sb.append("&nbsp;");
		
//-----洹몃９?럹?씠吏?泥섎━ ?떎?쓬 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a href='"+pageURL+"?page=");

			sb.append(endPage + 1);
			
			sb.append("'>?뼳</a>");
		}
		else
			sb.append("?뼳");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
	
	
	public static String getPaging(
			String pageURL, // /board/list.do
			int nowPage, 
			int rowTotal,
			String search,
			String search_text,
			int blockList, 
			int blockPage){
		
		int totalPage/*?쟾泥댄럹?씠吏??닔*/,
            startPage/*?떆?옉?럹?씠吏?踰덊샇*/,
            endPage;/*留덉?留됲럹?씠吏?踰덊샇*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //紐⑤뱺 ?긽?솴?쓣 ?뙋?떒?븯?뿬 HTML肄붾뱶瑜? ???옣?븷 怨?
		
		
		isPrevPage=isNextPage=false;
		//?엯?젰?맂 ?쟾泥? ?옄?썝?쓣 ?넻?빐 ?쟾泥? ?럹?씠吏? ?닔瑜? 援ы븳?떎..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//留뚯빟 ?옒紐삳맂 ?뿰?궛怨? ??吏곸엫?쑝濡? ?씤?븯?뿬 ?쁽?옱 ?럹?씠吏? ?닔媛? ?쟾泥? ?럹?씠吏? ?닔瑜?
		//?꽆?쓣 寃쎌슦 媛뺤젣濡? ?쁽?옱?럹?씠吏? 媛믪쓣 ?쟾泥? ?럹?씠吏? 媛믪쑝濡? 蹂?寃?
		if(nowPage > totalPage)nowPage = totalPage;
		

		//?떆?옉 ?럹?씠吏??? 留덉?留? ?럹?씠吏?瑜? 援ы븿.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//留덉?留? ?럹?씠吏? ?닔媛? ?쟾泥댄럹?씠吏??닔蹂대떎 ?겕硫? 留덉?留됲럹?씠吏? 媛믪쓣 蹂?寃?
		if(endPage > totalPage)endPage = totalPage;
		
		//留덉?留됲럹?씠吏?媛? ?쟾泥댄럹?씠吏?蹂대떎 ?옉?쓣 寃쎌슦 ?떎?쓬 ?럹?씠吏뺤씠 ?쟻?슜?븷 ?닔 ?엳?룄濡?
		//boolean?삎 蹂??닔?쓽 媛믪쓣 ?꽕?젙
		if(endPage < totalPage) isNextPage = true;
		//?떆?옉?럹?씠吏??쓽 媛믪씠 1蹂대떎 ?옉?쑝硫? ?씠?쟾?럹?씠吏? ?쟻?슜?븷 ?닔 ?엳?룄濡? 媛믪꽕?젙
		if(startPage > 1)isPrevPage = true;
		
		//HTML肄붾뱶瑜? ???옣?븷 StringBuffer?깮?꽦=>肄붾뱶?깮?꽦
		sb = new StringBuffer();
//-----洹몃９?럹?씠吏?泥섎━ ?씠?쟾 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			/*sb.append("<a href ='"+pageURL+"?page=");*/
			sb.append(startPage-1); // nowPage-blockPage
			
			/*sb.append("&search=");
			sb.append(search);
			sb.append("&search_text=");
			sb.append(search_text);
			sb.append("<li class=\'disabled\'><a href=\'#\'>吏?</a></li>");
			
			sb.append("'>??</a>");*/
			sb.append("<li class=\'disabled\'><a href=\'#\'>«</a></li>");
		}
		else
			/*sb.append("??");*/
			sb.append("<li class=\'disabled\'><a href=\'\'>«</a></li>");
		
//------?럹?씠吏? 紐⑸줉 異쒕젰 -------------------------------------------------------------------------------------------------
		sb.append("");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //?쁽?옱 ?엳?뒗 ?럹?씠吏?
				/*sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");*/
				sb.append("<li class=\'active\'><a href=\'list.do?page="+i+"\'>"+i+"<span class=\'sr-only\'>(current)</span></a></li>");
			}
			else{//?쁽?옱 ?럹?씠吏?媛? ?븘?땲硫?
				/*sb.append("&nbsp;<a href='"+pageURL+"?page=");
				sb.append(i);
				
				sb.append("&search=");
				sb.append(search);
				sb.append("&search_text=");
				sb.append(search_text);
				
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");*/
				sb.append("<li><a href=\'list.do?page=" + i +"\'>"+i+"</a></li>");
			}
		}// end for
		
		sb.append("&nbsp;");
		
//-----洹몃９?럹?씠吏?泥섎━ ?떎?쓬 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			/*sb.append("<a href='"+pageURL+"?page=");*/

			sb.append(endPage + 1);
			
	/*		sb.append("&search=");
			sb.append(search);
			sb.append("&search_text=");
			sb.append(search_text);
			
			sb.append("'>?뼳</a>");*/
			sb.append("<li><a href='list.do?page='>»</a></li>");
		}
		else
			/*sb.append("?뼳");*/
			sb.append("<li><a href=\"#\">»</a></li>");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
	
	public static String getCommentPaging(
			int nowPage, 
			int rowTotal,
			int blockList, 
			int blockPage){
		
		int totalPage/*?쟾泥댄럹?씠吏??닔*/,
            startPage/*?떆?옉?럹?씠吏?踰덊샇*/,
            endPage;/*留덉?留됲럹?씠吏?踰덊샇*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //紐⑤뱺 ?긽?솴?쓣 ?뙋?떒?븯?뿬 HTML肄붾뱶瑜? ???옣?븷 怨?
		
		
		isPrevPage=isNextPage=false;
		//?엯?젰?맂 ?쟾泥? ?옄?썝?쓣 ?넻?빐 ?쟾泥? ?럹?씠吏? ?닔瑜? 援ы븳?떎..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//留뚯빟 ?옒紐삳맂 ?뿰?궛怨? ??吏곸엫?쑝濡? ?씤?븯?뿬 ?쁽?옱 ?럹?씠吏? ?닔媛? ?쟾泥? ?럹?씠吏? ?닔瑜?
		//?꽆?쓣 寃쎌슦 媛뺤젣濡? ?쁽?옱?럹?씠吏? 媛믪쓣 ?쟾泥? ?럹?씠吏? 媛믪쑝濡? 蹂?寃?
		if(nowPage > totalPage)nowPage = totalPage;
		

		//?떆?옉 ?럹?씠吏??? 留덉?留? ?럹?씠吏?瑜? 援ы븿.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//留덉?留? ?럹?씠吏? ?닔媛? ?쟾泥댄럹?씠吏??닔蹂대떎 ?겕硫? 留덉?留됲럹?씠吏? 媛믪쓣 蹂?寃?
		if(endPage > totalPage)endPage = totalPage;
		
		//留덉?留됲럹?씠吏?媛? ?쟾泥댄럹?씠吏?蹂대떎 ?옉?쓣 寃쎌슦 ?떎?쓬 ?럹?씠吏뺤씠 ?쟻?슜?븷 ?닔 ?엳?룄濡?
		//boolean?삎 蹂??닔?쓽 媛믪쓣 ?꽕?젙
		if(endPage < totalPage) isNextPage = true;
		//?떆?옉?럹?씠吏??쓽 媛믪씠 1蹂대떎 ?옉?쑝硫? ?씠?쟾?럹?씠吏? ?쟻?슜?븷 ?닔 ?엳?룄濡? 媛믪꽕?젙
		if(startPage > 1)isPrevPage = true;
		
		//HTML肄붾뱶瑜? ???옣?븷 StringBuffer?깮?꽦=>肄붾뱶?깮?꽦
		sb = new StringBuffer();
//-----洹몃９?럹?씠吏?泥섎━ ?씠?쟾 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a onclick='comment_list(");
			sb.append(startPage-1); // nowPage-blockPage
			sb.append(");'>??</a>");
		}
		else
			sb.append("??");
		
//------?럹?씠吏? 紐⑸줉 異쒕젰 -------------------------------------------------------------------------------------------------
		sb.append("|");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //?쁽?옱 ?엳?뒗 ?럹?씠吏?
				sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");
			}
			else{//?쁽?옱 ?럹?씠吏?媛? ?븘?땲硫?
				sb.append("&nbsp;<a onclick='comment_list(");
				sb.append(i);
				sb.append(");'>");
				sb.append(i);
				sb.append("</a>");
			}
		}// end for
		
		sb.append("&nbsp;|");
		
//-----洹몃９?럹?씠吏?泥섎━ ?떎?쓬 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a onclick='comment_list(");

			sb.append(endPage + 1);
			
			sb.append(");'>?뼳</a>");
		}
		else
			sb.append("?뼳");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
}