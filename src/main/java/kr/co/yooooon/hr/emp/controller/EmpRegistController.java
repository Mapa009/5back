package kr.co.yooooon.hr.emp.controller;

import com.google.gson.GsonBuilder;
import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import net.sf.json.JSONObject;
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
import kr.co.yooooon.hr.emp.to.EmpTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@RestController
public class EmpRegistController {
	@Autowired
	private EmpServiceFacade empServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/emp/registEmployee")
	public void registEmployee(@RequestAttribute("reqData") PlatformData reqData) throws Exception {
		EmpTO emp = datasetBeanMapper.datasetToBean(reqData, EmpTO.class);

		empServiceFacade.registEmployee(emp);
	}
	
	@RequestMapping(value="/emp/findLastEmpCode")
	public void findLastEmpCode(@RequestAttribute("resData") PlatformData resData)throws Exception{
		EmpTO lastEmpCode= new EmpTO();
		lastEmpCode.setEmpCode(empServiceFacade.findLastEmpCode());
		datasetBeanMapper.beanToDataset(resData, lastEmpCode, EmpTO.class);
	}
	
}
