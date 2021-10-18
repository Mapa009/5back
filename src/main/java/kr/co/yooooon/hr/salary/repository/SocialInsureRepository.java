package kr.co.yooooon.hr.salary.repository;

import kr.co.yooooon.hr.salary.to.SocialInsureTO;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface SocialInsureRepository extends CrudRepository<SocialInsureTO,String> {
	//public ArrayList<SocialInsureTO> findAllbyid(String year);
}
