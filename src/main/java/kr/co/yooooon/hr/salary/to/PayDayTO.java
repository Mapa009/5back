package kr.co.yooooon.hr.salary.to;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="SALARY_BONUS_DATE")
@Entity
public class PayDayTO {
	@Id
	private String ord;
	private String paymentDate,
	smltnIssue,
	salaryType,
	remarks;
}
