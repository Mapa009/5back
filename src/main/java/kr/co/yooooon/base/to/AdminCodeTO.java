package kr.co.yooooon.base.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="ADMIN_CODE")
public class AdminCodeTO extends BaseTO{
	@Id
	String admin_code;
	String admin_authority;
}
