package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.DataTypes;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
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

	@RequestMapping(value="/base/batchHoilyDayProcess")
	public void batchHoilyDayProcess(@RequestAttribute("reqData")PlatformData reqData)throws Exception {
		ArrayList<HolidayTO> holydayList = (ArrayList<HolidayTO>) datasetBeanMapper.datasetToBeans(reqData,HolidayTO.class);
			
		baseServiceFacade.batchHoilyDayProcess(holydayList);
	}
}
