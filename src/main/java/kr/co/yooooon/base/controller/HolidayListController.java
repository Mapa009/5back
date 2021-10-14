package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.DataTypes;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
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
	
	@RequestMapping(value="/base/findWeekDayCount")
	public void findWeekDayCount(@RequestAttribute("variableList")VariableList variableList, @RequestAttribute("resData")PlatformData resData) {
		String startDate = variableList.getString("startDate");
		String endDate = variableList.getString("endDate");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@"+startDate);
		String weekdayCount = baseServiceFacade.findWeekDayCount(startDate, endDate);

		DataSet ds = new DataSet("ds_dayCount");
		ds.addColumn("WEEKDAY_COUNT"   , DataTypes.STRING  ,(short)10   );
		ds.newRow();
		ds.set(0 ,"WEEKDAY_COUNT",weekdayCount);
		resData.addDataSet(ds);
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
