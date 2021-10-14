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
@Table(name="BASE_EXT_SAL")
@Dataset(name="ds_baseExtSal")
public class BaseExtSalTO  extends BaseTO{
	@Id
	private String extSalCode;
	private String extSalName,ratio;
}
