package kr.co.yooooon.hr.attd.controller;

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
import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;

@RestController
public class DayAttdManageController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap map = new ModelMap();
	

	@RequestMapping(value="/attendance/findDayAttdMgtList")
	public void findDayAttdMgtList(@RequestAttribute("variableList")VariableList variableList, @RequestAttribute("resData")PlatformData resData)throws Exception{
		String applyDay = variableList.getString("applyDay");
		ArrayList<DayAttdMgtTO> dayAttdMgtList = attdServiceFacade.findDayAttdMgtList(applyDay);
		datasetBeanMapper.beansToDataset(resData,dayAttdMgtList,DayAttdMgtTO.class);
	}
	
	@RequestMapping(value="/attendance/modifyDayAttdList")
	public void modifyDayAttdList(@RequestAttribute("reqData")PlatformData reqData)throws Exception{
		ArrayList<DayAttdMgtTO> dayAttdMgtList = (ArrayList<DayAttdMgtTO>) datasetBeanMapper.datasetToBeans(reqData,DayAttdMgtTO.class);
		attdServiceFacade.modifyDayAttdMgtList(dayAttdMgtList);
	}	

}
