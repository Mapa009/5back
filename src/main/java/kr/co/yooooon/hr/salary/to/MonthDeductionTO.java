package kr.co.yooooon.hr.salary.to;

import kr.co.yooooon.hr.salary.compositKey.MonthDeductionID;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name="MONTH_DEDUCTION")
@IdClass(MonthDeductionID.class)
public class MonthDeductionTO {
	//복합키 필요
	@Id
	private String deductionCode;
	@Id
	private String applyYearMonth;
	@Id
	private String empCode;
	private String deductionName, price;

}
