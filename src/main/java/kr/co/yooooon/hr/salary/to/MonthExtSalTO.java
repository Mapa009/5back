package kr.co.yooooon.hr.salary.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;

@Data
@Entity
@Table(name="MONTH_EXT_SAL")
@Dataset(name="ds_monthExtSalList")
public class MonthExtSalTO {
	@Id
	private String monthExtSalCode;
	private String applyYearMonth;

	private String empCode;
	private String extSalCode,
	extSalName,
	price;

}
