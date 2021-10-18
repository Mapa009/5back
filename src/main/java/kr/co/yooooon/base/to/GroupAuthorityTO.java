package kr.co.yooooon.base.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "AUTHORITY_GROUP")
@Dataset(name="gds_groupAuthority")
@IdClass(value=GroupAuthorityTO.class)
public class GroupAuthorityTO implements Serializable{
	@Id
	private String groupCode;
	
	private String groupName;
	
	@Transient
	private String status;
}
