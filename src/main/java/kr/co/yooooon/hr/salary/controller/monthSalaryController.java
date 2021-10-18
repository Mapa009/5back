package kr.co.yooooon.hr.salary.controller;

import java.time.Month;
import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.salary.sf.SalaryServiceFacade;
import kr.co.yooooon.hr.salary.to.MonthSalaryTO;

@RestController
public class monthSalaryController {
	@Autowired
	private SalaryServiceFacade salaryServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	ModelMap modelMap = new ModelMap();

	@RequestMapping(value="/salary/findMonthSalary")
	public void findMonthSalary(@RequestAttribute("variableList")VariableList variableList, @RequestAttribute("resData")PlatformData resData)throws Exception {
		String applyYearMonth = variableList.getString("applyYearMonth");
		String empCode = variableList.getString("empCode");
		MonthSalaryTO monthSalaryList = salaryServiceFacade.findMonthSalary(applyYearMonth, empCode);
		datasetBeanMapper.beanToDataset(resData,monthSalaryList, MonthSalaryTO.class);
	}
	
	@RequestMapping(value="/salary/monthSalary" , params="applyYear")
	public ModelMap findYearSalary(@RequestParam("applyYear")String applyYear , @RequestParam("empCode")String empCode) {
		try {
			ArrayList<MonthSalaryTO> yearSalary = salaryServiceFacade.findYearSalary(applyYear, empCode);
			modelMap.put("yearSalary", yearSalary);
			modelMap.put("errorMsg", "success");
			modelMap.put("errorCode", 0);
		} catch (DataAccessException dae) {
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", dae.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value="/salary/monthSalary" , params="sendData")
	public ModelMap modifyMonthSalary(@RequestParam("sendData")String sendData) {
		try {
			Gson gson = new Gson();
			MonthSalaryTO monthSalary = gson.fromJson(sendData, MonthSalaryTO.class);
			salaryServiceFacade.modifyMonthSalary(monthSalary);
			modelMap.put("errorMsg", "success");
			modelMap.put("errorCode", 0);
		} catch (DataAccessException dae) {
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", dae.getMessage());
		}
		return modelMap;
	}
}