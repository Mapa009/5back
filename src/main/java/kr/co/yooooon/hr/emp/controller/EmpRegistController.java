package kr.co.yooooon.hr.emp.controller;

import com.google.gson.GsonBuilder;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.EmpTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@RestController
public class EmpRegistController {
	@Autowired
	private EmpServiceFacade empServiceFacade;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/emp/empRegistEmp" ,params="sendData")
	public ModelMap registEmployee(@RequestParam("sendData")String sendData) throws Exception {
		try {
			System.out.println(sendData);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			EmpTO emp = gson.fromJson(sendData, new TypeToken<EmpTO>(){}.getType());
			System.out.println(emp.getBirthdate());
			empServiceFacade.registEmployee(emp);

			map.put("errorMsg"," 사원이 등록되었습니다.");
			map.put("errorCode", 0);
			
		} catch (DataAccessException dae){
			map.put("errorMsg", "사원 등록에 실패했습니다 : "+dae.getMessage());
			map.put("errorCode", -1);
		} catch(Exception e) {
			map.put("errorMsg", e.getMessage());
			map.put("errorCode", -1);
		}
		return map;
	}
	
	@RequestMapping(value="/emp/empRegist")
	public ModelMap findLastEmpCode(){
		try {
			String empCode = empServiceFacade.findLastEmpCode();
			map.put("lastEmpCode", empCode);
			map.put("errorMsg","success");
			map.put("errorCode", 0);
		} catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
}
