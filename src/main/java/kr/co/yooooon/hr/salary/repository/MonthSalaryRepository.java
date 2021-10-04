package kr.co.yooooon.hr.salary.repository;

import kr.co.yooooon.hr.salary.to.MonthSalaryTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.HashMap;

public interface MonthSalaryRepository extends CrudRepository<MonthSalaryTO,String> {
    ArrayList<MonthSalaryTO> findAllByEmpCodeAndFinalizeStatusAndApplyYearMonth(String empCode,String finalizeStatus,String applyYearMonth);
}
