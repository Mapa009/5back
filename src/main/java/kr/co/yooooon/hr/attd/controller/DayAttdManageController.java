package kr.co.yooooon.hr.attd.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
	private ModelMap map = new ModelMap();
	

	@RequestMapping(value="/attendance/dayAttendanceManage", params="applyDay")
	public ModelMap findDayAttdMgtList(@RequestParam("applyDay")String applyDay,@RequestParam("dept")String dept){
		System.out.println("Check :                                "+ applyDay);
		
		try {
			ArrayList<DayAttdMgtTO> dayAttdMgtList = attdServiceFacade.findDayAttdMgtList(applyDay, dept);
			map.put("dayAttdMgtList", dayAttdMgtList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
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

}
