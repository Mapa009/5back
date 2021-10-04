package kr.co.yooooon.base.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.yooooon.base.to.ReportSalaryTO;
import kr.co.yooooon.base.to.ReportTO;

@Mapper
public interface ReportDAO {
   public ReportTO selectReport(String empCode);
   public ReportSalaryTO selecSalarytReport(@Param("empCode")String empCode, @Param("applyMonth")String applyMonth);  
}