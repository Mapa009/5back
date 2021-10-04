package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.emp.compositKey.EducationInfoID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="EDUCATION_INFO")
@EqualsAndHashCode(callSuper=false)
//복합키
@IdClass(EducationInfoID.class)
public class EducationInfoTO extends BaseTO{
	@Id
	private String empCode;
	@Id
	private String educationCode;
	private String schoolName, major, grade;

	private String entranceDate, graduateDate;
}
