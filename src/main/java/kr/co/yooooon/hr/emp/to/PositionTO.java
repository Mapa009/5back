package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="POSITION")
@EqualsAndHashCode(callSuper=false)
@Dataset(name="ds_baseSalary")
public class PositionTO extends BaseTO {
    @Id
    private String positionCode;
    
    @Column(nullable=false)
    private String position,baseSalary,hobongRatio,allowance;
}
