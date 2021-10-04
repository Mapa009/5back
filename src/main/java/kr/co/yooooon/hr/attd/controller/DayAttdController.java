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
import kr.co.yooooon.common.to.ResultTO;
import kr.co.yooooon.hr.attd.sf.AttdServiceFacade;
import kr.co.yooooon.hr.attd.to.DayAttdTO;

@RestController
public class DayAttdController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();

	@RequestMapping(value="/attendance/dayAttendance", params="applyDay")
	public ModelMap findDayAttdList(@RequestParam("applyDay")String applyDay , @RequestParam("empCode")String empCode){
		try {
			ArrayList<DayAttdTO> dayAttdList = attdServiceFacade.findDayAttdList(empCode, applyDay);
			map.put("dayAttdList", dayAttdList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		}  catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		} 
		return map;
	}

	@RequestMapping(value="/attendance/dayAttendance", params="sendData")
	public ModelMap registDayAttd(@RequestParam("sendData")String sendData){	
		try {	
			Gson gson = new Gson();
			DayAttdTO dayAttd = gson.fromJson(sendData, DayAttdTO.class);
			System.out.println(dayAttd);
			ResultTO resultTO = attdServiceFacade.registDayAttd(dayAttd);
			
			map.put("errorMsg",resultTO.getErrorMsg());
			map.put("errorCode", resultTO.getErrorCode());
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		} 
		
		return map;
	}

	@RequestMapping(value="/attendance/dayAttendance" ,params="sendData2")
	public ModelMap removeDayAttdList(@RequestParam("sendData2")String sendData){
		try {
			Gson gson = new Gson();
			ArrayList<DayAttdTO> dayAttdList = gson.fromJson(sendData, new TypeToken<ArrayList<DayAttdTO>>(){}.getType());
			System.out.println(dayAttdList);
			attdServiceFacade.removeDayAttdList(dayAttdList);
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
