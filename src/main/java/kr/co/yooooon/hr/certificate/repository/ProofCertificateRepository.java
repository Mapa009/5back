package kr.co.yooooon.hr.certificate.repository;

import kr.co.yooooon.hr.certificate.compositKey.ProofCertificateCompositID;
import kr.co.yooooon.hr.certificate.to.ProofTO;
import org.springframework.data.repository.CrudRepository;

public interface ProofCertificateRepository extends CrudRepository<ProofTO, ProofCertificateCompositID> {
}
