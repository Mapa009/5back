package kr.co.yooooon.base.repository;

import kr.co.yooooon.base.to.HolidayTO;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface HolidayRepository extends CrudRepository<HolidayTO, Date> {
}
