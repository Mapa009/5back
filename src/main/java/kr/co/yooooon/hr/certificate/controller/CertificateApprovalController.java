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
public class CertificateApprovalController {
	@Autowired
	private CertificateServiceFacade certificateServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/certificate/certificateApproval", params="deptName")
	public ModelMap findCertificateListByDept(@RequestParam("deptName")String deptName, @RequestParam("startDate")String startDate, @RequestParam("endDate")String endDate) {
		try {
			ArrayList<CertificateTO> certificateList = certificateServiceFacade.findCertificateListByDept(deptName, startDate, endDate);
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
	
	@RequestMapping(value="/certificate/certificateApproval", params="sendData")
	public ModelMap modifyCertificateList(@RequestParam("sendData")String sendData) {
		try {
			Gson gson = new Gson();
			ArrayList<CertificateTO> certificateList = gson.fromJson(sendData, new TypeToken<ArrayList<CertificateTO>>(){}.getType());
			certificateServiceFacade.modifyCertificateList(certificateList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);

			
		} catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
}
