package kr.co.yooooon.hr.certificate.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.yooooon.hr.certificate.to.CertificateTO;

@Mapper
public interface CertificateDAO {
	
	//public void insertCertificateRequest(CertificateTO certificate);
	public ArrayList<CertificateTO>selectCertificateList(@Param("empCode")String empCode, @Param("startDate")String startDate, @Param("endDate")String endDate);
	//public void deleteCertificate(CertificateTO certificate);
	public ArrayList<CertificateTO> selectCertificateListByAllDept(String requestDate);
	public ArrayList<CertificateTO> selectCertificateListByDept(HashMap<String ,Object> map);
	//public void updateCertificate(CertificateTO certificate);
}
