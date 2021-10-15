package kr.co.yooooon.base.controller;

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

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.CodeTO;
import kr.co.yooooon.base.to.DetailCodeTO;
import kr.co.yooooon.common.exception.DataAccessException;

@RestController
public class CodeListController{
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/detailCodeList")
	public void detailCodeList(@RequestAttribute("variableList")VariableList variableList, @RequestAttribute("resData")PlatformData resData) throws Exception{
		String code = variableList.getString("code");
		ArrayList<DetailCodeTO> detailCodeList=baseServiceFacade.findDetailCodeList(code);
		datasetBeanMapper.beansToDataset(resData,detailCodeList,DetailCodeTO.class);
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
	public void codelist(@RequestAttribute("resData")PlatformData resData)throws Exception{
		ArrayList<CodeTO> codeList=baseServiceFacade.findCodeList();
		datasetBeanMapper.beansToDataset(resData,codeList,CodeTO.class);
	}
}
