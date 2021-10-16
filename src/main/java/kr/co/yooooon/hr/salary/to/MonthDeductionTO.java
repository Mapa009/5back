package kr.co.yooooon.hr.salary.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.salary.compositKey.MonthDeductionID;
import lombok.Data;

@Data
@Entity
@Table(name="MONTH_DEDUCTION")
@Dataset(name="ds_monthDeductionList")
@IdClass(MonthDeductionID.class)
public class MonthDeductionTO {
	//복합키 필요
	//@Id
	private String deductionCode;
	//@Id
	private String applyYearMonth;
	@Id
	private String empCode;
	private String deductionName, price;

}
