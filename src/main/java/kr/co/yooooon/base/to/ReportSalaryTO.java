package kr.co.yooooon.base.to;

import lombok.Data;

@Data
public class ReportSalaryTO {
	private String empName, position, deptName, hiredate, applyYearMonth, totalExtSal, totalDeduction, totalPayment,
			realSalary, salary, cost, healthIns, goyongIns, janggiIns, gukmin;

}
