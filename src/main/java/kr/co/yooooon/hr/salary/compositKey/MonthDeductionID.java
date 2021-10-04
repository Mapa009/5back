package kr.co.yooooon.hr.salary.compositKey;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class MonthDeductionID implements Serializable {
    private String deductionCode;
    private String applyYearMonth;
    private String empCode;

}
