package kr.co.yooooon.hr.attd.sf;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import kr.co.yooooon.common.to.ResultTO;
import kr.co.yooooon.hr.attd.to.AnnualVacationMgtTO;
import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;
import kr.co.yooooon.hr.attd.to.DayAttdTO;
import kr.co.yooooon.hr.attd.to.MonthAttdMgtTO;
import kr.co.yooooon.hr.attd.to.RestAttdTO;

public interface AttdServiceFacade {
   public ArrayList<DayAttdTO> findDayAttdList(String empCode, String applyDay);
   public ResultTO registDayAttd(DayAttdTO dayAttd);
   public void removeDayAttdList(ArrayList<DayAttdTO> dayAttdList);
   public void insertDayAttd(DayAttdTO dayAttd); // test

   public ArrayList<RestAttdTO> findRestAttdList(String empCode, String startDate, String endDate, String code);
   public ArrayList<RestAttdTO> findRestAttdListByDept(String deptName, String startDate, String endDate) throws ParseException;
   public ArrayList<RestAttdTO> findRestAttdListByToday(String empCode, String toDay);
   public void registRestAttd(RestAttdTO restAttd);
   public void modifyRestAttdList(ArrayList<RestAttdTO> restAttdList);
   public void removeRestAttdList(ArrayList<RestAttdTO> restAttdList);

   public ArrayList<DayAttdMgtTO> findDayAttdMgtList(String applyDay);
   public void modifyDayAttdMgtList(ArrayList<DayAttdMgtTO> dayAttdMgtList);

   public ArrayList<MonthAttdMgtTO> findMonthAttdMgtList(String applyYearMonth);
   public void modifyMonthAttdMgtList(ArrayList<MonthAttdMgtTO> monthAttdMgtList);
   
   public List<AnnualVacationMgtTO> findAnnualVacationMgtList(String applyYearMonth);
   public void modifyAnnualVacationMgtList(ArrayList<AnnualVacationMgtTO> annualVacationMgtTO);
   public void cancelAnnualVacationMgtList(ArrayList<AnnualVacationMgtTO> annualVacationMgtList);
}