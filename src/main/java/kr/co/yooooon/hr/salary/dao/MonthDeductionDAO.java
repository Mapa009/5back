package kr.co.yooooon.hr.salary.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.yooooon.hr.salary.to.MonthDeductionTO;

@Mapper
public interface MonthDeductionDAO {
	//public ArrayList<MonthDeductionTO> selectMonthDeductionList(HashMap<String , Object> map);
	public ArrayList<MonthDeductionTO> selectMonthDeductionList(@Param("applyYearMonth") String applyYearMonth,@Param("empCode") String empCode);
}
