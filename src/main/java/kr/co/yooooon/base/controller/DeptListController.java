package kr.co.yooooon.base.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/deptList" , params = "sendData")
	public ModelMap batchDeptProcess(@RequestParam("sendData")String sendData) {
		Gson gson = new Gson();
		ArrayList<DeptTO> deptto = gson.fromJson(sendData, new TypeToken<ArrayList<DeptTO>>(){}.getType()); // 변경 
		
		try {
			baseServiceFacade.batchDeptProcess(deptto); 
			map.put("errorCode", 0);
			map.put("errorMsg", "Successble !");
		} catch (Exception e) {
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		
		return map;
	}

	@RequestMapping(value="/base/deptList")
	public ModelMap findDeptList() {	
		try {
			List<DeptTO> list = empServiceFacade.findDeptList();
			DeptTO emptyBean = new DeptTO();
			map.put("emptyBean", emptyBean);
			map.put("list", list);		
		}catch (Exception e) {
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		
		return map;
	}
}
