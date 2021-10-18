package kr.co.yooooon.base.dao;

import kr.co.yooooon.base.to.AuthorityTO;
import kr.co.yooooon.base.to.GroupAuthorityTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface AuthorityDAO {
	
	public ArrayList<AuthorityTO> findAuthorityList(HashMap<String, Object> map);
	
	public void update(AuthorityTO authority);
	
	public ArrayList<GroupAuthorityTO> findGroupAuthority(String empCode);
}
