package kr.co.yooooon.hr.certificate.dao;

import java.util.ArrayList;

import kr.co.yooooon.hr.certificate.to.ProofTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProofCertificateDAO{
	
	//public void insertProofCertificateRequest(ProofTO proof);
	public  ArrayList<ProofTO> selectProofCertificateList(String empCode, String Code, String startDate, String endDate);
	//public void deleteProof(ProofTO proof);
	public ArrayList<ProofTO> selectProofListByDept(String deptName, String startDate, String endDate);
	public ArrayList<ProofTO> selectProofListByAllDept(String startDate);
	//public void updateProof(ProofTO proof);
	
	public void updateProofImg(String cashCode, String proofImg);

}
