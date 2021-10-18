package kr.co.yooooon.hr.emp.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "EMP_AUTHORITY_GROUP")
@Dataset(name = "gds_empAuthGroup")
@IdClass(value=EmpAuthGroupTO.class)
public class EmpAuthGroupTO implements Serializable{
	@Id
	private String groupCode;
	@Id
	private String empCode;
	
	@Transient
	private String status;
}
