package service;

import java.util.List;

import vo.TeamVo;

public interface ServiceInterface {
	
	
int insert();
List selectList();
Object selectOne();
int update(Object [] ob);
int update();
int delete();




}
