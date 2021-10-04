package kr.co.yooooon.hr.salary.repository;

import kr.co.yooooon.hr.salary.to.PayDayTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SalaryBounsDateRepository extends CrudRepository<PayDayTO,String>{
}
