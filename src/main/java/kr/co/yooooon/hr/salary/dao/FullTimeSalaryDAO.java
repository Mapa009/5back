package kr.co.yooooon.hr.salary.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.yooooon.hr.salary.to.FullTimeSalTO;
import kr.co.yooooon.hr.salary.to.PayDayTO;

@Mapper
public interface FullTimeSalaryDAO {
	//ArrayList<FullTimeSalTO> findAllMoney(String applyYearMonth);
	ArrayList<FullTimeSalTO> selectFullTimeSalary(HashMap<String,Object> map);
	/*void updateFullTimeSalary(FullTimeSalTO fullTimeSalary);
	ArrayList<PayDayTO> selectPayDayList();*/
}
