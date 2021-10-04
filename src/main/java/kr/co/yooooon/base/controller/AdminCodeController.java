package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.AdminCodeTO;

@RestController
public class AdminCodeController {
	@Autowired
	private BaseServiceFacade baseSF;
	private ModelMap map=new ModelMap();

	@RequestMapping(value="/base/adminCodeList", method=RequestMethod.GET)
	public ModelMap	adminCodeList() {
		ArrayList<AdminCodeTO> adminCodeList = (ArrayList<AdminCodeTO>) baseSF.adminCodeList();		
		map.put("adminCodeList", adminCodeList);
		map.put("errorCode", 0);
		map.put("errorMsg", "Successable!");
	
		return map;
	}
	
	@RequestMapping(value="/base/adminCodeList", params="adminCode")
   	public ModelMap modifyAuthority(@RequestParam("empCode")String empCode , @RequestParam("adminCode") String adminCode){			
		baseSF.modifyAuthority(empCode , adminCode);
		
		map.put("errorCode", 0);
		map.put("errorMsg", "Successable!");
				
		return map;
	}
}
