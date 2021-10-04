package kr.co.yooooon.hr.emp.repository;

import kr.co.yooooon.hr.emp.to.RecordFamilyInfoTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RecordFamilyRepository extends CrudRepository<RecordFamilyInfoTO,String> {
    ArrayList<RecordFamilyInfoTO> findAllByEmpCode(String empCode);
}
