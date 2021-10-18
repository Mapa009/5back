package kr.co.yooooon.base.dao;

import kr.co.yooooon.base.to.PowerTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PowerDAO {
	public ArrayList<PowerTO> findPowerByPosition(String position);
	public void update(PowerTO power);
}
