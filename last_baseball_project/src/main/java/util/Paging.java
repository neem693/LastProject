
package util;
/*
        pageURL:?�읂?�뵠筌욌벡荑귞뵳�뗫막 �뵳�딅뮞?�뱜?�읂?�뵠筌�?      
        nowPage:?�겱?�삺?�읂?�뵠筌�?
        rowTotal:?�읈筌ｋ��쑓?�뵠?苑ｅ첎??�땾
        blockList:?釉�?�읂?�뵠筌�??�뼣 野껊슣�뻻�눧�눘�땾
        blockPage:?釉�?�넅筌롫똻肉� ?援�???沅� ?�읂?�뵠筌�? 筌뤴뫖以�?�땾
 */
public class Paging {
	public static String getPaging(
			String pageURL, // /board/list.do
			int nowPage, 
			int rowTotal,
			int blockList, 
			int blockPage){
		
		int totalPage/*?�읈筌ｋ똾�읂?�뵠筌�??�땾*/,
            startPage/*?�뻻?�삂?�읂?�뵠筌�?甕곕뜇�깈*/,
            endPage;/*筌띾뜆?筌띾맪�읂?�뵠筌�?甕곕뜇�깈*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //筌뤴뫀諭� ?湲�?�넺?�뱽 ?�솇?�뼊?釉�?肉� HTML�굜遺얜굡�몴? ???�삢?釉� ��?
		
		
		isPrevPage=isNextPage=false;
		//?�뿯?�젾?留� ?�읈筌�? ?�쁽?�뜚?�뱽 ?�꽰?鍮� ?�읈筌�? ?�읂?�뵠筌�? ?�땾�몴? �뤃�뗫립?�뼄..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//筌띾슣鍮� ?�삋筌륁궠留� ?肉�?沅쎿��? ??筌욊낯�뿫?�몵嚥�? ?�뵥?釉�?肉� ?�겱?�삺 ?�읂?�뵠筌�? ?�땾揶�? ?�읈筌�? ?�읂?�뵠筌�? ?�땾�몴?
		//?苑�?�뱽 野껋럩�뒭 揶쏅벡�젫嚥�? ?�겱?�삺?�읂?�뵠筌�? 揶쏅�れ뱽 ?�읈筌�? ?�읂?�뵠筌�? 揶쏅�れ몵嚥�? 癰�?野�?
		if(nowPage > totalPage)nowPage = totalPage;
		

		//?�뻻?�삂 ?�읂?�뵠筌�??? 筌띾뜆?筌�? ?�읂?�뵠筌�?�몴? �뤃�뗫맙.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//筌띾뜆?筌�? ?�읂?�뵠筌�? ?�땾揶�? ?�읈筌ｋ똾�읂?�뵠筌�??�땾癰귣��뼄 ?寃뺧쭖? 筌띾뜆?筌띾맪�읂?�뵠筌�? 揶쏅�れ뱽 癰�?野�?
		if(endPage > totalPage)endPage = totalPage;
		
		//筌띾뜆?筌띾맪�읂?�뵠筌�?揶�? ?�읈筌ｋ똾�읂?�뵠筌�?癰귣��뼄 ?�삂?�뱽 野껋럩�뒭 ?�뼄?�벉 ?�읂?�뵠筌욌벡�뵠 ?�읅?�뒠?釉� ?�땾 ?�뿳?猷꾣에?
		//boolean?�굨 癰�??�땾?�벥 揶쏅�れ뱽 ?苑�?�젟
		if(endPage < totalPage) isNextPage = true;
		//?�뻻?�삂?�읂?�뵠筌�??�벥 揶쏅�れ뵠 1癰귣��뼄 ?�삂?�몵筌�? ?�뵠?�읈?�읂?�뵠筌�? ?�읅?�뒠?釉� ?�땾 ?�뿳?猷꾣에? 揶쏅�り퐬?�젟
		if(startPage > 1)isPrevPage = true;
		
		//HTML�굜遺얜굡�몴? ???�삢?釉� StringBuffer?源�?苑�=>�굜遺얜굡?源�?苑�
		sb = new StringBuffer();
//-----域밸챶竊�?�읂?�뵠筌�?筌ｌ꼶�봺 ?�뵠?�읈 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a href ='"+pageURL+"?page=");
			sb.append(startPage-1); // nowPage-blockPage
			sb.append("'>??</a>");
		}
		else
			sb.append("??");
		
//------?�읂?�뵠筌�? 筌뤴뫖以� �빊�뮆�젾 -------------------------------------------------------------------------------------------------
		sb.append("|");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //?�겱?�삺 ?�뿳?�뮉 ?�읂?�뵠筌�?
				sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");
			}
			else{//?�겱?�삺 ?�읂?�뵠筌�?揶�? ?釉�?�빍筌�?
				sb.append("&nbsp;<a href='"+pageURL+"?page=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}// end for
		
		sb.append("&nbsp;");
		
//-----域밸챶竊�?�읂?�뵠筌�?筌ｌ꼶�봺 ?�뼄?�벉 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a href='"+pageURL+"?page=");

			sb.append(endPage + 1);
			
			sb.append("'>?堉�</a>");
		}
		else
			sb.append("?堉�");
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
		
		int totalPage/*?�읈筌ｋ똾�읂?�뵠筌�??�땾*/,
            startPage/*?�뻻?�삂?�읂?�뵠筌�?甕곕뜇�깈*/,
            endPage;/*筌띾뜆?筌띾맪�읂?�뵠筌�?甕곕뜇�깈*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //筌뤴뫀諭� ?湲�?�넺?�뱽 ?�솇?�뼊?釉�?肉� HTML�굜遺얜굡�몴? ???�삢?釉� ��?
		
		
		isPrevPage=isNextPage=false;
		//?�뿯?�젾?留� ?�읈筌�? ?�쁽?�뜚?�뱽 ?�꽰?鍮� ?�읈筌�? ?�읂?�뵠筌�? ?�땾�몴? �뤃�뗫립?�뼄..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//筌띾슣鍮� ?�삋筌륁궠留� ?肉�?沅쎿��? ??筌욊낯�뿫?�몵嚥�? ?�뵥?釉�?肉� ?�겱?�삺 ?�읂?�뵠筌�? ?�땾揶�? ?�읈筌�? ?�읂?�뵠筌�? ?�땾�몴?
		//?苑�?�뱽 野껋럩�뒭 揶쏅벡�젫嚥�? ?�겱?�삺?�읂?�뵠筌�? 揶쏅�れ뱽 ?�읈筌�? ?�읂?�뵠筌�? 揶쏅�れ몵嚥�? 癰�?野�?
		if(nowPage > totalPage)nowPage = totalPage;
		

		//?�뻻?�삂 ?�읂?�뵠筌�??? 筌띾뜆?筌�? ?�읂?�뵠筌�?�몴? �뤃�뗫맙.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//筌띾뜆?筌�? ?�읂?�뵠筌�? ?�땾揶�? ?�읈筌ｋ똾�읂?�뵠筌�??�땾癰귣��뼄 ?寃뺧쭖? 筌띾뜆?筌띾맪�읂?�뵠筌�? 揶쏅�れ뱽 癰�?野�?
		if(endPage > totalPage)endPage = totalPage;
		
		//筌띾뜆?筌띾맪�읂?�뵠筌�?揶�? ?�읈筌ｋ똾�읂?�뵠筌�?癰귣��뼄 ?�삂?�뱽 野껋럩�뒭 ?�뼄?�벉 ?�읂?�뵠筌욌벡�뵠 ?�읅?�뒠?釉� ?�땾 ?�뿳?猷꾣에?
		//boolean?�굨 癰�??�땾?�벥 揶쏅�れ뱽 ?苑�?�젟
		if(endPage < totalPage) isNextPage = true;
		//?�뻻?�삂?�읂?�뵠筌�??�벥 揶쏅�れ뵠 1癰귣��뼄 ?�삂?�몵筌�? ?�뵠?�읈?�읂?�뵠筌�? ?�읅?�뒠?釉� ?�땾 ?�뿳?猷꾣에? 揶쏅�り퐬?�젟
		if(startPage > 1)isPrevPage = true;
		
		//HTML�굜遺얜굡�몴? ???�삢?釉� StringBuffer?源�?苑�=>�굜遺얜굡?源�?苑�
		sb = new StringBuffer();
//-----域밸챶竊�?�읂?�뵠筌�?筌ｌ꼶�봺 ?�뵠?�읈 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			/*sb.append("<a href ='"+pageURL+"?page=");*/
			sb.append(startPage-1); // nowPage-blockPage
			
			/*sb.append("&search=");
			sb.append(search);
			sb.append("&search_text=");
			sb.append(search_text);
			sb.append("<li class=\'disabled\'><a href=\'#\'>筌�?</a></li>");
			
			sb.append("'>??</a>");*/
			sb.append("<li class=\'disabled\'><a href=\'#\'> < < </a></li>");
		}
		else
			/*sb.append("??");*/
			sb.append("<li class=\'disabled\'><a href=\'\'> < </a></li>");
		
//------?�읂?�뵠筌�? 筌뤴뫖以� �빊�뮆�젾 -------------------------------------------------------------------------------------------------
		sb.append("&nbsp;");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //?�겱?�삺 ?�뿳?�뮉 ?�읂?�뵠筌�?
				/*sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");*/
				sb.append("<li class=\'active\'><a href=\'list.do?page="+i+"\'>"+i+"<span class=\'sr-only\'>(current)</span></a></li>");
			}
			else{//?�겱?�삺 ?�읂?�뵠筌�?揶�? ?釉�?�빍筌�?
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
		
//-----域밸챶竊�?�읂?�뵠筌�?筌ｌ꼶�봺 ?�뼄?�벉 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			/*sb.append("<a href='"+pageURL+"?page=");*/

			sb.append(endPage + 1);
			
	/*		sb.append("&search=");
			sb.append(search);
			sb.append("&search_text=");
			sb.append(search_text);
			
			sb.append("'>?堉�</a>");*/
			sb.append("<li><a href='list.do?page='> > </a></li>");
		}
		else
			/*sb.append("?堉�");*/
			sb.append("<li><a href=\"#\"> > </a></li>");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
	
	public static String getCommentPaging(
			int nowPage, 
			int rowTotal,
			int blockList, 
			int blockPage){
		
		int totalPage/*?�읈筌ｋ똾�읂?�뵠筌�??�땾*/,
            startPage/*?�뻻?�삂?�읂?�뵠筌�?甕곕뜇�깈*/,
            endPage;/*筌띾뜆?筌띾맪�읂?�뵠筌�?甕곕뜇�깈*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //筌뤴뫀諭� ?湲�?�넺?�뱽 ?�솇?�뼊?釉�?肉� HTML�굜遺얜굡�몴? ???�삢?釉� ��?
		
		
		isPrevPage=isNextPage=false;
		//?�뿯?�젾?留� ?�읈筌�? ?�쁽?�뜚?�뱽 ?�꽰?鍮� ?�읈筌�? ?�읂?�뵠筌�? ?�땾�몴? �뤃�뗫립?�뼄..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//筌띾슣鍮� ?�삋筌륁궠留� ?肉�?沅쎿��? ??筌욊낯�뿫?�몵嚥�? ?�뵥?釉�?肉� ?�겱?�삺 ?�읂?�뵠筌�? ?�땾揶�? ?�읈筌�? ?�읂?�뵠筌�? ?�땾�몴?
		//?苑�?�뱽 野껋럩�뒭 揶쏅벡�젫嚥�? ?�겱?�삺?�읂?�뵠筌�? 揶쏅�れ뱽 ?�읈筌�? ?�읂?�뵠筌�? 揶쏅�れ몵嚥�? 癰�?野�?
		if(nowPage > totalPage)nowPage = totalPage;
		

		//?�뻻?�삂 ?�읂?�뵠筌�??? 筌띾뜆?筌�? ?�읂?�뵠筌�?�몴? �뤃�뗫맙.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//筌띾뜆?筌�? ?�읂?�뵠筌�? ?�땾揶�? ?�읈筌ｋ똾�읂?�뵠筌�??�땾癰귣��뼄 ?寃뺧쭖? 筌띾뜆?筌띾맪�읂?�뵠筌�? 揶쏅�れ뱽 癰�?野�?
		if(endPage > totalPage)endPage = totalPage;
		
		//筌띾뜆?筌띾맪�읂?�뵠筌�?揶�? ?�읈筌ｋ똾�읂?�뵠筌�?癰귣��뼄 ?�삂?�뱽 野껋럩�뒭 ?�뼄?�벉 ?�읂?�뵠筌욌벡�뵠 ?�읅?�뒠?釉� ?�땾 ?�뿳?猷꾣에?
		//boolean?�굨 癰�??�땾?�벥 揶쏅�れ뱽 ?苑�?�젟
		if(endPage < totalPage) isNextPage = true;
		//?�뻻?�삂?�읂?�뵠筌�??�벥 揶쏅�れ뵠 1癰귣��뼄 ?�삂?�몵筌�? ?�뵠?�읈?�읂?�뵠筌�? ?�읅?�뒠?釉� ?�땾 ?�뿳?猷꾣에? 揶쏅�り퐬?�젟
		if(startPage > 1)isPrevPage = true;
		
		//HTML�굜遺얜굡�몴? ???�삢?釉� StringBuffer?源�?苑�=>�굜遺얜굡?源�?苑�
		sb = new StringBuffer();
//-----域밸챶竊�?�읂?�뵠筌�?筌ｌ꼶�봺 ?�뵠?�읈 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a onclick='comment_list(");
			sb.append(startPage-1); // nowPage-blockPage
			sb.append(");'>??</a>");
		}
		else
			sb.append("??");
		
//------?�읂?�뵠筌�? 筌뤴뫖以� �빊�뮆�젾 -------------------------------------------------------------------------------------------------
		sb.append("|");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //?�겱?�삺 ?�뿳?�뮉 ?�읂?�뵠筌�?
				sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");
			}
			else{//?�겱?�삺 ?�읂?�뵠筌�?揶�? ?釉�?�빍筌�?
				sb.append("&nbsp;<a onclick='comment_list(");
				sb.append(i);
				sb.append(");'>");
				sb.append(i);
				sb.append("</a>");
			}
		}// end for
		
		sb.append("&nbsp;|");
		
//-----域밸챶竊�?�읂?�뵠筌�?筌ｌ꼶�봺 ?�뼄?�벉 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a onclick='comment_list(");

			sb.append(endPage + 1);
			
			sb.append(");'>?堉�</a>");
		}
		else
			sb.append("?堉�");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
}