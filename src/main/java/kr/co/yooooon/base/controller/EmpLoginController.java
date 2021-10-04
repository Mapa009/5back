package kr.co.yooooon.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.EmpTO;

@RestController
public class EmpLoginController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private EmpServiceFacade empServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
		
	@RequestMapping(value="/login")
	public void login(@RequestAttribute("variableList") VariableList variableList, @RequestAttribute("resData")PlatformData resData) throws Exception {
		String name = variableList.getString("empName");
		String empCode = variableList.getString("empCode");
		EmpTO empto = null;
		if (baseServiceFacade.login(name, empCode)) {
			 empto = empServiceFacade.getEmp(name);
		}
		datasetBeanMapper.beanToDataset(resData,empto,EmpTO.class);
	}
}
