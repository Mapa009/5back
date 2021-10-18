package kr.co.yooooon.base.controller;

import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.PowerTO;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PowerController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	/* powerManage2.xfdl */
	@RequestMapping("/base/findPowerList")
	public void findPowerList(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		String position = reqData.getVariable("position").getString();
		List<PowerTO> list= baseServiceFacade.findPowerList(position);
		datasetBeanMapper.beansToDataset(resData, list, PowerTO.class);
	};
	
	@RequestMapping("/base/findPowerListAll")//전체를 조회하지 않아서 쓰진 않음
	public void findPowerListAll(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		List<PowerTO> list= baseServiceFacade.findPowerListAll();
		datasetBeanMapper.beansToDataset(resData, list, PowerTO.class);
	};
	
	/* powerManage2.xfdl */
	@RequestMapping("/base/save")
	public void save(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		PowerTO power = datasetBeanMapper.datasetToBean(reqData, PowerTO.class);
		baseServiceFacade.savePower(power);
	};
}
