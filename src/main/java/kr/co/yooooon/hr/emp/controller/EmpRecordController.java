package kr.co.yooooon.hr.emp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.FamilyInfoTO;
import kr.co.yooooon.hr.emp.to.RecordFamilyInfoTO;

@RestController
public class EmpRecordController {
	@Autowired
	private EmpServiceFacade empServiceFacade;
	private ModelMap map = new ModelMap();

	@RequestMapping(value = "/emp/empRecordCard", params = "empCode")
	public ModelMap findAllEmployeeInfo(@RequestParam("empCode") String empCode) { /* 부서별 사원 정보 그리드 onRowClicked 함수 */
		try {
			RecordFamilyInfoTO recordFamilyInfoTO = empServiceFacade.findRecordEmpInfo(empCode);
			map.put("EMPCODE", empCode);
			map.put("empBean", recordFamilyInfoTO);
			FamilyInfoTO familyInfoTO = new FamilyInfoTO();
			map.put("emptyFamilyInfoBean", familyInfoTO);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/emp/empRecordCard", params = "sendData")
	public ModelMap modifyRecordEmployee(@RequestParam("sendData") String sendData) { /* 저장버튼 */
		try {
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<RecordFamilyInfoTO> holydayList = mapper.readValue(sendData,new TypeReference<ArrayList<RecordFamilyInfoTO>>(){});
			empServiceFacade.modifyRecordEmployee(holydayList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}
}