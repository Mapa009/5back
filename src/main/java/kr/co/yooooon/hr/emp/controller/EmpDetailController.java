package kr.co.yooooon.hr.emp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.CareerInfoTO;
import kr.co.yooooon.hr.emp.to.EducationInfoTO;
import kr.co.yooooon.hr.emp.to.EmpTO;
import kr.co.yooooon.hr.emp.to.FamilyInfoTO;
import kr.co.yooooon.hr.emp.to.LicenseInfoTO;
import kr.co.yooooon.hr.emp.to.WorkInfoTO;

@RestController
public class EmpDetailController {
	@Autowired
	private EmpServiceFacade empServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/emp/empDetail", params="empCode")
	public ModelMap findAllEmployeeInfo(@RequestParam("empCode")String empCode){
		
		try{
			EmpTO empTO=empServiceFacade.findAllEmpInfo(empCode);
			map.put("empBean", empTO); 
			WorkInfoTO workInfoTO = new WorkInfoTO();
			CareerInfoTO careerInfoTO = new CareerInfoTO();
			EducationInfoTO educationInfoTO = new EducationInfoTO();
			LicenseInfoTO licenseInfoTO = new LicenseInfoTO();			
			FamilyInfoTO familyInfoTO = new FamilyInfoTO();
			
			map.put("emptyFamilyInfoBean",familyInfoTO );
			map.put("emptyCareerInfoBean", careerInfoTO);
			map.put("emptyEducationInfoBean", educationInfoTO);
			map.put("emptyLicenseInfoBean", licenseInfoTO);
			map.put("emptyWorkInfoBean", workInfoTO);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/emp/empDetailModify", params="sendData")
	public ModelMap modifyEmployee(@RequestParam("sendData")String sendData){	
		try{
			Gson gson = new Gson();
			EmpTO emp = gson.fromJson(sendData, EmpTO.class);
			empServiceFacade.modifyEmployee(emp);
			map.put("errorMsg","success");
			map.put("errorCode", 0);

		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/emp/empDetailRemove", params="sendData2")
	public ModelMap removeEmployeeList(@RequestParam("sendData2")String sendData){
		try{
			Gson gson = new Gson();
			ArrayList<EmpTO> empList = gson.fromJson(sendData, new TypeToken<ArrayList<EmpTO>>(){}.getType());
			empServiceFacade.deleteEmpList(empList);
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
