package kr.co.yooooon.hr.attd.repository;

import kr.co.yooooon.hr.attd.compositKey.AnnualVacationID;
import kr.co.yooooon.hr.attd.to.AnnualVacationTO;
import org.springframework.data.repository.CrudRepository;

public interface AnnualVactionRepository extends CrudRepository<AnnualVacationTO, AnnualVacationID> {
}
