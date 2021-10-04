package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="POSITION")
@EqualsAndHashCode(callSuper=false)
public class PositionTO extends BaseTO {
    @Id
    private String positionCode;
    private String position,baseSalary,hobongRatio,allowance;
}
