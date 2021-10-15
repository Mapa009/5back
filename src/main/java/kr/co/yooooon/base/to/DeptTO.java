package kr.co.yooooon.base.to;

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
@Table(name="DEPT")
@Dataset(name="gds_deptcode")
public class DeptTO extends BaseTO{
	@Id
	String deptCode;
	String deptName, deptTel;

	@Transient
	String status;
}
