package kr.co.yooooon.hr.certificate.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.certificate.compositKey.ProofCertificateCompositID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="PROOF_LIST")
@IdClass(ProofCertificateCompositID.class)
@Dataset(name="ds_proof")
public class ProofTO extends BaseTO {
	//복합키 필요
	@Id
	private int seqNo;
	@Id
	private String empCode;

	private String proofCode,proofName,requestDate,position,deptName,prootCost,reason,receipt,applovalstatus;

	@Transient
	private String empName,status;
}
