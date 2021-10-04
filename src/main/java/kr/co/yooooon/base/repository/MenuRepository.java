package kr.co.yooooon.base.repository;

import kr.co.yooooon.base.to.MenuTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MenuRepository extends JpaRepository<MenuTO,String> {
    //한번사용해봄
    String Query="select  menu_name ,\n" +
            "\t\t\t\t\tmenu_code ,\n" +
            "\t\t\t\t\tlevel-1 as menu_lv ,\n" +
            "\t\t\t\t\tmenu_url \n" +
            "\t\t\t\t\tfrom menu \n" +
            "\t\t\t\t\tstart with super_menu_code is null connect by prior menu_code = super_menu_code";
    @Query(nativeQuery = true, value = Query)
    ArrayList<MenuTO> findMenuList();
}
