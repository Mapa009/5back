package kr.co.yooooon.hr.certificate.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
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
import kr.co.yooooon.hr.certificate.to.CertificateTO;

@RestController
public class CertificateApprovalController {
	@Autowired
	private CertificateServiceFacade certificateServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/certificate/findCertificateListByDept")
	public void findCertificateListByDept(@RequestAttribute("variableList")VariableList variableList,@RequestAttribute("resData") PlatformData resData)throws Exception {
		String deptName = variableList.getString("deptName");
		String startDate = variableList.getString("startDate");
		String endDate = variableList.getString("endDate");
		ArrayList<CertificateTO> certificateList = certificateServiceFacade.findCertificateListByDept(deptName, startDate, endDate);
		datasetBeanMapper.beansToDataset(resData,certificateList,CertificateTO.class);
	}
	
	@RequestMapping(value="/certificate/modifyCertificateList")
	public void modifyCertificateList(@RequestAttribute("reqData") PlatformData reqData) throws Exception{
		ArrayList<CertificateTO> certificateList=(ArrayList<CertificateTO>) datasetBeanMapper.datasetToBeans(reqData,CertificateTO.class);

		certificateServiceFacade.modifyCertificateList(certificateList);
	}
}
