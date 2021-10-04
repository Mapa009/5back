package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.emp.compositKey.CareerInfoID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="CAREER_INFO")
@EqualsAndHashCode(callSuper=false)
//복합키 empcode, careercode
@IdClass(CareerInfoID.class)
public class CareerInfoTO extends BaseTO{
	@Id
	private String empCode;
	@Id
	private String careerCode;

	private String companyName, occupation, assignmentTask;

	private String exHiredate, exRetirementDate;
}
