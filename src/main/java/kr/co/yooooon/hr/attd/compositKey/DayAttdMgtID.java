package kr.co.yooooon.hr.attd.compositKey;

import lombok.Data;

import java.io.Serializable;

@Data
public class DayAttdMgtID implements Serializable {
    private String empCode;
    private String applyDays;

}
