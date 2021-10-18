package kr.co.yooooon.hr.emp.repository;

import kr.co.yooooon.hr.emp.to.PositionTO;
import kr.co.yooooon.hr.salary.to.BaseSalaryTO;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PositionRepository extends CrudRepository<PositionTO,String> {
    PositionTO findPositionByPositionCode(String positionCode);
    ArrayList<PositionTO> findAllByOrderByPositionCode();
}
