package kr.co.yooooon.hr.attd.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
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
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

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

	@RequestMapping(value="/attendance/registRestAttd")
	public void registRestAttd(@RequestAttribute("reqData")PlatformData reqData) throws Exception{
		RestAttdTO restAttd = datasetBeanMapper.datasetToBean(reqData,RestAttdTO.class);
		attdServiceFacade.registRestAttd(restAttd);
	}
	
	@RequestMapping(value="/attendance/findRestAttdList")
	public void findRestAttdList(@RequestAttribute("variableList")VariableList variableList,@RequestAttribute("resData") PlatformData resData) throws Exception{
		String empCode = variableList.getString("empCode");
		String startDate = variableList.getString("startDate");
		String endDate = variableList.getString("endDate");
		String code = variableList.getString("code");
		ArrayList<RestAttdTO> restAttdList = attdServiceFacade.findRestAttdList(empCode, startDate, endDate, code);

		datasetBeanMapper.beansToDataset(resData,restAttdList,RestAttdTO.class);
	}

	@RequestMapping(value="/attendance/removeRestAttdList")
	public void removeRestAttdList(@RequestAttribute("reqData") PlatformData reqData) throws Exception{

		ArrayList<RestAttdTO> restAttdList = (ArrayList<RestAttdTO>) datasetBeanMapper.datasetToBeans(reqData,RestAttdTO.class);
		attdServiceFacade.removeRestAttdList(restAttdList);
	}

}
