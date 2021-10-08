package kr.co.yooooon.hr.emp.dao;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.yooooon.hr.emp.to.EmpTO;

@Mapper
public interface EmpDAO {
	public String selectLastEmpCode();
	public void registEmployee(HashMap<String ,Object> map);
	public void deleteEmployee(HashMap<String , Object> map);
	public void updateEmployee(EmpTO emp);
}
