package kr.co.yooooon.hr.attd.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.jfree.data.time.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
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
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap map = new ModelMap();

	@RequestMapping(value="/attendance/findDayAttdList")
	public void findDayAttdList(@RequestAttribute("variableList") VariableList variableList,@RequestAttribute("resData") PlatformData resData)throws Exception{
		String applyDay = variableList.getString("applyDay");
		String empCode = variableList.getString("empCode");
		ArrayList<DayAttdTO> dayAttdList = attdServiceFacade.findDayAttdList(empCode, applyDay);
		datasetBeanMapper.beansToDataset(resData,dayAttdList, DayAttdTO.class);
	}

	@RequestMapping(value="/attendance/dayAttendance")
	public void registDayAttd(@RequestAttribute("reqData") PlatformData reqData)throws Exception{
		DayAttdTO dayAttd = datasetBeanMapper.datasetToBean(reqData,DayAttdTO.class);
		ResultTO resultTO = attdServiceFacade.registDayAttd(dayAttd);
		if(resultTO.getErrorCode()!=null){
			int errorCode = Integer.parseInt(resultTO.getErrorCode());
			if(errorCode<0){
				throw new Exception(resultTO.getErrorMsg());
			}
		}
		System.out.println(resultTO.getErrorCode()+"@@@@@@@@@@@@@@@@@@@");
	}

	@RequestMapping(value="/attendance/removeDayAttdList")
	public void removeDayAttdList(@RequestAttribute("reqData") PlatformData reqData)throws Exception{

		ArrayList<DayAttdTO> dayAttdList = (ArrayList<DayAttdTO>) datasetBeanMapper.datasetToBeans(reqData,DayAttdTO.class);
		attdServiceFacade.removeDayAttdList(dayAttdList);
	}
}
