package kr.co.yooooon.base.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="DEPT")
public class DeptTO extends BaseTO{
	@Id
	String deptCode;
	String deptName, deptTel;
}
