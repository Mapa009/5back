package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.yooooon.hr.emp.to.PositionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/base/positionList")
	public ModelMap findPositionList(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<PositionTO> positionList = baseServiceFacade.findPositionList();
			PositionTO positionTO = new PositionTO();
			
			map.put("positionList", positionList);
			map.put("emptyPositionBean", positionTO);
			map.put("errorCode",0);
			map.put("errorMsg","success");
						
		}  catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping(value="/base/positionList" , params="sendData")
	public ModelMap modifyPosition(@RequestParam("sendData")String sendData){
		try{
			Gson gson = new Gson();
			ArrayList<PositionTO> positionList = gson.fromJson(sendData , new TypeToken<ArrayList<PositionTO>>(){}.getType());
			
			baseServiceFacade.modifyPosition(positionList);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());	
		}
		return map;
	}	
}
