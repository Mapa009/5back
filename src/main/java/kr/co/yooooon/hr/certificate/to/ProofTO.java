package kr.co.yooooon.hr.certificate.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.certificate.compositKey.ProofCertificateCompositID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="PROOF_LIST")
@IdClass(ProofCertificateCompositID.class)
public class ProofTO extends BaseTO {
	//복합키 필요
	@Id
	private String seqNo;
	@Id
	private String empCode;

	private String proofTypeCode,proofTypeName,startDate,position,dept,cash,cause,receipt,applovalStatus;

	@Transient
	private String empName;
}
