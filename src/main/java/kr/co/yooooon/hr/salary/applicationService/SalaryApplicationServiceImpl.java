package kr.co.yooooon.hr.salary.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.yooooon.hr.emp.repository.PositionRepository;
import kr.co.yooooon.hr.emp.to.PositionTO;
import kr.co.yooooon.hr.salary.dao.BaseDeductionDAO;
import kr.co.yooooon.hr.salary.dao.BaseExtSalDAO;
import kr.co.yooooon.hr.salary.dao.BaseSalaryDAO;
import kr.co.yooooon.hr.salary.dao.FullTimeSalaryDAO;
import kr.co.yooooon.hr.salary.dao.MonthDeductionDAO;
import kr.co.yooooon.hr.salary.dao.MonthExtSalDAO;
import kr.co.yooooon.hr.salary.dao.MonthSalaryDAO;
import kr.co.yooooon.hr.salary.dao.SocialInsureDAO;
import kr.co.yooooon.hr.salary.repository.BaseDeductionRepository;
import kr.co.yooooon.hr.salary.repository.BaseExtSalRepository;
import kr.co.yooooon.hr.salary.repository.FullTimeSalaryRepository;
import kr.co.yooooon.hr.salary.repository.MonthDeductionRepository;
import kr.co.yooooon.hr.salary.repository.MonthExtSalRepository;
import kr.co.yooooon.hr.salary.repository.MonthSalaryRepository;
import kr.co.yooooon.hr.salary.repository.SalaryBounsDateRepository;
import kr.co.yooooon.hr.salary.repository.SocialInsureRepository;
import kr.co.yooooon.hr.salary.to.BaseDeductionTO;
import kr.co.yooooon.hr.salary.to.BaseExtSalTO;
import kr.co.yooooon.hr.salary.to.FullTimeSalTO;
import kr.co.yooooon.hr.salary.to.MonthSalaryTO;
import kr.co.yooooon.hr.salary.to.PayDayTO;
import kr.co.yooooon.hr.salary.to.SocialInsureTO;

@Component
public class SalaryApplicationServiceImpl implements SalaryApplicationService {
	@Autowired
	private BaseSalaryDAO baseSalaryDAO;
	@Autowired
	private BaseDeductionDAO baseDeductionDAO;
	@Autowired
	private BaseExtSalDAO baseExtSalDAO;
	@Autowired
	private MonthSalaryDAO monthSalaryDAO;
	@Autowired
	private MonthDeductionDAO monthDeductionDAO;
	@Autowired
	private MonthExtSalDAO monthExtSalDAO;
	@Autowired
	private FullTimeSalaryDAO fulltimeSalDAO;
	@Autowired
	private SocialInsureDAO socialInsureDAO;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private BaseDeductionRepository baseDeductionRepository;
	@Autowired
	private BaseExtSalRepository baseExtSalRepository;
	@Autowired
	private MonthDeductionRepository monthDeductionRepository;
	@Autowired
	private MonthExtSalRepository monthExtSalRepository;
	@Autowired
	private MonthSalaryRepository monthSalaryRepository;
	@Autowired
	private FullTimeSalaryRepository fullTimeSalaryRepository;
	@Autowired
	private SalaryBounsDateRepository salaryBounsDateRepository;
	@Autowired
	private SocialInsureRepository socialInsureRepository;

	@Override
	public ArrayList<PositionTO> findBaseSalaryList() {
		ArrayList<PositionTO> baseSalaryList = positionRepository.findAllByOrderByPositionCode();
		return baseSalaryList;
	}

	@Override
	public void modifyBaseSalaryList(ArrayList<PositionTO> baseSalaryList) {
		for (PositionTO baseSalary : baseSalaryList) {
			if (baseSalary.getStatus().equals("update"))
				positionRepository.save(baseSalary);
		}
	}

	@Override
	public ArrayList<BaseDeductionTO> findBaseDeductionList() {
		ArrayList<BaseDeductionTO> baseDeductionList = (ArrayList<BaseDeductionTO>) baseDeductionRepository.findAll();
		return baseDeductionList;
	}

	@Override
	public void batchBaseDeductionProcess(ArrayList<BaseDeductionTO> baseDeductionList) {
		for (BaseDeductionTO baseDeduction : baseDeductionList) {
			switch (baseDeduction.getStatus()) {
			case "update":
				baseDeductionRepository.save(baseDeduction);
				break;

			case "insert":
				baseDeductionRepository.save(baseDeduction);
				break;

			case "delete":
				baseDeductionRepository.deleteById(baseDeduction.getDeductionCode());
				break;
			}
		}
	}

	@Override
	public ArrayList<BaseExtSalTO> findBaseExtSalList() {
		ArrayList<BaseExtSalTO> baseExtSalList = (ArrayList<BaseExtSalTO>) baseExtSalRepository.findAll();
		return baseExtSalList;
	}

	@Override
	public void modifyBaseExtSalList(ArrayList<BaseExtSalTO> baseExtSalList) {
		for (BaseExtSalTO baseExtSal : baseExtSalList) {
			if (baseExtSal.getStatus().equals("update"))
				baseExtSalRepository.save(baseExtSal);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String,Object> findMonthSalary(HashMap<String,Object> map) {
		
		// 월급여조회 프로시저
		monthSalaryDAO.batchMonthSalaryProcess(map);
		
		@SuppressWarnings("unchecked")
		ArrayList<MonthSalaryTO> monthSalaryList = (ArrayList<MonthSalaryTO>) map.get("result");
		
		MonthSalaryTO monthSalary = monthSalaryList.get(0);
		
		String applyYearMonth = monthSalary.getApplyYearMonth();
		String empCode = monthSalary.getEmpCode();

		
		// 공제내역
		monthSalary.setMonthDeductionList(monthDeductionDAO.selectMonthDeductionList(applyYearMonth, empCode));
		
		// 초과수당내역
		monthSalary.setMonthExtSalList(monthExtSalDAO.selectMonthExtSalList(applyYearMonth, empCode));
		map.put("result",monthSalary);
		return map;
	}
	
	@Override
	public HashMap<String,Object> findYearSalary(HashMap<String,Object> map) {

		ArrayList<MonthSalaryTO> yearSalary = monthSalaryDAO.selectYearSalary(map);
		map.clear();
		map.put("result",yearSalary);

		return map;
	}

	@Override
	public void modifyMonthSalary(MonthSalaryTO monthSalary) {
		monthSalaryRepository.save(monthSalary);
	}

	@Override
	public ArrayList<FullTimeSalTO> findAllMoney(String applyYearMonth) {
		ArrayList<FullTimeSalTO> findAllMoney = fullTimeSalaryRepository.findFullTimeSalTOByApplyYearMonth(applyYearMonth);
		return findAllMoney;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<FullTimeSalTO> findselectSalary(String applyYearMonth2, String empCode) {
		//프로시저
	    HashMap<String,Object> PrameterMap = new HashMap<String,Object>();
	    PrameterMap.put("applyYearMonth",applyYearMonth2);
	    PrameterMap.put("empCode",empCode);
		System.out.println("22222222222222222222222ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		System.out.println(empCode);
		System.out.println(applyYearMonth2);
		System.out.println(PrameterMap.get("empCode"));
		System.out.println(PrameterMap.get("applyYearMonth"));
	    fulltimeSalDAO.selectFullTimeSalary(PrameterMap);
		
	    ArrayList<FullTimeSalTO> selectSalary = (ArrayList<FullTimeSalTO>) PrameterMap.get("result");
		for(FullTimeSalTO fst : selectSalary) {
			System.out.println(fst.getBasicSalary());
			System.out.println(fst.getFamilySalary());
		}
		return selectSalary;
	}

	@Override
	public void modifyFullTimeSalary(ArrayList<FullTimeSalTO> fullTimeSalary) {
		for(FullTimeSalTO fts : fullTimeSalary) {
			   fullTimeSalaryRepository.save(fts);
		}
	}

	@Override
	public ArrayList<PayDayTO> findPayDayList() {
		ArrayList<PayDayTO> findPayDayList = (ArrayList<PayDayTO>) salaryBounsDateRepository.findAll();
		return findPayDayList;
	}

	
   @SuppressWarnings("unchecked")
   @Override
   public ArrayList<SocialInsureTO> findBaseInsureList(String yearBox) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("yearBox", yearBox);
	  //프로시져
      socialInsureDAO.selectBaseInsureList(map);
      ArrayList<SocialInsureTO> BaseInsureList = (ArrayList<SocialInsureTO>) map.get("result");
      return BaseInsureList;
   }

	@Override
	public void updateInsureData(ArrayList<SocialInsureTO> baseInsure) {
		socialInsureRepository.saveAll(baseInsure);
	}
	

	@Override
	public void deleteInsureData(ArrayList<SocialInsureTO> baseInsureList) {
		socialInsureRepository.deleteAll(baseInsureList);
	}
}