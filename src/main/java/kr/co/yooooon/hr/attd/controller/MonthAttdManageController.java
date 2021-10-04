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
import kr.co.yooooon.hr.attd.to.MonthAttdMgtTO;

@RestController
public class MonthAttdManageController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();

	@RequestMapping(value="/attendance/monthAttendanceManage", params="applyYearMonth")
	public ModelMap findMonthAttdMgtList(@RequestParam("applyYearMonth")String applyYearMonth){	
		try {
			ArrayList<MonthAttdMgtTO> monthAttdMgtList = attdServiceFacade.findMonthAttdMgtList(applyYearMonth);
			map.put("monthAttdMgtList", monthAttdMgtList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		} 	
		return map;
	}

	@RequestMapping(value="/attendance/monthAttendanceManage", params="sendData")
	public ModelMap modifyMonthAttdList(@RequestParam("sendData")String sendData){		
		try {
			Gson gson = new Gson();
			ArrayList<MonthAttdMgtTO> monthAttdMgtList = gson.fromJson(sendData, new TypeToken<ArrayList<MonthAttdMgtTO>>(){}.getType());
			attdServiceFacade.modifyMonthAttdMgtList(monthAttdMgtList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);

		}	catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}	 
		return map;
	} 

}
	
