package kr.co.yooooon.base.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.yooooon.base.dao.*;
import kr.co.yooooon.base.repository.*;
import kr.co.yooooon.base.to.*;
import kr.co.yooooon.hr.emp.repository.EmpRepository;
import kr.co.yooooon.hr.emp.repository.PositionRepository;
import kr.co.yooooon.hr.emp.to.PositionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.yooooon.base.exception.IdNotFoundException;
import kr.co.yooooon.base.exception.PwMissMatchException;
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

	@Autowired
	private PowerDAO powerDAO;
	@Autowired
	private PowerRepository powerRepository;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private GroupAuthorityRepository groupAuthorityRepository;

	public boolean loginEmployee(String name, String empCode) throws IdNotFoundException, PwMissMatchException {
		
		EmpTO emp = empServiceFacade.getEmp(name); // empName?????? ????????? ????????? ?????????

		if (emp == null) {
			throw new IdNotFoundException("???????????? ???????????? ????????????");
		} else {
			if (emp.getEmpCode().equals(empCode)) {
				return true;
			} else {
				throw new PwMissMatchException("??????????????? ??????????????????");
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
			System.out.println("?????? ????????? ?????? ?????? ??????");
			emp = new EmpTO();
			emp.setEmpCode(empCode);
			emp.setStatus("insert");
		} else {
			System.out.println("?????? ????????? ?????????");
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
	//reportTO ?????? ???????????? ??????????????? ???????????? ????????? ?????? ?????????
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

	@Override
	public ArrayList<PowerTO> findPowerList(String position){
		return (ArrayList<PowerTO>) powerDAO.findPowerByPosition(position);
	};
	@Override
	public ArrayList<PowerTO> findPowerListAll(){
		return (ArrayList<PowerTO>) powerRepository.findAll();
	};
	@Override
	public void savePower(PowerTO power) {
		powerDAO.update(power);
	}
	@Override
	public ArrayList<AuthorityTO> findAuthorityList(HashMap<String, Object> map){
		return authorityDAO.findAuthorityList(map);
	}

	@Override
	public void saveAuthority(ArrayList<AuthorityTO> authority) {

		System.out.println(authority);
		for(AuthorityTO auth : authority) {
			authorityDAO.update(auth);}
	}
	@Override
	public ArrayList<GroupAuthorityTO> findGroupAuthority(String empCode){
		return authorityDAO.findGroupAuthority(empCode);
	}
	@Override
	public List<GroupAuthorityTO> findAllGroupAuthority(){
		return groupAuthorityRepository.findAll();
	}
}