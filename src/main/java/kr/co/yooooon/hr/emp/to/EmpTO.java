package kr.co.yooooon.hr.emp.to;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import kr.co.yooooon.base.to.BaseTO;
import kr.co.yooooon.base.to.DeptTO;
import kr.co.yooooon.common.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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