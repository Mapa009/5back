package kr.co.yooooon.hr.emp.to;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="EMP")
@EqualsAndHashCode(callSuper=false)
@Dataset(name="gds_emp")
public class EmpTO{
   @Id
   private String empCode;
   private String empName,gender,mobileNumber,address,
           detailAddress,postNumber,email,lastSchool,imgExtend,authority,deptCode,positionCode;

   private String birthdate;

   //해당 테이블의 컬럼에 부합하지 않는 변수들은 영속성에서 제거
   @Transient
   private String position,deptName,hobong,occupation,employment,status;

   @OneToMany
   @JoinColumn(name="empCode")
   private List<WorkInfoTO> workInfoList; //ArrayList사용 불가

   @OneToMany
   @JoinColumn(name="empCode")
   private List<CareerInfoTO> careerInfoList;

   @OneToMany
   @JoinColumn(name="empCode")
   private List<EducationInfoTO> educationInfoList;

   @OneToMany
   @JoinColumn(name="empCode")
   private List<FamilyInfoTO> familyInfoList;

   @OneToMany
   @JoinColumn(name="empCode")
   private List<LicenseInfoTO> licenseInfoList;

}