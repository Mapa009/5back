package kr.co.yooooon.hr.attd.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.attd.compositKey.AnnualVacationMgtID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="annual_vacation_manage")
//테이블 2개를 처리함
//복합키
@IdClass(AnnualVacationMgtID.class)
@Dataset(name="ds_annualVacation")
public class AnnualVacationMgtTO extends BaseTO{
	//애를 annalvaction , annualvacationmanage 테이블 2개가 이 객체 하나로 관리됨 진짜 에바임 view단과 controller 고치고 싶으면 바꾸면됨
	@Id
	private String empCode;
	@Id
	private String applyYearMonth;

	private String afternoonOff, monthlyLeave, remainingHoliday, finalizeStatus,totalUsing;
	//애를 annualvacationmanage로 사용할 경우 transient 어노테이션 붙여줘야됨
	@Transient
	private String empName,year;
	
}
