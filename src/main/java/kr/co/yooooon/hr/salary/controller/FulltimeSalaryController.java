package kr.co.yooooon.hr.salary.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.salary.sf.SalaryServiceFacade;
import kr.co.yooooon.hr.salary.to.FullTimeSalTO;
import kr.co.yooooon.hr.salary.to.PayDayTO;

@RestController
public class FulltimeSalaryController{
	@Autowired
	private SalaryServiceFacade salaryServiceFacade;
	private ModelMap modelMap = new ModelMap();
	
	@RequestMapping(value="/salary/fullTimeSalary", params = "applyYearMonth")
	public ModelMap AllMoneyList(@RequestParam("applyYearMonth")String applyYearMonth){
		try {
			ArrayList<FullTimeSalTO> AllMoneyList = salaryServiceFacade.findAllMoney(applyYearMonth);
			modelMap.put("AllMoneyList", AllMoneyList);
			modelMap.put("errorMsg","success");
			modelMap.put("errorCode", 0);
		} catch (DataAccessException dae){
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", dae.getMessage());
		}
			return modelMap;
	}
	

	@RequestMapping(value="/salary/fullTimeSalary", params = "applyYearMonth2")
	public ModelMap selectSalary(@RequestParam("applyYearMonth2")String applyYearMonth2, @RequestParam("empCode")String empCode){
		System.out.println("앆!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		try {
			ArrayList<FullTimeSalTO> fullTimeSalaryList = salaryServiceFacade.findselectSalary(applyYearMonth2,empCode);
			modelMap.put("FullTimeSalaryList", fullTimeSalaryList);
			modelMap.put("errorMsg","success");
			modelMap.put("errorCode", 0);
		} catch (DataAccessException dae){
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", dae.getMessage());
		}
		return modelMap;
	}

	
	@RequestMapping(value="/salary/fullTimeSalary", params = "sendData")
	public ModelMap modifyFullTimeSalary(@RequestParam("sendData")String sendData){
		      try {
				 Gson gson = new Gson();
				 ArrayList<FullTimeSalTO> fullTimeSalary = gson.fromJson(sendData, new TypeToken<ArrayList<FullTimeSalTO>>(){}.getType());				 
		         salaryServiceFacade.modifyFullTimeSalary(fullTimeSalary);
		         modelMap.put("errorMsg", "success");
		         modelMap.put("errorCode", 0);
		      } catch (Exception e) {
		    	  modelMap.put("errorMsg", e.getMessage());
		      }
		      return modelMap;
		   }
	
	@RequestMapping(value="/salary/fullTimePayday")
	public ModelMap paydayList() {
		String viewName = null;		
		try {
			String value = "전체부서";
			System.out.println("선택한 부서명 : "+value);
			ArrayList<PayDayTO> list = salaryServiceFacade.findPayDayList();
			modelMap.put("list", list);
		} catch (Exception e) {
			viewName = "error";
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
		}
		return modelMap;
	}
}