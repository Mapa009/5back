package kr.co.yooooon.hr.emp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.EmpTO;

@RestController
public class EmpListController {
	@Autowired
	private EmpServiceFacade empServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/emp/list", method=RequestMethod.GET)
	public ModelMap emplist(@RequestParam("value")String pvalue) {
		
		try {
			String value = "전체부서"; 
			
			if (pvalue != null) {
				value = pvalue;
			}
			
			ArrayList<EmpTO> list = empServiceFacade.findEmpList(value);			
			map.put("list", list);
		} catch (Exception e) {
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}		
		return map;
	}	
}