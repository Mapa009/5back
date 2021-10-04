package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.hr.emp.compositKey.LicenseInfoID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name="LICENSE_INFO")
@EqualsAndHashCode(callSuper=false)
@IdClass(LicenseInfoID.class)
public class LicenseInfoTO extends BaseTO{
	//복합키
	@Id
	private String empCode;
	@Id
	private String licenseCode;
	private String licenseName, getDate, expireDate, licenseLevel, licenseCenter, issueNumber;
}
