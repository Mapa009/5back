package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.emp.to.PositionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.common.exception.DataAccessException;

@RestController
public class PositionListController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/findPositionList")
	public void findPositionList(@RequestAttribute("resData")PlatformData resData) throws Exception{
		ArrayList<PositionTO> positionList = baseServiceFacade.findPositionList();
		datasetBeanMapper.beansToDataset(resData,positionList,PositionTO.class);
	}
	
	@RequestMapping(value="/base/modifyPosition")
	public void modifyPosition(@RequestAttribute("reqData")PlatformData reqData)throws Exception{
		ArrayList<PositionTO> positionList = (ArrayList<PositionTO>) datasetBeanMapper.datasetToBeans(reqData,PositionTO.class);
		baseServiceFacade.modifyPosition(positionList);
	}	
}
