package kr.co.yooooon.hr.emp.sf;

import java.util.ArrayList;
import java.util.List;

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
}
