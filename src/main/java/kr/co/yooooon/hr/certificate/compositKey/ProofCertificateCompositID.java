package kr.co.yooooon.hr.certificate.compositKey;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
public class ProofCertificateCompositID implements Serializable {
    private String empCode;
    @SequenceGenerator(
            name = "proofSeqGen",
            sequenceName = "SEQUENCE_TAB1",
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "SEQUENCE_TAB1")
    private int seqNo;
}
