package kr.co.yooooon.hr.attd.repository;

import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;
import org.springframework.data.repository.CrudRepository;

public interface DayAttdMgtRepository extends CrudRepository<DayAttdMgtTO,String> {
}
