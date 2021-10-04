package kr.co.yooooon.hr.emp.compositKey;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class WorkInfoID implements Serializable {
    private String empCode;
    private String workInfoDays;

}
