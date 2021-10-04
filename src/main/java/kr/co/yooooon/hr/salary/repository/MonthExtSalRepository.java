package kr.co.yooooon.hr.salary.repository;

import kr.co.yooooon.hr.salary.to.MonthExtSalTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MonthExtSalRepository extends CrudRepository<MonthExtSalTO,String> {
    ArrayList<MonthExtSalTO> findMonthExtSalTOByApplyYearMonthAndEmpCode(String applyYearMonth,String empCode);
}
