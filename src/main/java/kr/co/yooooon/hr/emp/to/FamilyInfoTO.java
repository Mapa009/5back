package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.emp.compositKey.FamilyInfoID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="FAMILY_INFO")
@EqualsAndHashCode(callSuper=false)
@IdClass(FamilyInfoID.class)
public class FamilyInfoTO extends BaseTO{
	//복합키
	@Id
	private String empCode;
	@Id
	private String familyCode;

	private String familyName,relation,liveTogether;

	private String birthdate;
}
