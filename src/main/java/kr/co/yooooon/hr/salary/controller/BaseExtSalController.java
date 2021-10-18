package kr.co.yooooon.hr.salary.controller;

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

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.salary.sf.SalaryServiceFacade;
import kr.co.yooooon.hr.salary.to.BaseExtSalTO;

@RestController
public class BaseExtSalController {
	@Autowired
	private SalaryServiceFacade salaryServiceFacade;
	private ModelMap map = new ModelMap();
	
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/*
	 * @RequestMapping(value="/salary/baseExtSalManage") public ModelMap
	 * findBaseExtSalList(){ try { ArrayList<BaseExtSalTO> baseExtSalList =
	 * salaryServiceFacade.findBaseExtSalList(); map.put("baseExtSalList",
	 * baseExtSalList); map.put("errorMsg","success"); map.put("errorCode", 0); }
	 * catch (DataAccessException dae){ map.clear(); map.put("errorCode", -1);
	 * map.put("errorMsg", dae.getMessage()); } return map; }
	 */

	/*
	 * @RequestMapping(value="/salary/baseExtSalManage" , params="sendData") public
	 * ModelMap modifyBaseExtSalList(@RequestParam("sendData")String sendData){ try
	 * { Gson gson = new Gson(); ArrayList<BaseExtSalTO> baseExtSalList =
	 * gson.fromJson(sendData, new
	 * TypeToken<ArrayList<BaseExtSalTO>>(){}.getType());
	 * salaryServiceFacade.modifyBaseExtSalList(baseExtSalList);
	 * map.put("errorMsg","success"); map.put("errorCode", 0); } catch
	 * (DataAccessException dae){ map.clear(); map.put("errorCode", -1);
	 * map.put("errorMsg", dae.getMessage()); } return map; }
	 */
	
	
	//baseExtSalManage.xfdl 초과수당관리 페이지 로드시.
	@RequestMapping("/salary/findBaseExtSalList")
	public void findBaseExtSalList(@RequestAttribute("reqData") PlatformData reqData,
			@RequestAttribute("resData") PlatformData resData) throws Exception {
		
		ArrayList<BaseExtSalTO> baseExtSalList = salaryServiceFacade.findBaseExtSalList();
		
		datasetBeanMapper.beansToDataset(resData,baseExtSalList,BaseExtSalTO.class);
		
	}
	
	//baseExtSalManage.xfdl 에서 변경확정 버튼 클릭시
	@RequestMapping("/salary/modifyBaseExtSalList")
	public void modifyBaseExtSalList(@RequestAttribute("reqData") PlatformData reqData,
			@RequestAttribute("resData") PlatformData resData) throws Exception {
		
		ArrayList<BaseExtSalTO> baseExtSalList = (ArrayList<BaseExtSalTO>) datasetBeanMapper.datasetToBeans(reqData, BaseExtSalTO.class);
		salaryServiceFacade.modifyBaseExtSalList(baseExtSalList);
	}
	
	
}
