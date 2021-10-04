package kr.co.yooooon.hr.certificate.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.certificate.sf.CertificateServiceFacade;
import kr.co.yooooon.hr.certificate.to.CertificateTO;

@RestController
public class CertificateController {
	@Autowired
	private CertificateServiceFacade certificateServiceFacade;
	private ModelMap map = new ModelMap();

	@RequestMapping(value="/certificate/certificate", params="sendData")
	public ModelMap registRequest(@RequestParam("sendData")String sendData) {
		try {
			Gson gson = new Gson();
			CertificateTO certificate = gson.fromJson(sendData, CertificateTO.class);
			certificateServiceFacade.registRequest(certificate);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae) {
			
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/certificate/certificate", params="startDate")
	public ModelMap findCertificateList(@RequestParam("startDate")String startDate ,@RequestParam("endDate")String endDate ,@RequestParam("empCode")String empCode) {
		try {
			ArrayList<CertificateTO> certificateList = certificateServiceFacade.findCertificateList(empCode, startDate, endDate);
			map.put("certificateList", certificateList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/certificate/certificate", params="sendData2")
	public ModelMap removeCertificateRequest(@RequestParam("sendData2")String sendData){
		try {
			Gson gson=new Gson();
			ArrayList<CertificateTO> certificateList=gson.fromJson(sendData, new TypeToken<ArrayList<CertificateTO>>() {}.getType());
			certificateServiceFacade.removeCertificateRequest(certificateList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		
		}catch(DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
}
