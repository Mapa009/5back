package kr.co.yooooon.hr.attd.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.attd.compositKey.DayAttdID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="DAY_ATTD")
@IdClass(DayAttdID.class)
@Dataset(name="gds_dayAttd")
//복합키
public class DayAttdTO extends BaseTO{
	@Id
	private String dayAttdCode;
	@Id
	private String empCode;

	private String applyDay,attdTypeCode,attdTypeName,time;

	@Transient
	private String empName;
}
