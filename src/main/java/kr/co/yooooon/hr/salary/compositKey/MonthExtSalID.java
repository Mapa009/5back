package kr.co.yooooon.hr.salary.compositKey;

import kr.co.yooooon.hr.salary.to.MonthExtSalTO;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class MonthExtSalID implements Serializable {

    private String monthExtSalCode;
    private String applyYearMonth;
    private String empCode;
}
