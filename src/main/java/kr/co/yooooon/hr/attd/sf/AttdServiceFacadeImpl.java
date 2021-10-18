package kr.co.yooooon.hr.attd.sf;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.yooooon.common.to.ResultTO;
import kr.co.yooooon.hr.attd.applicationService.AttdApplicationService;
import kr.co.yooooon.hr.attd.to.AnnualVacationMgtTO;
import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;
import kr.co.yooooon.hr.attd.to.DayAttdTO;
import kr.co.yooooon.hr.attd.to.MonthAttdMgtTO;
import kr.co.yooooon.hr.attd.to.RestAttdTO;

@Service
public class AttdServiceFacadeImpl implements AttdServiceFacade{
	@Autowired
   private AttdApplicationService attdApplicationService;
   
   @Override
   public ArrayList<DayAttdTO> findDayAttdList(String empCode, String applyDay) {
         
         ArrayList<DayAttdTO> dayAttdList=attdApplicationService.findDayAttdList(empCode, applyDay);
         return dayAttdList;
         
   }
   
   @Override
   public ResultTO registDayAttd(DayAttdTO dayAttd) {
         ResultTO resultTO=attdApplicationService.registDayAttd(dayAttd);
         return resultTO;  
   }

   @Override
   public void removeDayAttdList(ArrayList<DayAttdTO> dayAttdList) { 
         attdApplicationService.removeDayAttdList(dayAttdList);
   }
   
   @Override
   public ArrayList<RestAttdTO> findRestAttdList(String empCode, String startDate, String endDate, String code) {    
         ArrayList<RestAttdTO> restAttdList = attdApplicationService.findRestAttdList(empCode, startDate, endDate, code);     
         return restAttdList;
   }

   @Override
   public ArrayList<RestAttdTO> findRestAttdListByDept(String deptName, String startDate, String endDate) throws ParseException {
    
         ArrayList<RestAttdTO> restAttdList = attdApplicationService.findRestAttdListByDept(deptName, startDate, endDate);
         return restAttdList;
     
   }

   @Override
   public ArrayList<RestAttdTO> findRestAttdListByToday(String empCode, String toDay) {
         ArrayList<RestAttdTO> restAttdList = attdApplicationService.findRestAttdListByToday(empCode,toDay);
         return restAttdList;
   }

   @Override
   public void registRestAttd(RestAttdTO restAttd) {
         attdApplicationService.registRestAttd(restAttd);
   }

   @Override
   public void modifyRestAttdList(ArrayList<RestAttdTO> restAttdList) {
         attdApplicationService.modifyRestAttdList(restAttdList);
   }
   
   @Override
   public void removeRestAttdList(ArrayList<RestAttdTO> restAttdList) {

         attdApplicationService.removeRestAttdList(restAttdList);
       
   }
   
   @Override
   public ArrayList<DayAttdMgtTO> findDayAttdMgtList(String applyDay, String dept) {	  
         ArrayList<DayAttdMgtTO> dayAttdMgtList = attdApplicationService.findDayAttdMgtList(applyDay, dept);   
         return dayAttdMgtList;
   }

   @Override
   public void modifyDayAttdMgtList(ArrayList<DayAttdMgtTO> dayAttdMgtList) {    
         attdApplicationService.modifyDayAttdMgtList(dayAttdMgtList);
   }
   
   @Override
   public ArrayList<MonthAttdMgtTO> findMonthAttdMgtList(String applyYearMonth) {
     
         ArrayList<MonthAttdMgtTO> monthAttdMgtList = attdApplicationService.findMonthAttdMgtList(applyYearMonth);
         return monthAttdMgtList;
   }
   
   @Override
   public void modifyMonthAttdMgtList(ArrayList<MonthAttdMgtTO> monthAttdMgtList) {
         attdApplicationService.modifyMonthAttdMgtList(monthAttdMgtList);
   }
   
   @Override
   public void insertDayAttd(DayAttdTO dayAttd) {
         attdApplicationService.insertDayAttd(dayAttd);
   }
   @Override
   public List<AnnualVacationMgtTO> findAnnualVacationMgtList(String applyYearMonth){
    
         ArrayList<AnnualVacationMgtTO> annualVacationMgtList = attdApplicationService.findAnnualVacationMgtList(applyYearMonth);
         return annualVacationMgtList;
   }
   
   @Override
   public void modifyAnnualVacationMgtList(AnnualVacationMgtTO annualVacationMgtTO) {
         attdApplicationService.modifyAnnualVacationMgtList(annualVacationMgtTO);
   }
   
   @Override
   public void cancelAnnualVacationMgtList(ArrayList<AnnualVacationMgtTO> annualVacationMgtList) {
	   	attdApplicationService.cancelAnnualVacationMgtList(annualVacationMgtList);
     }
}