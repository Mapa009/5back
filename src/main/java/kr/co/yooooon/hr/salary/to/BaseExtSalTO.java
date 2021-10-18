package kr.co.yooooon.hr.salary.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="BASE_EXT_SAL")
@Dataset(name="ds_baseExtSal")
public class BaseExtSalTO  extends BaseTO{
	@Id
	private String extSalCode;
	private String extSalName,ratio;
	
    //영속성에서 제거해주는 역할, 엑스플랫폼에서 dataset을 보면서 참고하자.
    @Transient 
    String status;
}
