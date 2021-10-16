package kr.co.yooooon.hr.attd.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.attd.sf.AttdServiceFacade;
import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;
import kr.co.yooooon.hr.attd.to.MonthAttdMgtTO;

@RestController
public class DayAttdManageController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping(value="/attendance/findDayAttdMgtList")
	public void findDayAttdMgtList(@RequestAttribute("variableList") VariableList variablelist,@RequestAttribute("resData")PlatformData resData) throws Exception{
		String applyDay = variablelist.getString("applyDay");
		ArrayList<DayAttdMgtTO> dayattdmgtto = null;
		
		System.out.println("Check :                                "+ applyDay);
		
		
		dayattdmgtto = attdServiceFacade.findDayAttdMgtList(applyDay);
		datasetBeanMapper.beansToDataset(resData, dayattdmgtto, DayAttdMgtTO.class);
		
		
	}
	
	/*
	 * @RequestMapping(value="/login") public void
	 * login(@RequestAttribute("variableList") VariableList
	 * variableList, @RequestAttribute("resData")PlatformData resData) throws Exception { String name = variableList.getString("empName"); String empCode =
	 * variableList.getString("empCode"); EmpTO empto = null; if
	 * (baseServiceFacade.login(name, empCode)) { empto =
	 * empServiceFacade.getEmp(name); }
	 * datasetBeanMapper.beanToDataset(resData,empto,EmpTO.class); }
	 */
	

	
	@RequestMapping(value="/attendance/dayAttendanceManage", params="sendData")
	public ModelMap modifyDayAttdList(@RequestParam("sendData")String sendData){
		try {	
			Gson gson = new Gson();
			ArrayList<DayAttdMgtTO> dayAttdMgtList = gson.fromJson(sendData, new TypeToken<ArrayList<DayAttdMgtTO>>(){}.getType());
			attdServiceFacade.modifyDayAttdMgtList(dayAttdMgtList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}	
	
	@RequestMapping(value="/attendance/modifyDayAttdList")
	public void modifyDayAttdList(@RequestAttribute("variableList") VariableList variablelist,@RequestAttribute("resData")PlatformData resData) throws Exception{
		String applyDay = variablelist.getString("applyDay");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+applyDay);
		
		//ArrayList<DayAttdMgtTO> dayAttdMgtList = attdServiceFacade.modifyDayAttdMgtList(applyDay);
		return;
		
	}

}
