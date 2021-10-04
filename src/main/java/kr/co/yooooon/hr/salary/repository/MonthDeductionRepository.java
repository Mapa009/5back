package kr.co.yooooon.hr.salary.repository;

import kr.co.yooooon.hr.salary.to.MonthDeductionTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MonthDeductionRepository extends CrudRepository<MonthDeductionTO,String> {
    ArrayList<MonthDeductionTO> findMonthDeductionTOByApplyYearMonthAndEmpCode(String applyYearMonth,String empCode);
}
