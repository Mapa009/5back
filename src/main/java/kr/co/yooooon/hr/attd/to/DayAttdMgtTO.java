package kr.co.yooooon.hr.attd.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.attd.compositKey.DayAttdMgtID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="DAY_ATTD_MANAGE")
@IdClass(DayAttdMgtID.class)
@Dataset(name="ds_dayAttenMng")
public class DayAttdMgtTO extends BaseTO{
	//복합키 필요
	@Id
	private String empCode;
	@Id
	private String applyDays;

	private String dayAttdCode
	,dayAttdName ,attendTime,HalfHolidayStatus
	,quitTime ,lateWhether ,leaveHour ,workHour,earlyLeaveHour
	,overWorkHour ,nightWorkHour ,finalizeStatus, privateleaveHour, publicleaveHour;

	@Transient
<<<<<<< HEAD
	private String empName,status;
=======
	private String empName;
	@Transient
	private String lateHour;
>>>>>>> jjy
}
