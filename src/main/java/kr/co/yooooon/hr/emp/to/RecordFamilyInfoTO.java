package kr.co.yooooon.hr.emp.to;

import java.util.ArrayList;

import kr.co.yooooon.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="FAMILY_DETAIL_INFO")
public class RecordFamilyInfoTO extends BaseTO{
	@Id
	private String familyCode;
	private String empCode, familyName,relation,birthdate,liveTogether;
	private String dependentRelation, residentRegistration, allowance, dependency, yearendTax, disabled, foreigner, solarLunal, education, graduated, job, workPlace, position;

	@Transient
	private ArrayList<RecordFamilyInfoTO> recordFamilyInfoList;
}