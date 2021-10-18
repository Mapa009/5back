package kr.co.yooooon.base.controller;

import java.util.ArrayList;
import java.util.List;

import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.DeptTO;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;

@RestController
public class DeptListController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private EmpServiceFacade empServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/batchDeptProcess")
	public void batchDeptProcess(@RequestAttribute("reqData")PlatformData reqData)throws Exception {
		ArrayList<DeptTO> deptto = (ArrayList<DeptTO>) datasetBeanMapper.datasetToBeans(reqData,DeptTO.class);
		baseServiceFacade.batchDeptProcess(deptto);
	}

	@RequestMapping(value="/base/findDeptList")
	public void findDeptList(@RequestAttribute("resData")PlatformData resData)throws Exception {
		List<DeptTO> list = empServiceFacade.findDeptList();
		datasetBeanMapper.beansToDataset(resData,list,DeptTO.class);
	}
}
