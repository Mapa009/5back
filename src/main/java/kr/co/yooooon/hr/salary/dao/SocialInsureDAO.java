package kr.co.yooooon.hr.salary.dao;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import kr.co.yooooon.hr.salary.to.SocialInsureTO;

@Mapper
public interface SocialInsureDAO {
	public ArrayList<SocialInsureTO> selectBaseInsureList(HashMap<String,Object> map);
	/*public void updateInsureData(ArrayList<SocialInsureTO> baseInsure);
	public void deleteInsureData(SocialInsureTO baseInsure);*/
}