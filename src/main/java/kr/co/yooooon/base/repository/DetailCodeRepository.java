package kr.co.yooooon.base.repository;

import kr.co.yooooon.base.to.DetailCodeTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DetailCodeRepository extends CrudRepository<DetailCodeTO,String> {
    ArrayList<DetailCodeTO> findDetailCodeListByCodeNumber(String DetailCodeNumber);
    DetailCodeTO findDetailCodeByDetailCodeNumber(String detailCodeNumber);
}
