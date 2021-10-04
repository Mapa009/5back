package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.emp.to.EmpTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.MenuTO;
import kr.co.yooooon.common.exception.DataAccessException;

@RestController
public class MenuController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/menuList")
	public void findMenuList(@RequestAttribute("resData") PlatformData resData)throws Exception{
		ArrayList<MenuTO> menuList = baseServiceFacade.findMenuList();
		datasetBeanMapper.beansToDataset(resData,menuList, MenuTO.class);
	}
}
