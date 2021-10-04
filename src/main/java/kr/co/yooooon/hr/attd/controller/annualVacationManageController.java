package kr.co.yooooon.hr.attd.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.attd.sf.AttdServiceFacade;
import kr.co.yooooon.hr.attd.to.AnnualVacationMgtTO;

@RestController
public class annualVacationManageController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();
  
	@RequestMapping(value="/attendance/annualVacationManage", params="applyYearMonth")
    public ModelMap findAnnualVacationMgtList(@RequestParam("applyYearMonth")String applyYearMonth){
     
		try {
			ArrayList<AnnualVacationMgtTO> annualVacationMgtList = attdServiceFacade.findAnnualVacationMgtList(applyYearMonth);
         	
			map.put("annualVacationMgtList", annualVacationMgtList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
      	return map;
   }
   
	@RequestMapping(value="/attendance/annualVacationManage", params="sendData")
    public ModelMap modifyAnnualVacationMgtList(@RequestParam("sendData")String sendData){
	  
	   	try {
	   		Gson gson = new Gson();
	   		ArrayList<AnnualVacationMgtTO> annualVacationMgtList = gson.fromJson(sendData, new TypeToken<ArrayList<AnnualVacationMgtTO>>(){}.getType());
	   		attdServiceFacade.modifyAnnualVacationMgtList(annualVacationMgtList);
	   		map.put("errorMsg","success");
	   		map.put("errorCode", 0);
         
	   	} catch (DataAccessException dae){
	   		map.clear();
	   		map.put("errorCode", -1);
	   		map.put("errorMsg", dae.getMessage());
	   	}
	   	return map;
   	} 
   
   @RequestMapping(value="/attendance/annualVacationManage", params="sendData2")
   public ModelMap cancelAnnualVacationMgtList(@RequestParam("sendData2")String sendData){
      
	   try {
		   Gson gson = new Gson();
		   ArrayList<AnnualVacationMgtTO> annualVacationMgtList = gson.fromJson(sendData, new TypeToken<ArrayList<AnnualVacationMgtTO>>(){}.getType());
		   attdServiceFacade.cancelAnnualVacationMgtList(annualVacationMgtList);
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