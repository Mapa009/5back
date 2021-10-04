package kr.co.yooooon.base.repository;

import kr.co.yooooon.base.to.DeptTO;
import org.springframework.data.repository.CrudRepository;

public interface DeptRepository extends CrudRepository<DeptTO,String> {
    DeptTO findDeptNameByDeptCode(String deptCode);
    DeptTO findDeptCodeByDeptName(String deptName);
}
