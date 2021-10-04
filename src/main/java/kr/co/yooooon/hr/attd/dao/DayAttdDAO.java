package kr.co.yooooon.hr.attd.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.yooooon.common.to.ResultTO;
import kr.co.yooooon.hr.attd.to.DayAttdTO;

@Mapper
public interface DayAttdDAO {
	//public ArrayList<DayAttdTO> selectDayAttdList(HashMap<String , Object> map);

	public ResultTO batchInsertDayAttd(HashMap<String , Object> map);
	
	/*public void insertDayAttd(DayAttdTO dayAttd);
	public void deleteDayAttd(DayAttdTO dayAttd);*/
}
