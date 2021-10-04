package kr.co.yooooon.hr.certificate.sf;

import java.util.ArrayList;

import kr.co.yooooon.hr.certificate.to.ProofTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.yooooon.hr.certificate.applicationService.CertificateApplicationService;
import kr.co.yooooon.hr.certificate.to.CertificateTO;

@Service
public class CertificateServiceFacadeImpl implements CertificateServiceFacade{
	@Autowired
	private CertificateApplicationService certificateApplicationService;
	
	@Override
	public void registRequest(CertificateTO certificate) {
		certificateApplicationService.registRequest(certificate);
	}
	@Override
	public ArrayList<CertificateTO> findCertificateList(String empCode, String startDate, String endDate) {
		ArrayList<CertificateTO> certificateList=certificateApplicationService.findCertificateList(empCode, startDate, endDate);
		return certificateList;
	}
	@Override
	public void removeCertificateRequest(ArrayList<CertificateTO> certificateList) {
		certificateApplicationService.removeCertificateRequest(certificateList);
	}
	@Override
	public ArrayList<CertificateTO> findCertificateListByDept(String deptName, String startDate, String endDate) {
		System.out.println(deptName);
		System.out.println(startDate);
		System.out.println(endDate);
		
		ArrayList<CertificateTO> certificateList=certificateApplicationService.findCertificateListByDept(deptName, startDate, endDate);
		return certificateList;
	}
	@Override
	public void modifyCertificateList(ArrayList<CertificateTO> certificateList) {
	
		certificateApplicationService.modifyCertificateList(certificateList);
	}
	
	public void proofRequest(ProofTO proof) {
		certificateApplicationService.proofRequest(proof);
	}
	@Override
	public ArrayList<ProofTO> proofLookupList(String empCode, String Code, String startDate, String endDate) {
		ArrayList<ProofTO> proofLookupList=certificateApplicationService.proofLookupList(empCode,Code,startDate, endDate);
		return proofLookupList;
	}
	
	@Override
	public void removeProofRequest(ArrayList<ProofTO> proofList) {
		certificateApplicationService.removeProofRequest(proofList);	
	}
	
	@Override
	public ArrayList<ProofTO> findProofListByDept(String deptName, String startDate, String endDate) {
		ArrayList<ProofTO> proofList=certificateApplicationService.findProofListByDept(deptName, startDate, endDate);
		return proofList;
	}
	@Override
	public void modifyProofList(ArrayList<ProofTO> proofList) {
		certificateApplicationService.modifyProofList(proofList);
	}
	public void  rsgistProofImg(String cashCode,String proofImg) {			
		certificateApplicationService.rsgistProofImg(cashCode,proofImg);
	}
	
}
