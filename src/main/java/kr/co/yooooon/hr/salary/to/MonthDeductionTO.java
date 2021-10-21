package kr.co.yooooon.hr.salary.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;

@Data
@Entity
@Table(name="MONTH_DEDUCTION")
@Dataset(name="ds_monthDeductionList")
public class MonthDeductionTO {
	@Id
	private String monthDeductionCode;
	private String deductionCode;
	private String applyYearMonth;
	private String empCode;
	private String deductionName, price;

}
