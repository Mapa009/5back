package kr.co.yooooon.hr.emp.applicationService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kr.co.yooooon.base.repository.BoardRepository;
import kr.co.yooooon.base.repository.DeptRepository;
import kr.co.yooooon.base.repository.DetailCodeRepository;
import kr.co.yooooon.base.repository.MenuRepository;
import kr.co.yooooon.base.to.BoardTO;
import kr.co.yooooon.base.to.MenuTO;
import kr.co.yooooon.hr.attd.repository.DayAttdMgtRepository;
import kr.co.yooooon.hr.certificate.compositKey.CertificateCompositID;
import kr.co.yooooon.hr.certificate.repository.CertificateRepository;
import kr.co.yooooon.hr.certificate.to.CertificateTO;
import kr.co.yooooon.hr.emp.repository.EmpRepository;
import kr.co.yooooon.hr.emp.repository.PositionRepository;
import kr.co.yooooon.hr.emp.to.EmpTO;
import kr.co.yooooon.hr.emp.to.PositionTO;
import kr.co.yooooon.hr.salary.repository.MonthSalaryRepository;
import kr.co.yooooon.hr.salary.to.MonthSalaryTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmpApplicationServiceImplTest {

    @Autowired
    EmpRepository er;
    @Autowired
    DeptRepository dr;
    @Autowired
    MenuRepository mr;
    @Autowired
    BoardRepository br;
    @Autowired
    PositionRepository pr;
    @Autowired
    MonthSalaryRepository msr;
    @Autowired
    DayAttdMgtRepository dmr;
    @Autowired
    CertificateRepository cfr;
    @Autowired
    DetailCodeRepository dcr;

    @Test
    @Transactional
    void findAllEmployeeInfo(){
        dcr.findById("GEC001");
    }
}