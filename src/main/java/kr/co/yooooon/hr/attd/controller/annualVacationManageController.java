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
import kr.co.yooooon.hr.attd.to.AnnualVacationMgtTO;
import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;

@RestController
public class annualVacationManageController {
	@Autowired
	private AttdServiceFacade attdServiceFacade;
	private ModelMap map = new ModelMap();
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping(value="/attendance/findAnnualVacationMgtList")
    public void findAnnualVacationMgtList(@RequestAttribute("variableList") VariableList variablelist,@RequestAttribute("resData")PlatformData resData) throws Exception{
		String applyYearMonth = variablelist.getString("applyYearMonth");
		
			ArrayList<AnnualVacationMgtTO> annualVacationMgtList = attdServiceFacade.findAnnualVacationMgtList(applyYearMonth);
         	
		
			datasetBeanMapper.beansToDataset(resData, annualVacationMgtList, AnnualVacationMgtTO.class);
			
			
		
      	
   }
   
	
	@RequestMapping(value="/attendance/modifyAnnualVacationMgtList")
    public void modifyAnnualVacationMgtList(@RequestAttribute("reqData") PlatformData reqData,@RequestAttribute("resData")PlatformData resData) throws Exception{
	  
	   		ArrayList<AnnualVacationMgtTO> annualVacationMgtList = (ArrayList<AnnualVacationMgtTO>)datasetBeanMapper.datasetToBeans(reqData, AnnualVacationMgtTO.class);
	   		attdServiceFacade.modifyAnnualVacationMgtList(annualVacationMgtList);
	   	
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