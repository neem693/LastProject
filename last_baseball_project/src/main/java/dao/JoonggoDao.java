package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import vo.JoonggoVo;

public interface JoonggoDao {

	// 목록 얻어오기 
	public List<JoonggoVo> selectList();
	
	// 조건별 목록 얻어오기 (페이지)
	public List<JoonggoVo> selectList(Map map);
	
	public int selectRowTotal(Map map);
	
	// 1건 조회
	public JoonggoVo selectOne(int j_idx);
	
	//글쓰기(insert)
	public int insert(JoonggoVo vo);
	
	//수정(update)
	public int update(JoonggoVo vo);
	
	//삭제(delete)
	public int delete(int j_idx);
	
	// 조회수(readhits)
	public int readhits(int j_idx);
	
	// 판매완료(sell)
	public int sell(JoonggoVo vo);
	
	public int commentDaoTotal(int j_idx);
	
	
	
/*	public int reply(JoonggoVo vo);*/
	
	
}
