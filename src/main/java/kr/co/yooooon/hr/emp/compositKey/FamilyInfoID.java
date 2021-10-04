package kr.co.yooooon.hr.emp.compositKey;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class FamilyInfoID implements Serializable{
    private String empCode;
    private String familyCode;

}
