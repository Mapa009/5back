package kr.co.yooooon.hr.emp.sf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.yooooon.base.to.DeptTO;
import kr.co.yooooon.base.to.MenuTO;
import kr.co.yooooon.hr.emp.to.EmpAuthGroupTO;
import kr.co.yooooon.hr.emp.to.EmpTO;
import kr.co.yooooon.hr.emp.to.RecordFamilyInfoTO;

public interface EmpServiceFacade {
	public EmpTO getEmp(String name); //selectEmp
	public String findLastEmpCode();
	public EmpTO findAllEmpInfo(String empCode);	
	public ArrayList<EmpTO> findEmpList(String dept); //findEmployeeListByDept
	public void registEmployee(EmpTO empto);
	public void modifyEmployee(EmpTO emp);
	public void deleteEmpList(ArrayList<EmpTO> empList);
	public ArrayList<DeptTO> findDeptList();
	public RecordFamilyInfoTO findRecordEmpInfo(String empCode);
	public void modifyRecordEmployee(List<RecordFamilyInfoTO> emp);
	public ArrayList<MenuTO> findAccessableMenu(String empCode);
	public List<EmpAuthGroupTO> findEmpAuthGroup();
	public void saveEmpAuthorityGroup(ArrayList<EmpAuthGroupTO> saveEmpAuthGroup);
	public void deleteEmpAuthorityGroup(HashMap<String, Object> map);
	
}