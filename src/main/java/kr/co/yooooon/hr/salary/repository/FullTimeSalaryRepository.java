package kr.co.yooooon.hr.salary.repository;

import kr.co.yooooon.hr.salary.to.FullTimeSalTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FullTimeSalaryRepository extends CrudRepository<FullTimeSalTO,String> {
    ArrayList<FullTimeSalTO> findFullTimeSalTOByApplyYearMonth(String applyYearMonth);
}
