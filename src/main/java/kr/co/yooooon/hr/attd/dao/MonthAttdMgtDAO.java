package kr.co.yooooon.hr.attd.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.yooooon.hr.attd.to.MonthAttdMgtTO;

@Mapper
public interface MonthAttdMgtDAO {
	public HashMap<String, Object> batchMonthAttdMgtProcess(HashMap<String , Object> map);
	//public void updateMonthAttdMgtList(MonthAttdMgtTO monthAttdMgt);
}
