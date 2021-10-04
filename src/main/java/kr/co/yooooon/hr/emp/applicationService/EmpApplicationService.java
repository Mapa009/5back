package kr.co.yooooon.hr.emp.applicationService;

import java.util.ArrayList;
import java.util.List;

import kr.co.yooooon.base.to.DeptTO;
import kr.co.yooooon.hr.emp.to.EmpTO;
import kr.co.yooooon.hr.emp.to.PositionTO;
import kr.co.yooooon.hr.emp.to.RecordFamilyInfoTO;

public interface EmpApplicationService {
	public EmpTO selectEmp(String name);
	public String findLastEmpCode();
	public EmpTO findAllEmployeeInfo(String empCode);
	public ArrayList<EmpTO> findEmployeeListByDept(String deptName);
	public void registEmployee(EmpTO emp);
	public void modifyEmployee(EmpTO emp);
	public void deleteEmpList(ArrayList<EmpTO> empList);
	public ArrayList<DeptTO> findDeptList();
	public ArrayList<PositionTO> selectPositionList();
	public RecordFamilyInfoTO findRecordEmpInfo(String empCode);
	public void modifyRecordEmployee(List<RecordFamilyInfoTO> emp);
}
