package kr.co.yooooon.base.controller;

import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.AuthorityTO;
import kr.co.yooooon.base.to.GroupAuthorityTO;
import kr.co.yooooon.common.mapper.DatasetBeanMapper;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.EmpAuthGroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class AuthorityController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private EmpServiceFacade empServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	/* powerManage.xfdl */
	@RequestMapping("/base/findAuthorityList")
	public void findAuthorityLiqst(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		
		String empCode = reqData.getVariable("empCode").getString();
		
		HashMap<String,Object> map = new HashMap<>();
		System.out.println(empCode);
		map.put("empCode", empCode);
		
		List<AuthorityTO> list= baseServiceFacade.findAuthorityList(map);
		datasetBeanMapper.beansToDataset(resData, list, AuthorityTO.class);
	};
	
	
	/* menu_authority.xfdl */
	@RequestMapping("/base/findGroupAuthority")
	public void findGroupAuthority(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		String empCode = reqData.getVariable("empCode").getString();
		
		List<GroupAuthorityTO> list= baseServiceFacade.findGroupAuthority(empCode);
		datasetBeanMapper.beansToDataset(resData, list, GroupAuthorityTO.class);
	};
	
	
	/* autorityGroupDialog.xfdl */
	@RequestMapping("/base/findAllGroupAuthority")
	public void findAllGroupAuthority(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		
		List<GroupAuthorityTO> list= baseServiceFacade.findAllGroupAuthority();
		datasetBeanMapper.beansToDataset(resData, list, GroupAuthorityTO.class);
	};
	
	
	/* powerManage.xfdl */
	@RequestMapping("/base/saveAuthority")
	public void save(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		
		ArrayList<AuthorityTO> authority = (ArrayList<AuthorityTO>) datasetBeanMapper.datasetToBeans(reqData, AuthorityTO.class);
		
		baseServiceFacade.saveAuthority(authority);
	};
	
	/* menu_authority.xfdl */
	/* autorityGroupDialog.xfdl */
	@RequestMapping("/base/saveEmpAuthorityGroup")
	public void saveEmpAuthorityGroup(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		
		ArrayList<EmpAuthGroupTO> saveEmpAuthGroup = (ArrayList<EmpAuthGroupTO>) datasetBeanMapper.datasetToBeans(reqData, EmpAuthGroupTO.class);
		
		empServiceFacade.saveEmpAuthorityGroup(saveEmpAuthGroup);
	};
	
	/* menu_authority.xfdl */
	@RequestMapping("/base/deleteGroupAuthority")
	public void deleteGroupAuthority(@RequestAttribute("reqData") PlatformData reqData,
           @RequestAttribute("resData") PlatformData resData) throws Exception {
		String empCode= reqData.getVariable("empCode").getString();
		String groupCode= reqData.getVariable("groupCode").getString();
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("empCode", empCode);
		map.put("groupCode",groupCode);
		empServiceFacade.deleteEmpAuthorityGroup(map);
	};
	
}
