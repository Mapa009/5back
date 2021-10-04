package kr.co.yooooon.hr.salary.sf;

import java.util.ArrayList;

import kr.co.yooooon.hr.emp.to.PositionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.yooooon.hr.salary.applicationService.SalaryApplicationService;
import kr.co.yooooon.hr.salary.to.BaseDeductionTO;
import kr.co.yooooon.hr.salary.to.BaseExtSalTO;
import kr.co.yooooon.hr.salary.to.FullTimeSalTO;
import kr.co.yooooon.hr.salary.to.MonthSalaryTO;
import kr.co.yooooon.hr.salary.to.PayDayTO;
import kr.co.yooooon.hr.salary.to.SocialInsureTO;                                            

@Service
public class SalaryServiceFacadeImpl implements SalaryServiceFacade{
	@Autowired
	private SalaryApplicationService salaryApplicationService;

	@Override
	public ArrayList<PositionTO> findBaseSalaryList() {
			ArrayList<PositionTO> baseSalaryList=salaryApplicationService.findBaseSalaryList();
			return baseSalaryList;
	}
	
	@Override
	public void modifyBaseSalaryList(ArrayList<PositionTO> baseSalaryList) {
		salaryApplicationService.modifyBaseSalaryList(baseSalaryList);
	}
	
	@Override
	public ArrayList<BaseDeductionTO> findBaseDeductionList() {
		ArrayList<BaseDeductionTO> baseDeductionList=salaryApplicationService.findBaseDeductionList();			
		return baseDeductionList;
		
	}
	
	@Override
	public void batchBaseDeductionProcess(ArrayList<BaseDeductionTO> baseDeductionList) {
		salaryApplicationService.batchBaseDeductionProcess(baseDeductionList);
	}
	
	@Override
	public ArrayList<BaseExtSalTO> findBaseExtSalList() {
		ArrayList<BaseExtSalTO> baseExtSalList=salaryApplicationService.findBaseExtSalList();	
		return baseExtSalList;
	}
	
	@Override
	public void modifyBaseExtSalList(ArrayList<BaseExtSalTO> baseExtSalList) {
		salaryApplicationService.modifyBaseExtSalList(baseExtSalList);	
	}

	@Override
	public MonthSalaryTO findMonthSalary(String ApplyYearMonth, String empCode) {	
		MonthSalaryTO monthSalary=salaryApplicationService.findMonthSalary(ApplyYearMonth, empCode);
		return monthSalary;
	}

	@Override
	public ArrayList<MonthSalaryTO> findYearSalary(String applyYear, String empCode) {
		ArrayList<MonthSalaryTO> monthSalary=salaryApplicationService.findYearSalary(applyYear, empCode);	
		return monthSalary;
	}
	
	@Override
	public void modifyMonthSalary(MonthSalaryTO monthSalary) {
		salaryApplicationService.modifyMonthSalary(monthSalary);
	}

	@Override
	public ArrayList<FullTimeSalTO> findAllMoney(String applyYearMonth) {
		ArrayList<FullTimeSalTO> allmoney=salaryApplicationService.findAllMoney(applyYearMonth);	
		return allmoney;
	}

	@Override
	public ArrayList<FullTimeSalTO> findselectSalary(String applyYearMonth2, String empCode) {
		ArrayList<FullTimeSalTO> selectSalary=salaryApplicationService.findselectSalary(applyYearMonth2,empCode);	
		return selectSalary;
	}

	@Override
	public void modifyFullTimeSalary(ArrayList<FullTimeSalTO> fullTimeSalary) {
		salaryApplicationService.modifyFullTimeSalary(fullTimeSalary);
	}

	@Override
	public ArrayList<PayDayTO> findPayDayList() {
		ArrayList<PayDayTO> payDayList=salaryApplicationService.findPayDayList();	
		return payDayList;
	}
	
	@Override
	public ArrayList<SocialInsureTO> findBaseInsureList(String yearBox) {
		ArrayList<SocialInsureTO> baseInsureList=salaryApplicationService.findBaseInsureList(yearBox);
		return baseInsureList;
	}
	
	@Override
	public void updateInsureData(ArrayList<SocialInsureTO> baseInsureList) {
		salaryApplicationService.updateInsureData(baseInsureList);	
	}
	
	@Override
	public void deleteInsureData(ArrayList<SocialInsureTO> baseInsureList) {
		salaryApplicationService.deleteInsureData(baseInsureList);
	}
	
}
