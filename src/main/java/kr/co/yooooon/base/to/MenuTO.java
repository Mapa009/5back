package kr.co.yooooon.base.to;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name="MENU")
@Dataset(name="gds_menu")
public class MenuTO {
	@Id
	private String menu_code ;
	private String menu_name ,menu_url, menu_lv ;

}
