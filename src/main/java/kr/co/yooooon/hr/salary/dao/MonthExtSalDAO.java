package kr.co.yooooon.hr.salary.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.yooooon.hr.salary.to.MonthExtSalTO;

@Mapper
public interface MonthExtSalDAO {
	//public ArrayList<MonthExtSalTO> selectMonthExtSalList(HashMap<String , Object> map);
	public ArrayList<MonthExtSalTO> selectMonthExtSalList(@Param("applyYearMonth") String applyYearMonth,@Param("empCode") String empCode);
}
