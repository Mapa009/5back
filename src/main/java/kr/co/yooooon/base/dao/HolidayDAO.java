package kr.co.yooooon.base.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.yooooon.base.to.HolidayTO;

@Mapper
public interface HolidayDAO {
	//public ArrayList<HolidayTO> selectHolidayList();
	String selectWeekDayCount(HashMap<String,Object> map);
	/*public void updateCodeList(HolidayTO holyday);
	public void insertCodeList(HolidayTO holyday);
	public void deleteCodeList(HolidayTO holyday);*/
}
