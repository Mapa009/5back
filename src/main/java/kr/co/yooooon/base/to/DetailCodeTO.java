package kr.co.yooooon.base.to;

import kr.co.yooooon.base.compositKey.DetailCodeID;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name="DETAIL_CODE")
//@IdClass(DetailCodeID.class)
public class DetailCodeTO {
	//복합키가 필요없는데 있음
	@Id
	String detailCodeNumber;

	String codeNumber;
	String detailCodeName, detailCodeNameusing;
}
