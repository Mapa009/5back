package kr.co.yooooon.hr.attd.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.attd.compositKey.MonthAttdMgtID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="MONTH_ATTD_MANAGE")
@IdClass(MonthAttdMgtID.class) 
@Dataset(name="ds_monthAttenMng")
public class MonthAttdMgtTO extends BaseTO{
	//복합키필요
	@Id
	private String empCode;
	@Id
	private String applyYearMonth;

	private String basicWorkDays
	,weekdayWorkDays ,basicWorkHour ,workHour
	,overWorkHour ,nightWorkHour ,holidayWorkDays,earlyLeaveDays
	,holidayWorkHour ,lateDays ,absentDays ,halfHolidays ,Holidays ,finalizeStatus;

	@Transient
	private String empName;
}
