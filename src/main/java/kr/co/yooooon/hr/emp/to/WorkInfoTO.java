package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.emp.compositKey.WorkInfoID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "WORK_INFO")
@EqualsAndHashCode(callSuper=false)
@IdClass(WorkInfoID.class)
public class WorkInfoTO extends BaseTO{
	//복합키 필요
	@Id
	private String empCode;
	@Id
	private String workInfoDays;
	private String retireDate,
	occupation, employmentType, hobong, position, deptName;

	private String hiredate;
}
