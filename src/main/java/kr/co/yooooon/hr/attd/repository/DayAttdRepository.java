package kr.co.yooooon.hr.attd.repository;

import kr.co.yooooon.hr.attd.to.DayAttdTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DayAttdRepository extends CrudRepository<DayAttdTO,String> {
    ArrayList<DayAttdTO> findDayAttdTOByEmpCodeAndApplyDayOrderByTime(String empCode,String applyDay);

}
