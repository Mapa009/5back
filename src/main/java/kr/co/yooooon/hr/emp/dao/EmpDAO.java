package kr.co.yooooon.hr.emp.dao;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.yooooon.base.to.MenuTO;
import org.apache.ibatis.annotations.Mapper;

import kr.co.yooooon.hr.emp.to.EmpTO;

@Mapper
public interface EmpDAO {
	public String selectLastEmpCode();
	public void registEmployee(HashMap<String ,Object> map);
	public void deleteEmployee(HashMap<String , Object> map);
	public void updateEmployee(EmpTO emp);
	public ArrayList<MenuTO> findAccessableMenu(String empCode);
	public void deleteEmpAuthorityGroup(HashMap<String,Object> map);
}
