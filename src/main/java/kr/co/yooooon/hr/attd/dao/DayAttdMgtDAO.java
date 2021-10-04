package kr.co.yooooon.hr.attd.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.yooooon.hr.attd.to.DayAttdMgtTO;

@Mapper
public interface DayAttdMgtDAO {
	public HashMap<String, Object> batchDayAttdMgtProcess(HashMap<String , Object> map);
	//public void updateDayAttdMgtList(DayAttdMgtTO dayAttdMgt);
}
