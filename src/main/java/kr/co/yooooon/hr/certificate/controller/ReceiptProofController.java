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
public class ReceiptProofController {
	@Autowired
	private CertificateServiceFacade certificateServiceFacade;
	
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/certificate/receiptProof", params="sendData")
	public ModelMap proofRequest(@RequestParam("sendData")String sendData) {
		try {
			Gson gson = new Gson();
			ProofTO proof = gson.fromJson(sendData, ProofTO.class);
			certificateServiceFacade.proofRequest(proof);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/certificate/receiptProof", params="startDate")
	public ModelMap proofLookupList(@RequestParam("empCode")String empCode,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,@RequestParam("code")String Code) {	
		
		try {
			ArrayList<ProofTO> proofList = certificateServiceFacade.proofLookupList(empCode,Code,startDate, endDate);
			map.put("proofList", proofList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/certificate/receiptProof", params="sendData2")
	public ModelMap removeProofAttdList(@RequestParam("sendData2")String sendData) {
		try {
			Gson gson=new Gson();
			ArrayList<ProofTO> proofList=gson.fromJson(sendData, new TypeToken<ArrayList<ProofTO>>() {}.getType());
			certificateServiceFacade.removeProofRequest(proofList);
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
