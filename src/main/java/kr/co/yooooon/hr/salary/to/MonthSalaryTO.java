package kr.co.yooooon.hr.salary.to;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="MONTH_SALARY")
@EqualsAndHashCode(callSuper=false)
//@IdClass(MonthSalaryID.class)
@Dataset(name="ds_MonthSalary")
public class MonthSalaryTO {
	//복합키 필요
	@Id
	private String empCode;
	//@Id
	private String applyYearMonth;
	private String salary,totalExtSal,totalPayment,totalDeduction,realSalary,cost,unusedDaySalary,finalizeStatus;
   @Transient
   private String status;
	
	@JoinColumn(name="empCode")
	@OneToMany 
	private List<MonthDeductionTO> monthDeductionList;
	
	@JoinColumn(name="empCode")
	@OneToMany 
	private List<MonthExtSalTO> monthExtSalList;
}
