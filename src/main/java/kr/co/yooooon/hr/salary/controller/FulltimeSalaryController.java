package kr.co.yooooon.hr.salary.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;

import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.salary.sf.SalaryServiceFacade;
import kr.co.yooooon.hr.salary.to.FullTimeSalTO;
import kr.co.yooooon.hr.salary.to.PayDayTO;

@RestController
public class FulltimeSalaryController{
	@Autowired
	private SalaryServiceFacade salaryServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping(value="/salary/AllMoneyList")
	public void AllMoneyList(@RequestAttribute("variableList") VariableList varList, @RequestAttribute("resData") PlatformData resData) throws Exception{

		
			String applyYearMonth=varList.getString("applyYearMonth");
			System.out.println(applyYearMonth);
			ArrayList<FullTimeSalTO> AllMoneyList = salaryServiceFacade.findAllMoney(applyYearMonth);
			
			datasetBeanMapper.beansToDataset(resData,AllMoneyList,FullTimeSalTO.class);

	}
	

	@RequestMapping(value="/salary/selectSalary")
	public void selectSalary(@RequestAttribute("variableList") VariableList varList, @RequestAttribute("resData") PlatformData resData) throws Exception{

		String applyYearMonth2 = varList.getString("applyYearMonth2");
		String empCode = varList.getString("empCode");
		System.out.println("111111111ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		System.out.println(empCode);
		System.out.println(applyYearMonth2);
		ArrayList<FullTimeSalTO> fullTimeSalaryList = salaryServiceFacade.findselectSalary(applyYearMonth2,empCode);

		datasetBeanMapper.beansToDataset(resData,fullTimeSalaryList,FullTimeSalTO.class);
		
	}

	
	@RequestMapping(value="/salary/modifyFullTimeSalary")
	public void modifyFullTimeSalary(@RequestAttribute("reqData") PlatformData reqData) throws Exception{

		ArrayList<FullTimeSalTO> fullTimeSalary = (ArrayList<FullTimeSalTO>) datasetBeanMapper.datasetToBeans(reqData, FullTimeSalTO.class);		 
		salaryServiceFacade.modifyFullTimeSalary(fullTimeSalary);


	}
	
	@RequestMapping(value="/salary/paydayList")
	public void paydayList(@RequestAttribute("resData") PlatformData resData) throws Exception{
		
		String value = "전체부서";
		System.out.println("선택한 부서명 : "+value);
		ArrayList<PayDayTO> list = salaryServiceFacade.findPayDayList();

		datasetBeanMapper.beansToDataset(resData, list,PayDayTO.class);
		
	}
}