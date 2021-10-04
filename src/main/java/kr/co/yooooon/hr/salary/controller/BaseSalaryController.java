package kr.co.yooooon.hr.salary.controller;

import java.util.ArrayList;

import kr.co.yooooon.hr.emp.to.PositionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.salary.sf.SalaryServiceFacade;

@RestController
public class BaseSalaryController {
	@Autowired
	private SalaryServiceFacade salaryServiceFacade;
	private ModelMap map = new ModelMap();

	@RequestMapping(value="/salary/baseSalaryManage")
	public ModelMap findBaseSalaryList(){
		try {
			ArrayList<PositionTO> baseSalaryList = salaryServiceFacade.findBaseSalaryList();
			map.put("baseSalaryList", baseSalaryList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		
		return map;
	}

	@RequestMapping(value="/salary/baseSalaryManage" , params="sendData")
	public ModelMap modifyBaseSalaryList(@RequestParam("sendData")String sendData){

		try { 			
			Gson gson = new Gson();
			ArrayList<PositionTO> baseSalaryList = gson.fromJson(sendData, new TypeToken<ArrayList<PositionTO>>(){}.getType());
			salaryServiceFacade.modifyBaseSalaryList(baseSalaryList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		
		return map;
	}
}
