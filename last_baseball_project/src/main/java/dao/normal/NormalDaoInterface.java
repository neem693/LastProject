package dao.normal;

import java.util.List;
import java.util.Map;

import vo.NormalVo;

public interface NormalDaoInterface {

	public List<NormalVo> selectList();
	/*public List<NormalVo> selectList(Map map);*/
	/*public NormalVo selectOne(int idx);*/
	/*public int update_readhit(int idx);*/
	public int insert(NormalVo vo);
	/*public int delete(int idx);*/
	/*public int update_step(NormalVo normalVo);*/
	/*public int reply(NormalVo vo);*/
	public int selctRowTotal(Map map);
	/*public int update(NormalVo vo);*/
}
