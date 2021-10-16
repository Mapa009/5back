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
public class MonthAttdManageController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping(value="/attendance/findMonthAttdMgtList")
	public void findMonthAttdMgtList(@RequestAttribute("variableList") VariableList variablelist,@RequestAttribute("resData")PlatformData resData) throws Exception{	
		String applyYearMonth = variablelist.getString("applyYearMonth");
		ArrayList<MonthAttdMgtTO> monthAttdMgtList = null;
		
		System.out.println("Check :                                "+ applyYearMonth);
		
		monthAttdMgtList = attdServiceFacade.findMonthAttdMgtList(applyYearMonth);
			
			datasetBeanMapper.beansToDataset(resData, monthAttdMgtList, MonthAttdMgtTO.class);
		
		
		
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
	
