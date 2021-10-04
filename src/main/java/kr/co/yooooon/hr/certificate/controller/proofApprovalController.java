package kr.co.yooooon.hr.certificate.controller;

import java.util.ArrayList;

import kr.co.yooooon.hr.certificate.to.ProofTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.certificate.sf.CertificateServiceFacade;

@RestController
public class proofApprovalController {
	@Autowired
	private CertificateServiceFacade certificateServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/certificate/proofApproval", params="deptName")
	public ModelMap findProofAttdListByDept(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,@RequestParam("deptName")String deptName){
		try {
			ArrayList<ProofTO> proofAttdList = certificateServiceFacade.findProofListByDept(deptName, startDate, endDate);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
			map.put("proofAttdList", proofAttdList);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/certificate/proofApproval", params="sendData")
	public ModelMap modifyProofList(@RequestParam("sendData")String sendData) {	
		try {
			 Gson gson = new Gson();
			 ArrayList<ProofTO> proofList = gson.fromJson(sendData, new TypeToken<ArrayList<ProofTO>>(){}.getType());
			 System.out.println(proofList);
			 certificateServiceFacade.modifyProofList(proofList);
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
