package kr.co.yooooon.base.applicationService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import kr.co.yooooon.hr.emp.to.EmpTO;
import kr.co.yooooon.hr.emp.to.PositionTO;


public interface BaseApplicationService {
	//Login Part
   public boolean loginEmployee(String name, String empCode) throws IdNotFoundException, PwMissMatchException;

   // Detail Code Part
   public ArrayList<DetailCodeTO> findDetailCodeList(String codetype);
   public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1, String code2, String code3);

   //HoilyDay
   public ArrayList<HolidayTO> findHolidayList();
   public String findWeekDayCount(String startDate, String endDate);

   // ImgUpload Part 
   public void registEmpImg(String empCode , String imgExtend);

   //Position Part 
   public void batchDeptProcess(ArrayList<DeptTO> deptto);
   public void modifyPosition(ArrayList<PositionTO> positionList);
   
   //Emp Part 
   public void registEmpCode(EmpTO emp);
   public void deleteEmpCode(EmpTO emp);
   public ArrayList<CodeTO> findCodeList();
   public void batchHoilyDayProcess(List<HolidayTO> holyday);
   public ReportTO viewReport(String empCode);
   public ReportSalaryTO viewSalaryReport(String empCode, String applyMonth);
   
   //Menu Part 
   public ArrayList<MenuTO> findMenuList();
   
   //Authority Part
	public ArrayList<AdminCodeTO> adminCodeList();
	public void modifyAuthority(String empCode, String adminCode);
	
	//Board Part
	   public void addBoard(BoardTO board);
	   public ArrayList<BoardTO> findBoardList();
	   public ArrayList<BoardTO> findPost(BoardTO board);
}