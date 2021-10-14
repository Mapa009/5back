package kr.co.yooooon.hr.salary.to;

import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="SOCIAL_INSURE_RATIO")
@Entity
@EqualsAndHashCode(callSuper=false)
@Dataset(name="ds_socialInsure")
public class SocialInsureTO extends BaseTO{
	@Id
	private String attributionYear;
	private String healthInsureRates,
	longtermCareRate,
	nationPenisionRates,
	teachPenisionRates,
	empInsureRates,
	wrkInsureRates,
	jobStabilRates,
	vocaCompetencyRates,
	industInsureRates,
	industInsurecharRates;

}