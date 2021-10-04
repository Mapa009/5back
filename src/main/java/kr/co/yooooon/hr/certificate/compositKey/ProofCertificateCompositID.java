package kr.co.yooooon.hr.certificate.compositKey;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
public class ProofCertificateCompositID implements Serializable {
    private String empCode;
    private String seqNo;
}
