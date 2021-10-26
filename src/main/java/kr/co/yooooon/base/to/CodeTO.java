package kr.co.yooooon.base.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="DIVISION_CODE")
@Dataset(name="gds_codeList")
public class CodeTO extends BaseTO{
	@Id
	String codeNumber;
	String codeName,modifiable;
}
