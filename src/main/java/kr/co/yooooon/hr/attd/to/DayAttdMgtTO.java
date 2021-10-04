package kr.co.yooooon.hr.attd.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.attd.compositKey.DayAttdMgtID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="DAY_ATTD_MANAGE")
@IdClass(DayAttdMgtID.class)
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
	private String empName;
}
