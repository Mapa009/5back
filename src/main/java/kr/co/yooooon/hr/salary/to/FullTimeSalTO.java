package kr.co.yooooon.hr.salary.to;


import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="FULLTIME_EMPLOYEE_SALARY")
@Dataset(name="ds_fullTimeSal")
public class FullTimeSalTO extends BaseTO {
	@Id
	private String empCode;
	private String applyYearMonth,
	basicSalary,
	positionSalary,
	familySalary,
	foodSalary,
	overWorkSalary,
	nationalPension,
	healthInsurance,
	longTermInsurance,
	employmentInsurance,
	religionDonation,
	incomeTax,
	residentTax,
	finalizeStatus,
	basicSalBefore;
}
	