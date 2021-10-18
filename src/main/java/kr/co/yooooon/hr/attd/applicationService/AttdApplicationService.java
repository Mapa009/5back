package kr.co.yooooon.hr.attd.applicationService;

import java.text.ParseException;
import java.util.ArrayList;

import kr.co.yooooon.common.to.ResultTO;
import kr.co.yooooon.hr.attd.to.*;
import kr.co.yooooon.hr.attd.to.AnnualVacationMgtTO;

public interface AttdApplicationService {
   public ArrayList<DayAttdTO> findDayAttdList(String empCode, String applyDay);
   public ResultTO registDayAttd(DayAttdTO dayAttd);
   public void removeDayAttdList(ArrayList<DayAttdTO> dayAttdList);
   public void insertDayAttd(DayAttdTO dayAttd); //test

   public ArrayList<DayAttdMgtTO> findDayAttdMgtList(String applyDay, String dept);
   public void modifyDayAttdMgtList(ArrayList<DayAttdMgtTO> dayAttdMgtList);
   public ArrayList<MonthAttdMgtTO> findMonthAttdMgtList(String applyYearMonth);
   public void modifyMonthAttdMgtList(ArrayList<MonthAttdMgtTO> monthAttdMgtList);
   
   public ArrayList<RestAttdTO> findRestAttdList(String empCode, String startDate, String endDate, String code);
   public ArrayList<RestAttdTO> findRestAttdListByDept(String deptName, String startDate, String endDate) throws ParseException;
   public ArrayList<RestAttdTO> findRestAttdListByToday(String empCode, String toDay);
   public void registRestAttd(RestAttdTO restAttd);
   public void modifyRestAttdList(ArrayList<RestAttdTO> restAttdList);
   public void removeRestAttdList(ArrayList<RestAttdTO> restAttdList);
   
   public ArrayList<AnnualVacationMgtTO> findAnnualVacationMgtList(String applyYearMonth);
   public void modifyAnnualVacationMgtList(AnnualVacationMgtTO annualVacationMgtTO);
   public void cancelAnnualVacationMgtList(ArrayList<AnnualVacationMgtTO> annualVacationMgtList);
   
}