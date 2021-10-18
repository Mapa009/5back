package kr.co.yooooon.base.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "AUTHORITY")
@Dataset(name="ds_authority")
@IdClass(value=AuthorityTO.class)
public class AuthorityTO implements Serializable{
	@Id
	private String empCode;
	@Id
	private String menuCode;
	
	private String empName,deptCode, deptName, menuName, authority;
	
	@Transient
	private String status;
}
