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
@Table(name="BASE_DEDUCTION")
@Dataset(name="ds_baseDeduction")
public class BaseDeductionTO extends BaseTO{
	@Id
	private String deductionCode;
	private String deductionName,ratio;
}
