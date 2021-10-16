package kr.co.yooooon.hr.attd.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.attd.compositKey.RestAttdID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="REST_ATTD")
@IdClass(RestAttdID.class)
@Dataset(name="ds_restAttd")
public class RestAttdTO extends BaseTO{
	//복합키 필요
	@Id
	@GenericGenerator(name = "restAttdSeqGen", strategy = "kr.co.yooooon.hr.attd.generator.restAttdGen")
	@GeneratedValue(generator = "restAttdSeqGen")
	private String restAttdCode;
	/*@SequenceGenerator(name = "restAttdSeqGen", sequenceName = "REST_ATTD_CODE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restAttdSeqGen") //GenerationType.SEQUENCE*/

	@Id
	private String empCode;

	private String restTypeCode
	,restTypeName, cost, cause
	,applovalStatus, rejectCause, startTime, endTime;

	private int numberOfDays;

	@Transient
	private String empName,status;

	private String requestDate, startDate,endDate;
}
