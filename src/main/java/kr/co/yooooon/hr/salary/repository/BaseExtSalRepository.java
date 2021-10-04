package kr.co.yooooon.hr.salary.repository;

import kr.co.yooooon.hr.salary.to.BaseExtSalTO;
import org.springframework.data.repository.CrudRepository;

public interface BaseExtSalRepository extends CrudRepository<BaseExtSalTO,String> {
}
