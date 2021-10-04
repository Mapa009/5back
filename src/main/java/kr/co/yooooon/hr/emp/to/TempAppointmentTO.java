package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TempAppointmentTO extends BaseTO{

	String appointmentNo, 
	empCode,
	appointmentHistory,
	currentInformation,
	preAppointmentInformation;

}
