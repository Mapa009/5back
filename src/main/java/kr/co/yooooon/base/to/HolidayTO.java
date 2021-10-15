package kr.co.yooooon.base.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="HOLIDAY")
@Dataset(name="ds_holiday")
public class HolidayTO extends BaseTO{
	@Id
	@Temporal(TemporalType.DATE)
	private Date applyDay;
	private String holidayName, note;

	@Transient
	private String status;
}
