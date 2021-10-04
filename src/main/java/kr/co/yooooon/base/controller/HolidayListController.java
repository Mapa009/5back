package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.HolidayTO;
import kr.co.yooooon.common.exception.DataAccessException;

@RestController
public class HolidayListController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/holidayList")
	public ModelMap findHolidayList() {
		try {
			ArrayList<HolidayTO> holidayList = baseServiceFacade.findHolidayList();
			HolidayTO holito = new HolidayTO();
			map.put("holidayList", holidayList);
			map.put("emptyHoilday", holito);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		}  catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		} 
		
		return map;
	}
	
	@RequestMapping(value="/base/holidayList" , params = "startDate")
	public ModelMap findWeekDayCount(@RequestParam("startDate")String startDate , @RequestParam("endDate")String endDate) {
		try {
			String weekdayCount = baseServiceFacade.findWeekDayCount(startDate, endDate);
			map.put("weekdayCount", weekdayCount);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		} 
		return map;
	}

	@RequestMapping(value="/base/holidayList" ,params="sendData")
	public ModelMap batchHoilyDayProcess(@RequestParam("sendData")String sendData) { 
		try {
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<HolidayTO> holydayList = mapper.readValue(sendData, new TypeReference<ArrayList<HolidayTO>>() {});
			
			baseServiceFacade.batchHoilyDayProcess(holydayList);
			
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			map.put("errorCode", -1);
		}
		
		return map;
	}
}
