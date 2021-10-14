package kr.co.yooooon.hr.certificate.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.yooooon.hr.certificate.repository.CertificateRepository;
import kr.co.yooooon.hr.certificate.repository.ProofCertificateRepository;
import kr.co.yooooon.hr.certificate.to.ProofTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.yooooon.hr.certificate.dao.CertificateDAO;
import kr.co.yooooon.hr.certificate.dao.ProofCertificateDAO;
import kr.co.yooooon.hr.certificate.to.CertificateTO;

@Component
public class CertificateApplicatonServiceImpl implements CertificateApplicationService{

	private final CertificateDAO certificateDAO;
	private final ProofCertificateDAO proofCertificateDAO;
	private final CertificateRepository certificateRepository;
	private final ProofCertificateRepository proofCertificateRepository;

	@Autowired
	public CertificateApplicatonServiceImpl(CertificateDAO certificateDAO,ProofCertificateDAO proofCertificateDAO,CertificateRepository certificateRepository,ProofCertificateRepository proofCertificateRepository){
		this.certificateDAO=certificateDAO;
		this.proofCertificateDAO=proofCertificateDAO;
		this.certificateRepository=certificateRepository;
		this.proofCertificateRepository=proofCertificateRepository;
	}

	@Override
	public void registRequest(CertificateTO certificate) {		
		certificateRepository.save(certificate);
	}
	@Override
	public ArrayList<CertificateTO> findCertificateList(String empCode, String startDate, String endDate) {
		ArrayList<CertificateTO> certificateList=certificateDAO.selectCertificateList(empCode, startDate, endDate);
		return certificateList;
	}
	@Override
	public void removeCertificateRequest(ArrayList<CertificateTO> certificateList) {
		certificateRepository.deleteAll(certificateList);
	}
	@Override
	public ArrayList<CertificateTO> findCertificateListByDept(String deptName, String startDate, String endDate) {
		ArrayList<CertificateTO> certificateList = null;
		HashMap<String , Object> map = new HashMap<String , Object>();
		
		
		if(deptName.equals("모든부서")) {
			certificateList = certificateDAO.selectCertificateListByAllDept(startDate);
		}else {
			map.put("deptName",deptName);
			map.put("startDate",startDate);
			map.put("endDate",endDate);
			certificateList = certificateDAO.selectCertificateListByDept(map);
		}
		
		return certificateList;
	}
	@Override
	public void modifyCertificateList(ArrayList<CertificateTO> certificateList) {
		for(CertificateTO certificate : certificateList) {
			System.out.println(certificate.getApprovalStatus());
			if(certificate.getStatus().equals("update")) {
				certificateRepository.save(certificate);
			}
		}
	}
	
	@Override
	public void proofRequest(ProofTO proof) {
		proofCertificateRepository.save(proof);
	}
	public ArrayList<ProofTO> proofLookupList(String empCode, String Code, String startDate, String endDate) {
		ArrayList<ProofTO> proofLookupList=proofCertificateDAO.selectProofCertificateList(empCode,Code,startDate, endDate);
		return proofLookupList;
	}
	
	@Override
	public void removeProofRequest(ArrayList<ProofTO> proofList) {
		proofCertificateRepository.deleteAll(proofList);
	}
	@Override
	public ArrayList<ProofTO> findProofListByDept(String deptName, String startDate, String endDate) {
		ArrayList<ProofTO> proofList = null;
	
		if(deptName.equals("모든부서")) {
			proofList = proofCertificateDAO.selectProofListByAllDept(startDate);
		}else {
			proofList = proofCertificateDAO.selectProofListByDept(deptName, startDate, endDate);
			
		}
	
		return proofList;
	}
	@Override
	public void modifyProofList(ArrayList<ProofTO> proofList) {
		for(ProofTO proof : proofList) {
			System.out.println(proof.getApplovalstatus());
			
			if(proof.getStatus().equals("update")) {
				proofCertificateRepository.save(proof);
			}
		}
	}
	
	public void rsgistProofImg(String cashCode,String proofImg) {
		proofCertificateDAO.updateProofImg(cashCode,proofImg);
	}
	
}
