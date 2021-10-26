package kr.co.yooooon.base.repository;


import kr.co.yooooon.base.to.AuthorityTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityTO, String>{

	//void saveAll(ArrayList<AuthorityTO> authority);

}