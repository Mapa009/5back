package kr.co.yooooon.hr.salary.to;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity // JPA에선 TO를 @Entity 라 한다 ( 영구적인 )
@Data   // getter / setter
@EqualsAndHashCode(callSuper=false)
@Table(name = "POSITION")  // 테이블
@Dataset(name="ds_position")  // 나의 dataset이름
public class BaseSalaryTO {
	@Id
	private String positionCode;
	
	@Column(nullable=false)
	private String position,baseSalary,hobongRatio,allowance;
}
