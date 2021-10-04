package kr.co.yooooon.hr.attd.repository;

import kr.co.yooooon.hr.attd.compositKey.AnnualVacationMgtID;
import kr.co.yooooon.hr.attd.to.AnnualVacationMgtTO;
import org.springframework.data.repository.CrudRepository;

public interface AnnualVacationMgtRepository extends CrudRepository<AnnualVacationMgtTO, AnnualVacationMgtID> {
}
