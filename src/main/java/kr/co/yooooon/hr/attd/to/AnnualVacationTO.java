package kr.co.yooooon.hr.attd.to;

import kr.co.yooooon.hr.attd.compositKey.AnnualVacationID;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name="annual_vacation")
@IdClass(AnnualVacationID.class)
//복합키
public class AnnualVacationTO {
    @Id
    private String empCode;
    @Id
    private String year;
    private String remainingVacation,hobong,deptName,position;
}
