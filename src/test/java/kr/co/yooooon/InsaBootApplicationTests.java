package kr.co.yooooon;

import kr.co.yooooon.base.applicationService.BaseApplicationService;
import kr.co.yooooon.hr.emp.repository.EmpRepository;
import kr.co.yooooon.hr.emp.sf.EmpServiceFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InsaBootApplicationTests {

	@Autowired
	private EmpServiceFacade empServiceFacade;

	@Autowired
	private EmpRepository er;
	@Test
	void contextLoads() {
		er.findEmpInfoByEmpCode("1111");
	}

}
