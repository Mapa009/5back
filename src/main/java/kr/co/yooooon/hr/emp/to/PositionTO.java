package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name="POSITION")
@EqualsAndHashCode(callSuper=false)
@Dataset(name="ds_position")
public class PositionTO extends BaseTO {
    @Id
    private String positionCode;
    private String position,baseSalary,hobongRatio,allowance;

    @Transient
    private String status;
}
