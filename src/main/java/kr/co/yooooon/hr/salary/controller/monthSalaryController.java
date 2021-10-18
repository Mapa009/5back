package kr.co.yooooon.hr.salary.controller;

<<<<<<< HEAD
import java.time.Month;
import java.util.ArrayList;
=======
import java.util.HashMap;
import java.util.List;
>>>>>>> ji

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tobesoft.xplatform.data.PlatformData;

import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.salary.sf.SalaryServiceFacade;
import kr.co.yooooon.hr.salary.to.MonthDeductionTO;
import kr.co.yooooon.hr.salary.to.MonthExtSalTO;
import kr.co.yooooon.hr.salary.to.MonthSalaryTO;

@RestController
public class monthSalaryController {
	@Autowired
	private SalaryServiceFacade salaryServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	ModelMap modelMap = new ModelMap();
	HashMap<String,Object> map = new HashMap<>();
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping(value="/salary/findMonthSalary")
	public void findMonthSalary(@RequestAttribute("reqData") PlatformData reqData,
			@RequestAttribute("resData") PlatformData resData) throws Exception {
			
		String applyYearMonth = reqData.getVariable("applyYearMonth").getString();
		String empCode = reqData.getVariable("empCode").getString();
		
		HashMap<String,Object> result=new HashMap<>();
		
		map.clear();
		map.put("applyYearMonth","2021-9");
		map.put("empCode","A490071");
		result = salaryServiceFacade.findMonthSalary(map);
		
		MonthSalaryTO monthSalaryTO=(MonthSalaryTO)result.get("result");
		List<MonthDeductionTO> monthDeductionList=monthSalaryTO.getMonthDeductionList();
		List<MonthExtSalTO> monthExtSalList=monthSalaryTO.getMonthExtSalList();
		
		datasetBeanMapper.beanToDataset(resData,monthSalaryTO,MonthSalaryTO.class);
		datasetBeanMapper.beansToDataset(resData,monthDeductionList,MonthDeductionTO.class);
		datasetBeanMapper.beansToDataset(resData,monthExtSalList,MonthExtSalTO.class);
			

<<<<<<< HEAD
	@RequestMapping(value="/salary/findMonthSalary")
	public void findMonthSalary(@RequestAttribute("variableList")VariableList variableList, @RequestAttribute("resData")PlatformData resData)throws Exception {
		String applyYearMonth = variableList.getString("applyYearMonth");
		String empCode = variableList.getString("empCode");
		MonthSalaryTO monthSalaryList = salaryServiceFacade.findMonthSalary(applyYearMonth, empCode);
		datasetBeanMapper.beanToDataset(resData,monthSalaryList, MonthSalaryTO.class);
=======
>>>>>>> ji
	}
	
	@RequestMapping(value="/salary/findYearSalary")
	public void findYearSalary(@RequestAttribute("reqData") PlatformData reqData,
            @RequestAttribute("resData") PlatformData resData) throws Exception {
		HashMap<String,Object> result = null;

		String empCode = reqData.getVariable("empCode").getString();
		String applyYearMonth = reqData.getVariable("applyYear").getString()+"%";
		map.clear();
		//String applyYearMonth = applyYear+"%";

		map.put("applyYearMonth",applyYearMonth);
		map.put("empCode","A490071");
		result = salaryServiceFacade.findYearSalary(map);
		@SuppressWarnings("unchecked")
		List<MonthSalaryTO> monthSalaryTO=(List<MonthSalaryTO>)result.get("result");
		datasetBeanMapper.beansToDataset(resData,monthSalaryTO,MonthSalaryTO.class);

	}

	@RequestMapping(value="/salary/modifyMonthSalary")
	public void modifyMonthSalary(@RequestAttribute("reqData") PlatformData reqData,
            @RequestAttribute("resData") PlatformData resData) throws Exception {
	
			MonthSalaryTO monthSalary = datasetBeanMapper.datasetToBean(reqData, MonthSalaryTO.class);
			salaryServiceFacade.modifyMonthSalary(monthSalary);
	}
}