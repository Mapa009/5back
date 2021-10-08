package kr.co.yooooon.hr.emp.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.EmpTO;

@RestController
public class EmpListController {
	@Autowired
	private EmpServiceFacade empServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping(value="/emp/list")
	public void emplist(@RequestAttribute("variableList") VariableList varList, @RequestAttribute("resData") PlatformData resData) throws Exception{
		String value = varList.getString("value");

		ArrayList<EmpTO> list = (ArrayList<EmpTO>) empServiceFacade.findEmpList(value);
		datasetBeanMapper.beansToDataset(resData, list, EmpTO.class);
	}	
}