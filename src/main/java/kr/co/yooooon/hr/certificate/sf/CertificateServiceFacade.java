package kr.co.yooooon.hr.certificate.sf;


import java.util.ArrayList;
import kr.co.yooooon.hr.certificate.to.CertificateTO;
import kr.co.yooooon.hr.certificate.to.ProofTO;


public interface CertificateServiceFacade {
	
	public void registRequest(CertificateTO certificate);	
	public ArrayList<CertificateTO> findCertificateList(String empCode, String startDate, String endDate);
	public void removeCertificateRequest(ArrayList<CertificateTO> certificateList);
	public ArrayList<CertificateTO> findCertificateListByDept(String deptName, String startDate, String endDate);
	public void modifyCertificateList(ArrayList<CertificateTO> certificateList);
	public void proofRequest(ProofTO proof);
	public ArrayList<ProofTO> proofLookupList(String empCode, String Code, String startDate, String endDate);
	public void removeProofRequest(ArrayList<ProofTO> proofList);
	public ArrayList<ProofTO> findProofListByDept(String deptName, String startDate, String endDate);
	public void modifyProofList(ArrayList<ProofTO> proofList);
	public void rsgistProofImg(String cashCode,String proofImg);
	
	
}
