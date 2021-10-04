package kr.co.yooooon.hr.salary.sf;

import java.util.ArrayList;

import kr.co.yooooon.hr.emp.to.PositionTO;
import kr.co.yooooon.hr.salary.to.BaseDeductionTO;
import kr.co.yooooon.hr.salary.to.BaseExtSalTO;
import kr.co.yooooon.hr.salary.to.FullTimeSalTO;
import kr.co.yooooon.hr.salary.to.MonthSalaryTO;
import kr.co.yooooon.hr.salary.to.PayDayTO;
import kr.co.yooooon.hr.salary.to.SocialInsureTO;

public interface SalaryServiceFacade {
	
	public ArrayList<PositionTO> findBaseSalaryList();
	public void modifyBaseSalaryList(ArrayList<PositionTO> baseSalaryList);

	public ArrayList<BaseDeductionTO> findBaseDeductionList();
	public void batchBaseDeductionProcess(ArrayList<BaseDeductionTO> baseDeductionList);

	public ArrayList<BaseExtSalTO> findBaseExtSalList();
	public void modifyBaseExtSalList(ArrayList<BaseExtSalTO> baseExtSalList);

	public MonthSalaryTO findMonthSalary(String ApplyYearMonth, String empCode);
	public ArrayList<MonthSalaryTO> findYearSalary(String applyYear, String empCode);
	public void modifyMonthSalary(MonthSalaryTO monthSalary);
	
	public ArrayList<FullTimeSalTO> findAllMoney(String applyYearMonth);
	public ArrayList<FullTimeSalTO> findselectSalary(String applyYearMonth, String empCode);
	public void modifyFullTimeSalary(ArrayList<FullTimeSalTO> fullTimeSalary);
	public ArrayList<PayDayTO> findPayDayList();
	
	public ArrayList<SocialInsureTO> findBaseInsureList(String yearBox);
	public void updateInsureData(ArrayList<SocialInsureTO> baseInsureList);
	public void deleteInsureData(ArrayList<SocialInsureTO> baseInsureList);
	
	
}
