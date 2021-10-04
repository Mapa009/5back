package kr.co.yooooon.hr.salary.to;

import kr.co.yooooon.hr.salary.compositKey.MonthExtSalID;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name="MONTH_EXT_SAL")
@IdClass(MonthExtSalID.class)
public class MonthExtSalTO {
	//복합키 필요
	@Id
	private String monthExtSalCode;
	@Id
	private String applyYearMonth;
	@Id
	private String empCode;
	private String extSalCode,
	extSalName,
	price;

}
