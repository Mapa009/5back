package kr.co.yooooon.base.compositKey;

import lombok.Data;

import java.io.Serializable;

//테이블은 DETAILCODE가 복합키로 이루어 져있는데 DETIALCODENUMBER만 있어도 유일키 가능
@Data
public class DetailCodeID implements Serializable {
    String detailCodeNumber;
    String codeNumber;

}
