package dao.normal;

import java.util.List;
import java.util.Map;

import vo.NormalVo;

public interface NormalDaoInterface {

	public List<NormalVo> selectList(Map map);
	/*public List<NormalVo> selectList(Map map);*/
	public NormalVo selectOne(int nc_idx);
	public int update_views(int nc_idx);
	public int insert(NormalVo vo);
	public int delete(int nc_idx);
	public int selctRowTotal(Map map);
	public int update(NormalVo vo);
}
