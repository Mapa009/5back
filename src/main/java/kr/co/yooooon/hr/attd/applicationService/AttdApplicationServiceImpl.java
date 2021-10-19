package kr.co.yooooon.hr.attd.applicationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import kr.co.yooooon.hr.attd.repository.*;
import kr.co.yooooon.hr.attd.to.*;
import kr.co.yooooon.hr.emp.repository.EmpRepository;
import kr.co.yooooon.hr.emp.to.EmpTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.common.to.ResultTO;
import kr.co.yooooon.hr.attd.dao.AnnualVacationMgtDAO;
import kr.co.yooooon.hr.attd.dao.DayAttdDAO;
import kr.co.yooooon.hr.attd.dao.DayAttdMgtDAO;
import kr.co.yooooon.hr.attd.dao.MonthAttdMgtDAO;
import kr.co.yooooon.hr.attd.dao.RestAttdDAO;

@Component
public class AttdApplicationServiceImpl implements AttdApplicationService{
	 
   @Autowired
   private DayAttdDAO dayAttdDAO;
   @Autowired
   private RestAttdDAO restAttdDAO;
   @Autowired
   private DayAttdMgtDAO dayAttdMgtDAO;
   @Autowired
   private MonthAttdMgtDAO monthAttdMgtDAO;
   @Autowired
   private AnnualVacationMgtDAO annualVacationMgtDAO; 
   @Autowired
   private DayAttdRepository dayAttdRepository;
   @Autowired
   private RestAttdRepository restAttdRepository;
   @Autowired
   private DayAttdMgtRepository dayAttdMgtRepository;
   @Autowired
   private MonthAttdMgtRepository monthAttdMgtRepository;
   @Autowired
   private AnnualVacationMgtRepository annualVacationMgtRepository;
   @Autowired
   private EmpRepository empRepository;
   @Autowired
   private AnnualVactionRepository annualVactionRepository;

   @Override
   public ArrayList<DayAttdTO> findDayAttdList(String empCode, String applyDay) {
      ArrayList<DayAttdTO> dayAttdList=dayAttdRepository.findDayAttdTOByEmpCodeAndApplyDayOrderByTime(empCode,applyDay);
      for(DayAttdTO t : dayAttdList){
         EmpTO emp = empRepository.findEmpInfoByEmpCode(t.getEmpCode());
         t.setEmpName(emp.getEmpName());
      }
      return dayAttdList;
   }

   @Override
   public ResultTO registDayAttd(DayAttdTO dayAttd) {
	  HashMap<String , Object> map = new HashMap<String , Object>(); 
	  map.put("empCode",dayAttd.getEmpCode());
	  map.put("attdTypeCode",dayAttd.getAttdTypeCode());
	  map.put("attdTypeName",dayAttd.getAttdTypeName());
	  map.put("applyDay",dayAttd.getApplyDay());
	  map.put("time",dayAttd.getTime());
		//프로시저
      dayAttdDAO.batchInsertDayAttd(map);
      
      String errorCode = (String)map.get("errorCode");
      String errorMsg = (String) map.get("errorMsg");
      
      ResultTO resultTO = new ResultTO();
      resultTO.setErrorCode(errorCode);
      resultTO.setErrorMsg(errorMsg);
      
      return resultTO;      
      
   }

   @Override
   public void removeDayAttdList(ArrayList<DayAttdTO> dayAttdList) {
      dayAttdRepository.deleteAll(dayAttdList);
   }
   
   @Override
   public ArrayList<RestAttdTO> findRestAttdListByToday(String empCode, String toDay) {
     HashMap<String , Object> map = new HashMap<String , Object>();
     map.put("empCode",empCode);
     map.put("toDay",toDay);
	   //서브쿼리
      ArrayList<RestAttdTO> restAttdList = restAttdDAO.selectRestAttdListByToday(map);
      
      return restAttdList;
   }

   @Override
   public void modifyDayAttdMgtList(ArrayList<DayAttdMgtTO> dayAttdMgtList) {
     System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@###################"+dayAttdMgtList);
      for(DayAttdMgtTO dayAttdMgt : dayAttdMgtList){
    	  
    	  dayAttdMgtDAO.updateDayAttdMgtList(dayAttdMgt);
            //dayAttdMgtRepository.save(dayAttdMgt);
         
         
      }
 
   }

   @Override
   public ArrayList<MonthAttdMgtTO> findMonthAttdMgtList(String applyYearMonth) {
	  HashMap<String , Object> map = new HashMap<String , Object>();
	  map.put("applyYearMonth",applyYearMonth);
	   //프로시저
      monthAttdMgtDAO.batchMonthAttdMgtProcess(map);
      
      @SuppressWarnings("unchecked")
      ArrayList<MonthAttdMgtTO> monthAttdMgtList = (ArrayList<MonthAttdMgtTO>) map.get("result");
      return monthAttdMgtList;
   }
   
   @Override
   public ArrayList<RestAttdTO> findRestAttdListByDept(String deptName, String startDate, String endDate) throws ParseException {
      ArrayList<RestAttdTO> restAttdList = null;
      HashMap<String , Object> map = new HashMap<String , Object>();
      if(deptName==""){
         deptName="모든부서";
      }
      if(deptName.equals("모든부서")) {
         //서브쿼리
         restAttdList = restAttdDAO.selectRestAttdListByAllDept(startDate);
      }else {
    	  map.put("deptName",deptName);
          map.put("startDate",startDate);
          map.put("endDate",endDate);
         restAttdList = restAttdDAO.selectRestAttdListByDept(map);
      }
      return restAttdList;
   }
   
   
   @Override
   public void registRestAttd(RestAttdTO restAttd) {
	   restAttdRepository.save(restAttd);
   }
   
   @Override
   public ArrayList<RestAttdTO> findRestAttdList(String empCode, String startDate, String endDate, String code) {   
      ArrayList<RestAttdTO> restAttdList=null;
      HashMap<String , Object> map = new HashMap<String,Object>();
    
      if(code == "") {
    	  map.put("empCode",empCode);
    	  map.put("startDate",startDate);
    	  map.put("endDate",endDate);
         restAttdList = restAttdDAO.selectRestAttdList(map);
      }else {
    	  map.put("empCode",empCode);
    	  map.put("startDate",startDate);
    	  map.put("endDate",endDate);
    	  map.put("code",code);
         restAttdList = restAttdDAO.selectRestAttdListCode(map);
      }
      return restAttdList;
   }
   
   @Override
   public void removeRestAttdList(ArrayList<RestAttdTO> restAttdList) {
      restAttdRepository.deleteAll(restAttdList);
   }

   @Override
   public void modifyRestAttdList(ArrayList<RestAttdTO> restAttdList) {
     
      for(RestAttdTO restAttd : restAttdList){
         if(restAttd.getCause() == null || restAttd.getRejectCause() == null) {
        	 restAttd.setCause("X");
        	 restAttd.setRejectCause("X");
         }
         
         if(restAttd.getStatus().equals("update")){
            restAttdDAO.updateRestAttd(restAttd);
         }
      }
       
   }
   
   @Override
   public ArrayList<DayAttdMgtTO> findDayAttdMgtList(String applyDay) {
     HashMap<String , Object> map = new HashMap<String , Object>();
     map.put("applyDay",applyDay);
     
     dayAttdMgtDAO.batchDayAttdMgtProcess(map);
    
     String errorCode = (String)map.get("errorCode");
     String errorMsg =  (String)map.get("errorMsg");
    
     if(Integer.parseInt(errorCode) < 0){ throw new DataAccessException(errorMsg);}
    
     @SuppressWarnings("unchecked")
     ArrayList<DayAttdMgtTO> dayAttdMgtList = (ArrayList<DayAttdMgtTO>)map.get("result");
     System.out.println(map);
     
     return dayAttdMgtList;
   }
   
   @Override
   public void modifyMonthAttdMgtList(ArrayList<MonthAttdMgtTO> monthAttdMgtList) {
      for(MonthAttdMgtTO monthAttdMgt : monthAttdMgtList){
         
           monthAttdMgtRepository.save(monthAttdMgt);
         
      }       
   }
   
   @Override
   public void insertDayAttd(DayAttdTO dayAttd) { //test
      dayAttdRepository.save(dayAttd);
   }
   
   @Override
   public ArrayList<AnnualVacationMgtTO> findAnnualVacationMgtList(String applyYearMonth){
    HashMap<String , Object> map = new HashMap<String ,Object>();
     map.put("applyYearMonth",applyYearMonth);
     map.put("dept","인사팀");
       ArrayList<AnnualVacationMgtTO> annualVacationMgtList=annualVacationMgtRepository.findAllByApplyYearMonth(applyYearMonth);
      /*annualVacationMgtDAO.batchAnnualVacationMgtProcess(map);
      
      String errorCode = (String)map.get("errorCode");
      String errorMsg =  (String)map.get("errorMsg");
     
     if(Integer.parseInt(errorCode) < 0){ throw new DataAccessException(errorMsg);}
     
      @SuppressWarnings("unchecked")
      ArrayList<AnnualVacationMgtTO> annualVacationMgtList = (ArrayList<AnnualVacationMgtTO>) map.get("result");*/
     for(AnnualVacationMgtTO to : annualVacationMgtList){
         String a = to.getRemainingHoliday();
         System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@Remain : "+to.getFinalizeStatus());
         if(a==null){
             to.setRemainingHoliday("0");
         }
     }
      return annualVacationMgtList;
   }
   
   @Override
   public void modifyAnnualVacationMgtList(AnnualVacationMgtTO annualVacationMgtTO) {
	   
         if(annualVacationMgtTO.getStatus().equals("update")){
        	 System.out.println("Check :  "+annualVacationMgtTO.getRemainingHoliday());
				
			 annualVacationMgtRepository.save(annualVacationMgtTO);
             annualVacationMgtDAO.updateAnnualVacationList(annualVacationMgtTO);
				 
      }
   }
   
   @Override
   public void cancelAnnualVacationMgtList(ArrayList<AnnualVacationMgtTO> annualVacationMgtList) {
     
      for(AnnualVacationMgtTO annualVacationMgt : annualVacationMgtList){
    	  System.out.println("Check :  "+annualVacationMgt.getRemainingHoliday());
    	  
         if(annualVacationMgt.getStatus().equals("update")){
            annualVacationMgtRepository.save(annualVacationMgt);
            AnnualVacationTO avt = new AnnualVacationTO();
            avt.setEmpCode(annualVacationMgt.getEmpCode());
            //avt.setYear(annualVacationMgt.getYear());
            annualVactionRepository.save(avt);
         }
      }
   }
   
}