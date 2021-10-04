package kr.co.yooooon.hr.salary.to;

import kr.co.yooooon.hr.salary.compositKey.MonthSalaryID;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Table(name="MONTH_SALARY")
@IdClass(MonthSalaryID.class)
public class MonthSalaryTO {
	//복합키 필요
	@Id
	private String empCode;
	@Id
	private String applyYearMonth;
	private String salary,totalExtSal,totalPayment,totalDeduction,realSalary,cost,unusedDaySalary,finalizeStatus;
	@Transient
	private ArrayList<MonthDeductionTO> monthDeductionList;
	@Transient
	private ArrayList<MonthExtSalTO> monthExtSalList;
}
