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
import kr.co.yooooon.hr.attd.to.RestAttdTO;

@RestController
public class RestAttdController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();
	
	/*
	 * @RequestMapping(value="/attendance/restAttendance.do", params="toDay") public
	 * ModelMap findRestAttdListByToday(@RequestParam("empCode")String empCode
	 * , @RequestParam("toDay")String toDay) {
	 * 
	 * try { ArrayList<RestAttdTO> restAttdList =
	 * attdServiceFacade.findRestAttdListByToday(empCode, toDay);
	 * map.put("errorMsg", "success"); map.put("errorCode", 0);
	 * map.put("restAttdList", restAttdList); } catch (DataAccessException dae) {
	 * map.clear(); map.put("errorCode", -1); map.put("errorMsg", dae.getMessage());
	 * } return map; }
	 */

	@RequestMapping(value="/attendance/restAttendance", params="sendData")
	public ModelMap registRestAttd(@RequestParam("sendData")String sendData) {
		try {
			Gson gson = new Gson();
			RestAttdTO restAttd = gson.fromJson(sendData, RestAttdTO.class);
			attdServiceFacade.registRestAttd(restAttd);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		}catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/attendance/restAttendance", params="startDate")
	public ModelMap findRestAttdList(@RequestParam("empCode")String empCode , @RequestParam("startDate")String startDate , @RequestParam("endDate")String endDate ,@RequestParam("code")String code) {
		try {
			ArrayList<RestAttdTO> restAttdList = attdServiceFacade.findRestAttdList(empCode, startDate, endDate, code); 
			map.put("restAttdList", restAttdList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		}  catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
	
		return map;
	}

	@RequestMapping(value="/attendance/restAttendance", params="sendData2")
	public ModelMap removeRestAttdList(@RequestParam("sendData2")String sendData) {	
		try {
			Gson gson = new Gson();
			ArrayList<RestAttdTO> restAttdList = gson.fromJson(sendData, new TypeToken<ArrayList<RestAttdTO>>(){}.getType());
			attdServiceFacade.removeRestAttdList(restAttdList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);

		}catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}

}
