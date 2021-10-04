package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.CodeTO;
import kr.co.yooooon.base.to.DetailCodeTO;
import kr.co.yooooon.common.exception.DataAccessException;

@RestController
public class CodeListController{
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/codeList", params="code")
	public ModelMap detailCodelist(@RequestParam("code")String code) {

		try {
			ArrayList<DetailCodeTO> detailCodeList=baseServiceFacade.findDetailCodeList(code);	
			map.put("detailCodeList", detailCodeList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		}catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		} 
		
		return map;
	}
	
	@RequestMapping(value="/base/codeList", params="code1")
	public ModelMap detailCodelistRest(@RequestParam("code1")String code1 , @RequestParam("code2")String code2 ,@RequestParam("code3")String code3) {
		System.out.println(code1+"@@@@");
		System.out.println(code2+"@@@@");
		System.out.println(code3+"@@@@");
		try {
			ArrayList<DetailCodeTO> detailCodeList=baseServiceFacade.findDetailCodeListRest(code1,code2,code3);
			map.put("detailCodeList", detailCodeList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		}catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		} 
		
		return map;
	}
	
	@RequestMapping(value="/base/codeList")
	public ModelMap codelist(){
		try {
			ArrayList<CodeTO> codeList=baseServiceFacade.findCodeList();
			map.put("codeList", codeList);
			map.put("errorMsg","Success !");
			map.put("errorCode", 0);
		}catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		
		return map;
	} 
}
