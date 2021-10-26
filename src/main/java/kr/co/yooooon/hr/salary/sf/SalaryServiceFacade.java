package kr.co.yooooon.hr.salary.sf;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.yooooon.hr.emp.to.PositionTO;
import kr.co.yooooon.hr.salary.to.BaseDeductionTO;
import kr.co.yooooon.hr.salary.to.BaseExtSalTO;
import kr.co.yooooon.hr.salary.to.BaseSalaryTO;
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

	public HashMap<String,Object> findMonthSalary(HashMap<String ,Object> map);
	public HashMap<String,Object> findYearSalary(HashMap<String,Object> map);
	public void modifyMonthSalary(MonthSalaryTO monthSalary);
	
	public ArrayList<FullTimeSalTO> findAllMoney(String applyYearMonth);
	public ArrayList<FullTimeSalTO> findselectSalary(String applyYearMonth, String empCode);
	public void modifyFullTimeSalary(ArrayList<FullTimeSalTO> fullTimeSalary);
	public ArrayList<PayDayTO> findPayDayList();
	
	public ArrayList<SocialInsureTO> findBaseInsureList(String yearBox);
	public void updateInsureData(ArrayList<SocialInsureTO> baseInsureList);
	public void deleteInsureData(ArrayList<SocialInsureTO> baseInsureList);
	
	
}
