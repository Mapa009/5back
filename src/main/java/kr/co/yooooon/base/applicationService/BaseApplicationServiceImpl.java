package kr.co.yooooon.base.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.yooooon.base.repository.*;
import kr.co.yooooon.hr.emp.repository.EmpRepository;
import kr.co.yooooon.hr.emp.repository.PositionRepository;
import kr.co.yooooon.hr.emp.to.PositionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.yooooon.base.dao.AdminCodeDAO;
import kr.co.yooooon.base.dao.BoardDAO;
import kr.co.yooooon.base.dao.CodeDAO;
import kr.co.yooooon.base.dao.DeptDAO;
import kr.co.yooooon.base.dao.DetailCodeDAO;
import kr.co.yooooon.base.dao.HolidayDAO;
import kr.co.yooooon.base.dao.ReportDAO;
import kr.co.yooooon.base.dao.menuDAO;
import kr.co.yooooon.base.exception.IdNotFoundException;
import kr.co.yooooon.base.exception.PwMissMatchException;
import kr.co.yooooon.base.to.AdminCodeTO;
import kr.co.yooooon.base.to.BoardTO;
import kr.co.yooooon.base.to.CodeTO;
import kr.co.yooooon.base.to.DeptTO;
import kr.co.yooooon.base.to.DetailCodeTO;
import kr.co.yooooon.base.to.HolidayTO;
import kr.co.yooooon.base.to.MenuTO;
import kr.co.yooooon.base.to.ReportSalaryTO;
import kr.co.yooooon.base.to.ReportTO;
import kr.co.yooooon.common.exception.DataAccessException;
import kr.co.yooooon.hr.emp.applicationService.EmpApplicationService;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import kr.co.yooooon.hr.emp.to.EmpTO;
import kr.co.yooooon.hr.salary.dao.BaseSalaryDAO;

@Component
public class BaseApplicationServiceImpl implements BaseApplicationService {
	@Autowired
	private EmpApplicationService empApplicationService;
	@Autowired
	private EmpServiceFacade empServiceFacade;
	@Autowired
	private DetailCodeDAO detailCodeDAO;
	@Autowired
	private HolidayDAO holidayDAO;
	@Autowired
	private DeptDAO deptDAO;
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BaseSalaryDAO baseSalaryDAO;
	@Autowired
	private CodeDAO codeDAO;
	@Autowired
	private ReportDAO reportDAO;
	@Autowired
	private menuDAO menuDAO;
	@Autowired
	private AdminCodeDAO adminDAO;
	@Autowired
	private DetailCodeRepository detailCodeRepository;
	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private DeptRepository deptRepository;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private CodeRepository codeRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private AdminCodeRepository adminCodeRepository;
	@Autowired
	private EmpRepository empRepository;
	@Autowired
	private BoardRepository boardRepository;

	public boolean loginEmployee(String name, String empCode) throws IdNotFoundException, PwMissMatchException {
		
		EmpTO emp = empServiceFacade.getEmp(name); // empName으로 사원의 정보를 찾는다

		if (emp == null) {
			throw new IdNotFoundException("사원명이 존재하지 않습니다");
		} else {
			if (emp.getEmpCode().equals(empCode)) {
				return true;
			} else {
				throw new PwMissMatchException("사원번호를 확인해주세요");
			}
		}
	}

	@Override
	public ArrayList<DetailCodeTO> findDetailCodeList(String codetype) {
		ArrayList<DetailCodeTO> detailCodeList = null;
		detailCodeList = detailCodeRepository.findDetailCodeListByCodeNumber(codetype);
		return detailCodeList;
	}

	@Override
	public void registEmpCode(EmpTO emp) {
		DetailCodeTO detailCodeto = new DetailCodeTO();
		detailCodeto.setDetailCodeNumber(emp.getEmpCode());
		detailCodeto.setDetailCodeName(emp.getEmpName());
		detailCodeto.setCodeNumber("CO-17");
		detailCodeto.setDetailCodeNameusing("N");
		detailCodeRepository.save(detailCodeto);
	}

	@Override
	public void deleteEmpCode(EmpTO emp) {
		DetailCodeTO detailCodeTO = new DetailCodeTO();
		detailCodeTO.setDetailCodeNumber(emp.getEmpCode());
		detailCodeRepository.delete(detailCodeTO);
	}

	@Override
	public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1, String code2, String code3) {
		ArrayList<DetailCodeTO> detailCodeList = new ArrayList<>();
		ArrayList<String> detailCodeArr = new ArrayList<>();
		detailCodeArr.add(code1);
		detailCodeArr.add(code2);
		detailCodeArr.add(code3);
		for(String code : detailCodeArr){
			detailCodeList.add(detailCodeRepository.findDetailCodeByDetailCodeNumber(code));
		}
		return detailCodeList;
	}

	@Override
	public ArrayList<HolidayTO> findHolidayList() {
		ArrayList<HolidayTO> holidayList = (ArrayList<HolidayTO>) holidayRepository.findAll();
		return holidayList;
	}

	@Override
	public void batchDeptProcess(ArrayList<DeptTO> deptto) {
		DetailCodeTO detailCodeto = new DetailCodeTO(); 

		for (DeptTO dept : deptto) {
			
			switch (dept.getStatus()) {

				case "update":
					deptRepository.save(dept);
					detailCodeto.setDetailCodeNumber(dept.getDeptCode());
					detailCodeto.setDetailCodeName(dept.getDeptName());
					detailCodeto.setCodeNumber("CO-07");
					detailCodeto.setDetailCodeNameusing("Y");
					detailCodeRepository.save(detailCodeto);
				break;

				case "insert":
					deptRepository.save(dept);
					detailCodeto.setDetailCodeNumber(dept.getDeptCode());
					detailCodeto.setDetailCodeName(dept.getDeptName());
					detailCodeto.setCodeNumber("CO-07");
					detailCodeto.setDetailCodeNameusing("Y");
					detailCodeRepository.save(detailCodeto);
				break;

				case "delete":
					deptRepository.deleteById(dept.getDeptCode());
					detailCodeRepository.deleteById(dept.getDeptCode());
				break;

				case "normal":break;
			}
		}
	}

	@Override
	public void modifyPosition(ArrayList<PositionTO> positionList) {
		if (positionList != null && positionList.size() > 0){
			
			for (PositionTO position : positionList) {
				DetailCodeTO detailCodeto = new DetailCodeTO();
				switch (position.getStatus()) {

				case "update":
					positionRepository.save(position);
					detailCodeto.setDetailCodeNumber(position.getPositionCode());
					detailCodeto.setDetailCodeName(position.getPosition());
					detailCodeto.setCodeNumber("CO-04");
					detailCodeto.setDetailCodeNameusing("Y");
					detailCodeRepository.save(detailCodeto);
					break;

				case "insert":
					positionRepository.save(position);
					detailCodeto.setDetailCodeNumber(position.getPositionCode());
					detailCodeto.setDetailCodeName(position.getPosition());
					detailCodeto.setCodeNumber("CO-04");
					detailCodeto.setDetailCodeNameusing("Y");
					detailCodeRepository.save(detailCodeto);
					break;

				case "delete":
					positionRepository.deleteById(position.getPositionCode());
					detailCodeRepository.deleteById(position.getPositionCode());
					break;
				}
			}
		}
	}

	@Override
	public String findWeekDayCount(String startDate, String endDate) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		String weekdayCount = holidayDAO.selectWeekDayCount(map);
		return weekdayCount;
	}

	@Override
	public void registEmpImg(String empCode, String imgExtend) {
		EmpTO emp = empApplicationService.findAllEmployeeInfo(empCode);
		
		if (emp == null) {
			System.out.println("해당 사원이 존재 하지 않음");
			emp = new EmpTO();
			emp.setEmpCode(empCode);
			emp.setStatus("insert");
		} else {
			System.out.println("해당 사원은 존재함");
			emp.setStatus("update");
		}
		
		System.out.println("Check :             :               :                 :"+emp.getBirthdate());
		
		emp.setImgExtend(imgExtend);
		empApplicationService.modifyEmployee(emp);
	}

	@Override
	public ArrayList<CodeTO> findCodeList() {
		ArrayList<CodeTO> codeList = (ArrayList<CodeTO>) codeRepository.findAll();
		return codeList;
	}

	@Override
	public void batchHoilyDayProcess(List<HolidayTO> holyday) {
		
		for (HolidayTO holiday : holyday) {
			switch (holiday.getStatus()) {

			case "update":
				holidayRepository.save(holiday);
				break;
			
			case "insert":
				holidayRepository.save(holiday);
				break;

			case "delete":
				holidayRepository.deleteById(holiday.getApplyDay());
				break;
			}
			
		}
	}
	//reportTO 쓰는 부분들은 서브쿼리로 결과값을 뽑아서 쓰는 애들임
	@Override
	public ReportTO viewReport(String empCode) {
		
		 ReportTO to=null;

		try {
			 to=reportDAO.selectReport(empCode);
		 }catch(DataAccessException e){
			 throw e;
		 }

		return to;
	}
	
	
	@Override
	public ReportSalaryTO viewSalaryReport(String empCode, String applyMonth) {
		 ReportSalaryTO to=null;

		try {
			 to=reportDAO.selecSalarytReport(empCode,applyMonth);
		 }catch(DataAccessException e){
			throw e;
		 }
		return to;
	}
	
	@Override
	public ArrayList<MenuTO> findMenuList() {
		ArrayList<MenuTO> menuList = menuRepository.findMenuList();
		return menuList;
	}
	@Override
	public ArrayList<AdminCodeTO> adminCodeList() {
		ArrayList<AdminCodeTO> adminCodeList = adminCodeRepository.findAdminCodeList();
		return adminCodeList;
	}
	
	@Override
	public void modifyAuthority(String empCode, String adminCode) {
		EmpTO empTO = new EmpTO();
		empTO.setEmpCode(adminCode);
		empTO.setAuthority(empCode);
		empRepository.save(empTO);
	}
	
	@Override
	public void addBoard(BoardTO board) {
        System.out.println("Ap@@@@@@@@@@@@@@@@@@@@@@@");
		boardRepository.save(board);
	}
	
	@Override
	public ArrayList<BoardTO> findBoardList() {
        System.out.println("Ap@@@@@@@@@@@@@@@@@@@@@@@");
        ArrayList<BoardTO> array=boardRepository.findByOrderByBoardSeqDesc();
        return array;
	}
	
	@Override
	public ArrayList<BoardTO> findPost(BoardTO board) {
		System.out.println("Fa@@@@@@@@@@@@@@@@@@@@@@@");
		ArrayList<BoardTO> array=boardRepository.findByBoardSeqAndRefSeq(board.getBoardSeq(),board.getRefSeq());
		return array;
	}

}