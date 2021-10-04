package kr.co.yooooon.base.repository;

import kr.co.yooooon.base.to.AdminCodeTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AdminCodeRepository extends JpaRepository<AdminCodeTO,String> {
    String Query="select * from admin_code where admin_code not in('AD_00','AD_01')";
    @Query(nativeQuery = true,value=Query)
    ArrayList<AdminCodeTO> findAdminCodeList();
}
