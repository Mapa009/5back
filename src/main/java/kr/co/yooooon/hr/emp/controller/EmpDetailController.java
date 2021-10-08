package kr.co.yooooon.hr.emp.controller;

import java.util.ArrayList;
import java.util.List;

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
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/emp/findAllEmployeeInfo")
	public void findAllEmployeeInfo(@RequestAttribute("variableList") VariableList varList, @RequestAttribute("resData") PlatformData resData)throws Exception{
		String empCode = varList.getString("empCode");
		EmpTO empTO=empServiceFacade.findAllEmpInfo(empCode);

		List<WorkInfoTO> workInfoList = empTO.getWorkInfoList();
		List<CareerInfoTO> careerInfoList = empTO.getCareerInfoList();
		List<EducationInfoTO> educationInfoList = empTO.getEducationInfoList();
		List<LicenseInfoTO> licenseInfoList = empTO.getLicenseInfoList();
		List<FamilyInfoTO> familyInfoList = empTO.getFamilyInfoList();

		datasetBeanMapper.beansToDataset(resData,workInfoList,WorkInfoTO.class);
		datasetBeanMapper.beansToDataset(resData,careerInfoList,CareerInfoTO.class);
		datasetBeanMapper.beansToDataset(resData,educationInfoList,EducationInfoTO.class);
		datasetBeanMapper.beansToDataset(resData,familyInfoList,FamilyInfoTO.class);
		datasetBeanMapper.beansToDataset(resData,licenseInfoList,LicenseInfoTO.class);
	}
	
	@RequestMapping(value="/emp/empDetailModify")
	public void modifyEmployee(@RequestAttribute("reqData") PlatformData reqData)throws Exception{
		EmpTO emp = datasetBeanMapper.datasetToBean(reqData, EmpTO.class);

		List<WorkInfoTO> workinfo = datasetBeanMapper.datasetToBeans(reqData, WorkInfoTO.class);
		List<CareerInfoTO> careerinfo = datasetBeanMapper.datasetToBeans(reqData, CareerInfoTO.class);
		List<EducationInfoTO> educationinfo = datasetBeanMapper.datasetToBeans(reqData, EducationInfoTO.class);
		List<FamilyInfoTO> familyinfo = datasetBeanMapper.datasetToBeans(reqData, FamilyInfoTO.class);
		List<LicenseInfoTO> licenseinfo = datasetBeanMapper.datasetToBeans(reqData, LicenseInfoTO.class);


		emp.setWorkInfoList(workinfo);
		emp.setCareerInfoList(careerinfo);
		emp.setEducationInfoList(educationinfo);
		emp.setFamilyInfoList(familyinfo);
		emp.setLicenseInfoList(licenseinfo);

		empServiceFacade.modifyEmployee(emp);
	}
	
	@RequestMapping(value="/emp/removeEmployeeList")
	public void removeEmployeeList(@RequestAttribute("reqData") PlatformData reqData)throws Exception{
		ArrayList<EmpTO> empList = (ArrayList<EmpTO>) datasetBeanMapper.datasetToBeans(reqData, EmpTO.class);

		empServiceFacade.deleteEmpList(empList);
	}
}
