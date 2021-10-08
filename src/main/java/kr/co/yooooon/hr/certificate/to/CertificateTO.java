package kr.co.yooooon.hr.certificate.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.hr.certificate.compositKey.CertificateCompositID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="CERTIFICATE")
@Dataset(name="ds_certificate")
@IdClass(CertificateCompositID.class) //mybatis로 받아야되는 곳도 있어서 idclass사용
public class CertificateTO extends BaseTO{
	//복합키
	@Id
	private String empCode;
	@Id
	private String requestDate;

	private String   useDate, usageCode,
	 etc, approvalStatus, rejectCause;

	@Transient
	private String usageName,empName,deptName,status;
}
