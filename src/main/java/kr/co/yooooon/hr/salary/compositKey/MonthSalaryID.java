package kr.co.yooooon.hr.salary.compositKey;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class MonthSalaryID implements Serializable {

    private String empCode;
    private String applyYearMonth;

}
