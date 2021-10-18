package kr.co.yooooon.base.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "POSITION_AUTHORITY")
@Dataset(name="gds_powerList")
public class PowerTO {
	@Id
	private String position;
	
	private String positionCode;
	
	@Column(nullable=false)
	private String empRegist, empDetailedView, certificateApproval, attendanceApproval,
	annualVacationManage, dayAttendanceManage, monthAttendanceManage, proofApproval,
	monthSalary;
	@Transient
	private String status;
}
