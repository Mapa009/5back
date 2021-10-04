package kr.co.yooooon.hr.salary.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.salary.sf.SalaryServiceFacade;
import kr.co.yooooon.hr.salary.to.SocialInsureTO;

@RestController
public class SocialInsureController{
	@Autowired
	private SalaryServiceFacade salaryServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/salary/socialInsure" , params = "yearBox")
	public ModelMap findBaseInsureList(@RequestParam("yearBox")String yearBox){
			
		try {
			ArrayList<SocialInsureTO> baseInsureList = salaryServiceFacade.findBaseInsureList(yearBox);
			SocialInsureTO emptyBean = new SocialInsureTO();
			map.put("baseInsureList", baseInsureList); 
			emptyBean.setStatus("insert");                     
			map.put("emptyBean", emptyBean);                 
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}

	@RequestMapping(value="/salary/socialInsure" , params = "sendData")
	public ModelMap updateInsureData(@RequestParam("sendData")String sendData){
		try {	
			Gson gson = new Gson();
			ArrayList<SocialInsureTO> baseInsureList = gson.fromJson(sendData, new TypeToken<ArrayList<SocialInsureTO>>(){}.getType());
			for(SocialInsureTO sit : baseInsureList) {
				System.out.println("GSON맛갓니?");
				System.out.println(sit.getHealthInsureRates());
				System.out.println(sit.getLongtermCareRate());
			}
			salaryServiceFacade.updateInsureData(baseInsureList);
	
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/salary/socialInsure" , params = "sendData2")
	public ModelMap deleteInsureData(@RequestParam("sendData2") String sendData){
		try {
			Gson gson = new Gson();
			ArrayList<SocialInsureTO> baseInsureList = gson.fromJson(sendData, new TypeToken<ArrayList<SocialInsureTO>>(){}.getType());
			salaryServiceFacade.deleteInsureData(baseInsureList);
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