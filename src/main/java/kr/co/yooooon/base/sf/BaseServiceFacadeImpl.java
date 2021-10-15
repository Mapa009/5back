package kr.co.yooooon.base.sf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.yooooon.base.to.*;
import kr.co.yooooon.hr.emp.to.PositionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.yooooon.base.applicationService.BaseApplicationService;
import kr.co.yooooon.base.exception.IdNotFoundException;
import kr.co.yooooon.base.exception.PwMissMatchException;
import kr.co.yooooon.hr.emp.applicationService.EmpApplicationService;

@Service
public class BaseServiceFacadeImpl implements BaseServiceFacade {
	@Autowired
	private BaseApplicationService baseApplicationService;
	@Autowired
	private EmpApplicationService empApplicationService;
	
	@Override
	public boolean login(String name, String empCode) {

		boolean check = false;
		try {
			check = baseApplicationService.loginEmployee(name, empCode);
		}catch(PwMissMatchException | IdNotFoundException e){
			throw new RuntimeException(e);
		}
		return check;
	}

	
	@Override
	public ArrayList<DetailCodeTO> findDetailCodeList(String codetype) {
		ArrayList<DetailCodeTO> detailCodeto = baseApplicationService.findDetailCodeList(codetype);
		return detailCodeto;
	}

	
	@Override
	public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1, String code2, String code3) {
		ArrayList<DetailCodeTO> detailCodeto = baseApplicationService.findDetailCodeListRest(code1, code2, code3);
		return detailCodeto;
	}

	
	@Override
	public ArrayList<HolidayTO> findHolidayList() {	
		ArrayList<HolidayTO> holidayList = baseApplicationService.findHolidayList();		
		return holidayList;
	}

	
	@Override
	public String findWeekDayCount(String startDate, String endDate) {
		String weekdayCount = baseApplicationService.findWeekDayCount(startDate, endDate);	
		return weekdayCount;
	}

	
	@Override
	public void registEmpImg(String empCode , String imgExtend) {
		baseApplicationService.registEmpImg(empCode ,imgExtend);
	}

	
	@Override
	public void batchDeptProcess(ArrayList<DeptTO> deptto) {
		baseApplicationService.batchDeptProcess(deptto);
	}

	
	@Override
	public ArrayList<PositionTO> findPositionList() {
		ArrayList<PositionTO> positionList = empApplicationService.selectPositionList();
		return positionList;
	}

	
	@Override
	public void modifyPosition(ArrayList<PositionTO> positionList) {
		baseApplicationService.modifyPosition(positionList);
	}

	
	@Override
	public ArrayList<CodeTO> findCodeList() {
		ArrayList<CodeTO> codeto = baseApplicationService.findCodeList();
		return codeto;
	}

	
	@Override
	public void batchHoilyDayProcess(List<HolidayTO> holyday) {
		baseApplicationService.batchHoilyDayProcess(holyday);
	}
	
	@Override
	public ReportTO viewReport(String empCode) {
		ReportTO to=null;
		to=baseApplicationService.viewReport(empCode);
		
		return to;
	}
	
	@Override
	public ReportSalaryTO viewSalaryReport(String empCode, String applyMonth) {
		ReportSalaryTO to=null;
		to=baseApplicationService.viewSalaryReport(empCode,applyMonth);
			
		return to;
	}
	
	@Override
	public void addBoard(BoardTO board) {
		System.out.println("Fa@@@@@@@@@@@@@@@@@@@@@@@");
			baseApplicationService.addBoard(board);
	}

	@Override
	public ArrayList<BoardTO> findBoardList() {
		System.out.println("Fa@@@@@@@@@@@@@@@@@@@@@@@");
		ArrayList<BoardTO> array=baseApplicationService.findBoardList();
		return array;
	}
	
	@Override
	public ArrayList<BoardTO> findPost(BoardTO board) {
		System.out.println("Fa@@@@@@@@@@@@@@@@@@@@@@@");
		ArrayList<BoardTO> array=baseApplicationService.findPost(board);
		return array;
	}
	
	@Override
	public ArrayList<MenuTO> findMenuList() {
		ArrayList<MenuTO> menuList = baseApplicationService.findMenuList();
		return menuList;
	}
	@Override
	public ArrayList<AdminCodeTO> adminCodeList() {
		ArrayList<AdminCodeTO> amdList = baseApplicationService.adminCodeList();
		return amdList;
	}
	
	@Override
	public void modifyAuthority(String empCode, String adminCode) {
		baseApplicationService.modifyAuthority(empCode,adminCode);
	}
	public ArrayList<PowerTO> findPowerList(String position){
		return baseApplicationService.findPowerList(position);
	}
	@Override
	public ArrayList<PowerTO> findPowerListAll(){
		return baseApplicationService.findPowerListAll();
	}
	@Override
	public void savePower(PowerTO power) {
		baseApplicationService.savePower(power);
	}
	@Override
	public ArrayList<AuthorityTO> findAuthorityList(HashMap<String, Object> map){
		return baseApplicationService.findAuthorityList(map);
	}
	@Override
	public void saveAuthority(ArrayList<AuthorityTO> authority) {
		baseApplicationService.saveAuthority(authority);
	}
	@Override
	public ArrayList<GroupAuthorityTO> findGroupAuthority(String empCode){
		return baseApplicationService.findGroupAuthority(empCode);
	}
	@Override
	public List<GroupAuthorityTO> findAllGroupAuthority(){
		return baseApplicationService.findAllGroupAuthority();
	}

}
