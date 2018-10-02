package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import vo.JoonggoVo;

public interface JoonggoDao {

	// ��� ������ 
	public List<JoonggoVo> selectList();
	
	// ���Ǻ� ��� ������ (������)
	public List<JoonggoVo> selectList(Map map);
	
	public int selectRowTotal(Map map);
	
	// 1�� ��ȸ
	public JoonggoVo selectOne(int j_idx);
	
	//�۾���(insert)
	public int insert(JoonggoVo vo);
	
	//����(update)
	public int update(JoonggoVo vo);
	
	//����(delete)
	public int delete(int j_idx);
	
	// ��ȸ��(readhits)
	public int readhits(int j_idx);
	
	// �ǸſϷ�(sell)
	public int sell(JoonggoVo vo);
	
	public int commentDaoTotal(int j_idx);
	
	
	
/*	public int reply(JoonggoVo vo);*/
	
	
}
