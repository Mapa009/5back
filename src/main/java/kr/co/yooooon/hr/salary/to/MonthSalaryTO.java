package kr.co.yooooon.hr.salary.to;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.salary.compositKey.MonthSalaryID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="MONTH_SALARY")
@EqualsAndHashCode(callSuper=false)
@IdClass(MonthSalaryID.class)
@Dataset(name="ds_MonthSalary")
public class MonthSalaryTO{
	//복합키 필요
	@Id
	private String empCode;
	@Id
	private String applyYearMonth;
	private String salary,totalExtSal,totalPayment,totalDeduction,realSalary,cost,unusedDaySalary,finalizeStatus;
   @Transient
   private String status;
	
	@JoinColumns({
			@JoinColumn(name = "empCode"),
			@JoinColumn(name = "applyYearMonth")
	})
	@OneToMany 
	private List<MonthDeductionTO> monthDeductionList;

	@JoinColumns({
			@JoinColumn(name = "empCode"),
			@JoinColumn(name = "applyYearMonth")
	})
	@OneToMany 
	private List<MonthExtSalTO> monthExtSalList;
}
