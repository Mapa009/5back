package kr.co.yooooon.base.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="HOLIDAY")
public class HolidayTO extends BaseTO{
	@Id
	@Temporal(TemporalType.DATE)
	private Date applyDay;
	private String holidayName, note;
}
