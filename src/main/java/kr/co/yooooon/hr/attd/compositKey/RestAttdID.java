package kr.co.yooooon.hr.attd.compositKey;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestAttdID implements Serializable {
    private String restAttdCode;
    private String empCode;

}
