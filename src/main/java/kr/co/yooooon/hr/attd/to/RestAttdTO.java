package kr.co.yooooon.hr.attd.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.attd.compositKey.RestAttdID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="REST_ATTD")
@IdClass(RestAttdID.class)
public class RestAttdTO extends BaseTO{
	//복합키 필요
	@Id
	@SequenceGenerator(name = "restAttdSeqGen", sequenceName = "REST_ATTD_CODE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restAttdSeqGen")
	private Long restAttdCode;
	@Id
	private String empCode;

	private String restTypeCode
	,restTypeName, cost, cause
	,applovalStatus, rejectCause, startTime, endTime;

	private int numberOfDays;

	@Transient
	private String empName;

	private String requestDate, startDate,endDate;
}
