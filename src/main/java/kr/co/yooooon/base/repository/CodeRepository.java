package kr.co.yooooon.base.repository;

import kr.co.yooooon.base.to.CodeTO;
import org.springframework.data.repository.CrudRepository;

public interface CodeRepository extends CrudRepository<CodeTO,String> {
}
