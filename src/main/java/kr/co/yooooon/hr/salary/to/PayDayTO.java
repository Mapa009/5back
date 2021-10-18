package kr.co.yooooon.hr.salary.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name="SALARY_BONUS_DATE")
@Entity
@EqualsAndHashCode(callSuper=false)
@Dataset(name="gds_payDay")
public class PayDayTO {
	@Id
	private String ord;
	private String paymentDate,
	smltnIssue,
	salaryType,
	remarks;
}
