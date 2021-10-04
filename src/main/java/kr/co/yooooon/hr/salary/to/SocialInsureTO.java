package kr.co.yooooon.hr.salary.to;

import kr.co.yooooon.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="SOCIAL_INSURE_RATIO")
@Entity
@EqualsAndHashCode(callSuper=false)
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