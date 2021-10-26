package kr.co.yooooon.hr.attd.controller;

import java.text.ParseException;
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
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.attd.sf.AttdServiceFacade;
import kr.co.yooooon.hr.attd.to.RestAttdTO;

@RestController
public class AttdApplovalController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/attendance/findRestAttdListByDept")
	public void findRestAttdListByDept(@RequestAttribute("variableList")VariableList variableList, @RequestAttribute("resData")PlatformData resData)throws Exception{
		String deptName=variableList.getString("deptName");
		String startDate = variableList.getString("startDate");
		String endDate = variableList.getString("endDate");
		ArrayList<RestAttdTO> restAttdList = attdServiceFacade.findRestAttdListByDept(deptName, startDate, endDate);
		datasetBeanMapper.beansToDataset(resData,restAttdList,RestAttdTO.class);
	}
	
	@RequestMapping(value="/attendance/modifyRestAttdList")
	public void modifyRestAttdList(@RequestAttribute("reqData")PlatformData reqData)throws Exception{
		ArrayList<RestAttdTO> restAttdList = (ArrayList<RestAttdTO>) datasetBeanMapper.datasetToBeans(reqData,RestAttdTO.class);
		attdServiceFacade.modifyRestAttdList(restAttdList);
	} 
}
