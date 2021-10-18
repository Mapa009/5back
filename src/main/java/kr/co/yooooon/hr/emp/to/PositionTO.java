package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
<<<<<<< HEAD
=======


>>>>>>> min

@Data
@Entity
@Table(name="POSITION")
@EqualsAndHashCode(callSuper=false)
<<<<<<< HEAD
@Dataset(name="ds_position")
=======
@Dataset(name="ds_baseSalary")
>>>>>>> min
public class PositionTO extends BaseTO {
    @Id
    private String positionCode;
    
    @Column(nullable=false)
    private String position,baseSalary,hobongRatio,allowance;
<<<<<<< HEAD

    @Transient
    private String status;
=======
    
    //영속성에서 제거해주는 역할, 엑스플랫폼에서 dataset을 보면서 참고하자.
    @Transient 
    String status;
>>>>>>> min
}
