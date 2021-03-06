package kr.co.yooooon.hr.attd.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.yooooon.hr.attd.sf.AttdServiceFacade;
import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;

@RestController
public class DayAttdManageController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap map = new ModelMap();

	@RequestMapping(value="/attendance/findDayAttdMgtList")
	public void findDayAttdMgtList(@RequestAttribute("variableList")VariableList variableList, @RequestAttribute("resData")PlatformData resData)throws Exception{
		String applyDay = variableList.getString("applyDay");
		ArrayList<DayAttdMgtTO> dayAttdMgtList = attdServiceFacade.findDayAttdMgtList(applyDay);
		datasetBeanMapper.beansToDataset(resData,dayAttdMgtList,DayAttdMgtTO.class);
	}
	
	// 1018 ????????????
	@RequestMapping(value="/attendance/modifyDayAttdList")
	public void modifyDayAttdList(@RequestAttribute("reqData")PlatformData reqData, @RequestAttribute("resData")PlatformData resData)throws Exception{
		ArrayList<DayAttdMgtTO> dayAttdMgtList = (ArrayList<DayAttdMgtTO>) datasetBeanMapper.datasetToBeans(reqData,DayAttdMgtTO.class);
		attdServiceFacade.modifyDayAttdMgtList(dayAttdMgtList);
	}
	
	/*
	 * //마감조회,취소
	 * 
	 * @RequestMapping(value="/attendance/modifyDayAttdList") public void
	 * modifyDayAttdList(@RequestAttribute("variableList") VariableList
	 * variablelist,@RequestAttribute("resData")PlatformData resData) throws
	 * Exception{ HashMap<String, Object> result = null;
	 * 
	 * ArrayList<DayAttdMgtTO> dayAttdMgtList = null;
	 * 
	 * //ArrayList<DayAttdMgtTO> dayAttdMgtList =
	 * attdServiceFacade.modifyDayAttdMgtList(applyDay);
	 * 
	 * 
	 * dayAttdMgtList = (ArrayList<DayAttdMgtTO>)
	 * datasetBeanMapper.datasetToBeans(resData, DayAttdMgtTO.class);
	 * attdServiceFacade.modifyDayAttdMgtList(dayAttdMgtList);
	 * 
	 * 
	 * }
	 */


	
	/*
	 * @RequestMapping(value="/attendance/dayAttendanceManage", params="sendData")
	 * public ModelMap modifyDayAttdList(@RequestParam("sendData")String sendData){
	 * try { Gson gson = new Gson(); ArrayList<DayAttdMgtTO> dayAttdMgtList =
	 * gson.fromJson(sendData, new
	 * TypeToken<ArrayList<DayAttdMgtTO>>(){}.getType());
	 * attdServiceFacade.modifyDayAttdMgtList(dayAttdMgtList);
	 * map.put("errorMsg","success"); map.put("errorCode", 0); } catch
	 * (DataAccessException dae){ map.clear(); map.put("errorCode", -1);
	 * map.put("errorMsg", dae.getMessage()); } return map; }
	 */
	
}
