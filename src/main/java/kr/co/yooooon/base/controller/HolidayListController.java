package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
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
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/findHolidayList")
	public void findHolidayList(@RequestAttribute("resData")PlatformData resData)throws Exception {
		ArrayList<HolidayTO> holidayList = baseServiceFacade.findHolidayList();
		datasetBeanMapper.beansToDataset(resData,holidayList,HolidayTO.class);
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

	@RequestMapping(value="/base/batchHoilyDayProcess")
	public void batchHoilyDayProcess(@RequestAttribute("reqData")PlatformData reqData)throws Exception {
		System.out.println(reqData.getDataSetList().size()+"@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(reqData.getDataSetList().get(0)+"@@@@@@@@@@@@@@@@@@@@@@@");
		ArrayList<HolidayTO> holydayList = (ArrayList<HolidayTO>) datasetBeanMapper.datasetToBeans(reqData,HolidayTO.class);
		baseServiceFacade.batchHoilyDayProcess(holydayList);
	}
}
