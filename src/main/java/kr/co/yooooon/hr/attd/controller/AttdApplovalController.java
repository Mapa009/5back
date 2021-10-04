package kr.co.yooooon.hr.attd.controller;

import java.text.ParseException;
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
public class AttdApplovalController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/attendance/attendanceApploval", params="startDate")
	public ModelMap findRestAttdListByDept(@RequestParam("startDate")String startDate , @RequestParam("endDate")String endDate ,@RequestParam("deptName")String deptName){
	
		try {
			ArrayList<RestAttdTO> restAttdList = attdServiceFacade.findRestAttdListByDept(deptName, startDate, endDate);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
			map.put("restAttdList", restAttdList);
		}  catch (DataAccessException | ParseException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
	
		return map;
	}
	
	@RequestMapping(value="/attendance/attendanceApploval", params="sendData")
	public ModelMap modifyRestAttdList(@RequestParam("sendData")String sendData){
		try {	
			Gson gson = new Gson();
			ArrayList<RestAttdTO> restAttdList = gson.fromJson(sendData, new TypeToken<ArrayList<RestAttdTO>>(){}.getType());
			attdServiceFacade.modifyRestAttdList(restAttdList);
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
