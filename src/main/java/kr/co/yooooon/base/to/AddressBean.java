package kr.co.yooooon.base.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Getter;
import lombok.Setter;

@Dataset(name="gds_post")
public class AddressBean {
   @Setter
   @Getter
   String zipNO, lnmAdres, rnAdres;

}