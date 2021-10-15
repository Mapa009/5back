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
import kr.co.yooooon.hr.salary.to.BaseDeductionTO;

@RestController
public class BaseDeductionController  {
	@Autowired //??
	private SalaryServiceFacade salaryServiceFacade;
	private ModelMap map = new ModelMap();
	@Autowired //흐으응으ㅡ음
	private DatasetBeanMapper datasetBeanMapper;
	
	/*
	 * @RequestMapping(value="/salary/baseDeductionManage") public ModelMap
	 * findBaseDeductionList(){
	 * 
	 * try { ArrayList<BaseDeductionTO> baseDeductionList =
	 * salaryServiceFacade.findBaseDeductionList(); BaseDeductionTO emptyBean = new
	 * BaseDeductionTO();
	 * 
	 * map.put("baseDeductionList", baseDeductionList);
	 * emptyBean.setStatus("insert"); map.put("emptyBean", emptyBean);
	 * map.put("errorMsg","success"); map.put("errorCode", 0); } catch
	 * (DataAccessException dae){ map.clear(); map.put("errorCode", -1);
	 * map.put("errorMsg", dae.getMessage()); } return map; }
	 */
	
	@RequestMapping(value="/salary/baseDeductionManage" , params = "sendData")
	public ModelMap batchBaseDeductionProcess(@RequestParam("sendData")String sendData){
		try {
			Gson gson = new Gson();
			ArrayList<BaseDeductionTO> baseDeductionList = gson.fromJson(sendData, new TypeToken<ArrayList<BaseDeductionTO>>(){}.getType());
			salaryServiceFacade.batchBaseDeductionProcess(baseDeductionList);

			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	// 페이지 로드시 실행
	@RequestMapping("/salary/findBaseDeductionList") //211013
	public void findBaseDeductionList(@RequestAttribute("reqData") PlatformData reqData,
			@RequestAttribute("resData") PlatformData resData) throws Exception {
		ArrayList<BaseDeductionTO> baseDeductionList = salaryServiceFacade.findBaseDeductionList();
		
		datasetBeanMapper.beansToDataset(resData,baseDeductionList,BaseDeductionTO.class);
	}
}
