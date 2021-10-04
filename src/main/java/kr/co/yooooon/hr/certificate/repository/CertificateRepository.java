package kr.co.yooooon.hr.certificate.repository;

import kr.co.yooooon.hr.certificate.compositKey.CertificateCompositID;
import kr.co.yooooon.hr.certificate.to.CertificateTO;
import org.springframework.data.repository.CrudRepository;

public interface CertificateRepository extends CrudRepository<CertificateTO, CertificateCompositID> {
}
