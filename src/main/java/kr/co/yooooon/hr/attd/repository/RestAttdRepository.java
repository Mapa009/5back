package kr.co.yooooon.hr.attd.repository;

import kr.co.yooooon.hr.attd.to.RestAttdTO;
import org.springframework.data.repository.CrudRepository;

public interface RestAttdRepository extends CrudRepository<RestAttdTO,String> {

}
