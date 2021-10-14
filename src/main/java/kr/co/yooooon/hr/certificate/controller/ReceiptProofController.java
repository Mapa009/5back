package kr.co.yooooon.hr.certificate.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.certificate.to.ProofTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
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
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/certificate/receiptProof")
	public void proofRequest(@RequestAttribute("reqData")PlatformData reqData)throws Exception {
		ProofTO proof = datasetBeanMapper.datasetToBean(reqData,ProofTO.class);
		certificateServiceFacade.proofRequest(proof);
	}
	
	@RequestMapping(value="/certificate/findProofList")
	public void proofLookupList(@RequestAttribute("variableList")VariableList variableList,@RequestAttribute("resData") PlatformData resData)throws Exception {
		String empCode = variableList.getString("empCode");
		String Code = variableList.getString("Code");
		String startDate = variableList.getString("startDate");
		String endDate = variableList.getString("endDate");

		ArrayList<ProofTO> proofList = certificateServiceFacade.proofLookupList(empCode,Code,startDate, endDate);

		datasetBeanMapper.beansToDataset(resData,proofList,ProofTO.class);
	}
	
	@RequestMapping(value="/certificate/removeProofAttdList")
	public void removeProofAttdList(@RequestAttribute("reqData") PlatformData reqData)throws Exception {
		ArrayList<ProofTO> proofList= (ArrayList<ProofTO>) datasetBeanMapper.datasetToBeans(reqData,ProofTO.class);
		certificateServiceFacade.removeProofRequest(proofList);
	}
}
