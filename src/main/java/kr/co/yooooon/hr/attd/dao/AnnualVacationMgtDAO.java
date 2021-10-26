package kr.co.yooooon.hr.attd.dao;

import java.util.HashMap;

import kr.co.yooooon.hr.attd.to.AnnualVacationMgtTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnualVacationMgtDAO {
   public HashMap<String, Object> batchAnnualVacationMgtProcess(HashMap<String , Object> map);
   public void updateAnnualVacationMgtList(AnnualVacationMgtTO annualVacationMgt);
   public void updateAnnualVacationList(AnnualVacationMgtTO annualVacationMgt);
   public void cancelAnnualVacationMgtList(AnnualVacationMgtTO annualVacationMgt);
   public void cancelAnnualVacationList(AnnualVacationMgtTO annualVacationMgt);
}