package kr.co.yooooon.hr.emp.repository;

import kr.co.yooooon.hr.emp.to.EmpTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmpRepository extends CrudRepository<EmpTO, String> {
    EmpTO findEmpInfoByEmpCode(String empCode);
    EmpTO findEmpInfoByEmpName(String empName); //이상한거임
    ArrayList<EmpTO> findEmpInfoByDeptCode(String DeptCode);
    ArrayList<EmpTO> findEmpListByEmpName(String empName);
}
