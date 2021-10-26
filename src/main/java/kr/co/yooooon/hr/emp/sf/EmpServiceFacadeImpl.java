package kr.co.yooooon.hr.emp.sf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.yooooon.base.to.MenuTO;
import kr.co.yooooon.hr.emp.to.EmpAuthGroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.yooooon.base.to.DeptTO;
import kr.co.yooooon.hr.emp.applicationService.EmpApplicationService;
import kr.co.yooooon.hr.emp.to.EmpTO;
import kr.co.yooooon.hr.emp.to.RecordFamilyInfoTO;

@Service
public class EmpServiceFacadeImpl implements EmpServiceFacade {
	@Autowired
	private EmpApplicationService empApplicationService;
	
	@Override
	public EmpTO getEmp(String name) {
		EmpTO empto = null;
		empto = empApplicationService.selectEmp(name);
		return empto;
	}

	@Override
	public String findLastEmpCode() {
		String empCode = empApplicationService.findLastEmpCode();
		return empCode;
	}
	
	@Override
	public EmpTO findAllEmpInfo(String empCode) {
		EmpTO empTO = empApplicationService.findAllEmployeeInfo(empCode);	
		return empTO;
	}

	@Override
	public ArrayList<EmpTO> findEmpList(String dept) {
		ArrayList<EmpTO> empList = empApplicationService.findEmployeeListByDept(dept);			
		return empList;		
	}

	@Override
	public void registEmployee(EmpTO empto) {
		empApplicationService.registEmployee(empto);
	}
	
	@Override
	public void modifyEmployee(EmpTO emp) {
		empApplicationService.modifyEmployee(emp);		
	}

	@Override
	public void deleteEmpList(ArrayList<EmpTO> empList) {
		empApplicationService.deleteEmpList(empList);
	}

	@Override
	public ArrayList<DeptTO> findDeptList() {
		ArrayList<DeptTO> deptList = empApplicationService.findDeptList();
		return deptList;
	}

	@Override
	public RecordFamilyInfoTO findRecordEmpInfo(String empCode) {
		RecordFamilyInfoTO recordFamilyInfoTO = empApplicationService.findRecordEmpInfo(empCode);
		return recordFamilyInfoTO;
	}

	@Override
	public void modifyRecordEmployee(List<RecordFamilyInfoTO> emp) {
		empApplicationService.modifyRecordEmployee(emp);
		
	}

	@Override
	public ArrayList<MenuTO> findAccessableMenu(String empCode){
		return empApplicationService.findAccessableMenu(empCode);
	}
	@Override
	public List<EmpAuthGroupTO> findEmpAuthGroup(){
		return empApplicationService.findEmpAuthGroup();
	}
	@Override
	public void saveEmpAuthorityGroup(ArrayList<EmpAuthGroupTO> saveEmpAuthGroup){
		empApplicationService.saveEmpAuthorityGroup(saveEmpAuthGroup);
	}
	@Override
	public void deleteEmpAuthorityGroup(HashMap<String, Object> map){
		empApplicationService.deleteEmpAuthorityGroup(map);
	}

	@Override
	public ArrayList<EmpTO> listByMonthSal(String value) {
		return empApplicationService.listByMonthSal(value);
	}
}
