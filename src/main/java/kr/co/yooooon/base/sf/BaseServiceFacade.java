package kr.co.yooooon.base.sf;

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
import kr.co.yooooon.hr.emp.to.PositionTO;

public interface BaseServiceFacade {
	//Login Part
	public boolean login(String name, String empCode) throws PwMissMatchException, IdNotFoundException;
	
	//Code Part 
	public ArrayList<DetailCodeTO> findDetailCodeList(String codetype);
	public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1,String code2,String code3);
	public ArrayList<CodeTO> findCodeList();
	//HoilyDay Part
	public ArrayList<HolidayTO> findHolidayList();
	public String findWeekDayCount(String startDate, String endDate);
	void batchHoilyDayProcess(List<HolidayTO> holyday);
	
	//IO part
	public void registEmpImg(String empCode , String imgExtend);
	
	//Dept part 
	public void batchDeptProcess(ArrayList<DeptTO> deptto);
	
	//Position Part 
	public ArrayList<PositionTO> findPositionList();
	public void modifyPosition(ArrayList<PositionTO> positionList);

	// Ireport Part 
	public ReportTO viewReport(String empCode);
	public ReportSalaryTO viewSalaryReport(String empCode, String applyMonth);
	
	// Menu Part 
	public ArrayList<MenuTO> findMenuList();
	
	//Authority Part
	public ArrayList<AdminCodeTO> adminCodeList(); 
	public void modifyAuthority(String empCode, String adminCode);
	
	//Board Part
	public void addBoard(BoardTO board);
	public ArrayList<BoardTO> findBoardList();
	public ArrayList<BoardTO> findPost(BoardTO board);
}
