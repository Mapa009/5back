package kr.co.yooooon.hr.attd.compositKey;


import lombok.Data;

import java.io.Serializable;

@Data
public class MonthAttdMgtID implements Serializable {
    private String empCode;
    private String applyYearMonth;

}
