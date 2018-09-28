/**
 * 
 */





function party_join(f){
	
	if(f.pt_idx == null)
		alert("잘못된 접근입니다.");
	
	f.method = "POST";
	
	f.action = "party_join.do";
	f.submit();
};


function party_leave(f){
	
	if(f.pt_idx == null)
		alert("잘못된 접근입니다.");
	
	f.method = "POST";
	
	f.action = "party_leave.do";
	f.submit();
	
}