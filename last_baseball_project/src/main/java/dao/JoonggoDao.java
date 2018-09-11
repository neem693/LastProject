package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import vo.JoonggoVo;

public interface JoonggoDao {

	// ����Ʈ ������ 
	public List<JoonggoVo> selectList();
	
	public List<JoonggoVo> selectList(Map map);
	
	// 1�� ��ȸ
	public JoonggoVo selectOne(int idx);
	
	//�۾���(insert)
	public int insert(JoonggoVo vo);
	
	//����(update)
	public int update(JoonggoVo vo);
	
	//����(delete)
	public int delete(int idx);
	
	// ��ȸ��(readhits)
	public int readhits(int idx);
	
	
}