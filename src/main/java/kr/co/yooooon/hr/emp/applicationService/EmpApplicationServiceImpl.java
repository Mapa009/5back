package kr.co.yooooon.hr.emp.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.yooooon.base.repository.DeptRepository;
import kr.co.yooooon.base.to.MenuTO;
import kr.co.yooooon.hr.emp.repository.*;
import kr.co.yooooon.hr.emp.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.yooooon.base.applicationService.BaseApplicationService;
import kr.co.yooooon.base.dao.DeptDAO;
import kr.co.yooooon.base.to.DeptTO;
import kr.co.yooooon.hr.emp.dao.CareerInfoDAO;
import kr.co.yooooon.hr.emp.dao.EducationInfoDAO;
import kr.co.yooooon.hr.emp.dao.EmpDAO;
import kr.co.yooooon.hr.emp.dao.FamilyInfoDAO;
import kr.co.yooooon.hr.emp.dao.LicenseInfoDAO;
import kr.co.yooooon.hr.emp.dao.RecordFamilyInfoDAO;
import kr.co.yooooon.hr.emp.dao.WorkInfoDAO;
import kr.co.yooooon.hr.salary.applicationService.SalaryApplicationService;

import javax.inject.Inject;

@Component
public class EmpApplicationServiceImpl implements EmpApplicationService {
	@Autowired
	private EmpDAO empDAO;
	@Autowired
	private WorkInfoDAO workInfoDAO;
	@Autowired
	private CareerInfoDAO careerInfoDAO;
	@Autowired
	private EducationInfoDAO educationInfoDAO;
	@Autowired
	private LicenseInfoDAO licenseInfoDAO;
	@Autowired
	private FamilyInfoDAO familyInfoDAO;
	@Autowired
	private DeptDAO deptDAO;
	@Autowired
	private RecordFamilyInfoDAO recordFamilyInfoDAO;
	@Autowired
	private SalaryApplicationService salaryApplication;
	@Autowired
	private BaseApplicationService baseApplicationService;
	@Autowired
	private EmpRepository empRepository;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private DeptRepository deptRepository;
	@Autowired
	private WorkInfoRepository workInfoRepository;
	@Autowired
	private CareerInfoRepository careerInfoRepository;
	@Autowired
	private EducationInfoRepository educationInfoRepository;
	@Autowired
	private FamilyInfoRepository familyInfoRepository;
	@Autowired
	private LicenseInfoRepository licenseInfoRepository;
	@Autowired
	private RecordFamilyRepository recordFamilyRepository;
	@Autowired
	private EmpAuthGroupRepository empAuthGroupRepository;

	@Override
	public EmpTO selectEmp(String name) {
		EmpTO empto=null;
		empto = empRepository.findEmpInfoByEmpName(name);
		if(empto!=null){
			DeptTO deptto = deptRepository.findDeptNameByDeptCode(empto.getDeptCode());
			PositionTO positionTO = positionRepository.findPositionByPositionCode(empto.getPositionCode());
			empto.setPosition(positionTO.getPosition());
			empto.setDeptName(deptto.getDeptName());
		}
		return empto;
	}
	
	@Override
	public String findLastEmpCode() {
		String empCode = empDAO.selectLastEmpCode();
		return empCode;
	}
	
	@Override
	public EmpTO findAllEmployeeInfo(String empCode) {
		EmpTO empTO = empRepository.findEmpInfoByEmpCode(empCode);
		DeptTO deptto = deptRepository.findDeptNameByDeptCode(empTO.getDeptCode());
		PositionTO positionTO = positionRepository.findPositionByPositionCode(empTO.getPositionCode());
		empTO.setPosition(positionTO.getPosition());
		empTO.setDeptName(deptto.getDeptName());
		return empTO;
	}
	
	@Override
	public ArrayList<EmpTO> findEmployeeListByDept(String deptName) {
		ArrayList<EmpTO> empList = null;
		System.out.println(deptName);
		DeptTO deptTO=null;
		String deptCode=null;
		if (deptName.equals("????????????")) {
			empList = (ArrayList<EmpTO>) empRepository.findAll();
			System.out.println(empList);
		} else if (deptName.substring(deptName.length()-1, deptName.length()).equals("???")) {
			deptTO = deptRepository.findDeptCodeByDeptName(deptName);
			deptCode = deptTO.getDeptCode();
			empList = empRepository.findEmpInfoByDeptCode(deptCode);
			for(EmpTO emp:empList){
				emp.setDeptName(deptName);
				PositionTO position = positionRepository.findPositionByPositionCode(emp.getPositionCode());
				emp.setPosition(position.getPosition());
			}
		} else {
			//???????????? dept??? ?????????????????? ?????????
			empList = empRepository.findEmpListByEmpName(deptName);
			for(EmpTO emp : empList){
				deptTO = deptRepository.findDeptNameByDeptCode(emp.getDeptCode());
				emp.setDeptName(deptTO.getDeptName());
				PositionTO position = positionRepository.findPositionByPositionCode(emp.getPositionCode());
				emp.setPosition(position.getPosition());
			}
		}
		return empList;
	}
	
	@Override
	public void registEmployee(EmpTO emp) { //??????????????? ?????????
		HashMap<String , Object> map = new HashMap<String , Object>();
		System.out.println(emp.getEmpCode());
		map.put("empCode",emp.getEmpCode());
		map.put("empName",emp.getEmpName());
		map.put("birthdate",emp.getBirthdate());
		map.put("gender",emp.getGender());
		map.put("mobileNumber",emp.getMobileNumber());
		map.put("address",emp.getAddress());
		map.put("detailAddress",emp.getDetailAddress());
		map.put("postNumber",emp.getPostNumber());
		map.put("email",emp.getEmail());
		map.put("lastSchool",emp.getLastSchool());
		map.put("imgExtend",emp.getImgExtend());
		map.put("deptName",emp.getDeptName());
		map.put("position",emp.getPosition());
		map.put("hobong",emp.getHobong());
		map.put("occupation",emp.getOccupation());
		map.put("employment",emp.getEmployment());
		
		System.out.println("Check :                          :                                 :"+emp.getBirthdate());
		
		empDAO.registEmployee(map); 			    // ???????????? ?????? ??????????????? ?????? ??? 
		baseApplicationService.registEmpCode(emp);  // ????????? ????????? ???????????? ????????? ????????? ????????? 
	}
	
	@Override
	public void modifyEmployee(EmpTO emp) {
		if (emp.getStatus().equals("update")) {
			empDAO.updateEmployee(emp);
		}
		if (emp.getWorkInfoList() != null) {
			List<WorkInfoTO> workInfoList = emp.getWorkInfoList();
			for (WorkInfoTO workInfo : workInfoList) {
				System.out.println("???????????????@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("@@@"+workInfo.getStatus());
				switch (workInfo.getStatus()) {
				case "insert":
					workInfoRepository.save(workInfo);
					//workInfoDAO.insertWorkInfo(workInfo);
					break;
				case "update":
					workInfoRepository.save(workInfo);
					//workInfoDAO.updateWorkInfo(workInfo);
					break;
				case "delete":
					workInfoRepository.delete(workInfo);
					//workInfoDAO.deleteWorkInfo(workInfo);
					break;
				}
			}
		}
		if (emp.getCareerInfoList() != null && emp.getCareerInfoList().size() > 0) {
			List<CareerInfoTO> careerInfoList = emp.getCareerInfoList();
			for (CareerInfoTO careerInfo : careerInfoList) {
				switch (careerInfo.getStatus()) {
				case "insert":
					careerInfoRepository.save(careerInfo);
					break;
				case "update":
					careerInfoRepository.save(careerInfo);
					break;
				case "delete":
					careerInfoRepository.delete(careerInfo);
					break;
				}
			}
		}		
		
		if (emp.getEducationInfoList() != null && emp.getEducationInfoList().size() > 0) {
			List<EducationInfoTO> educationInfoList = emp.getEducationInfoList();
			for (EducationInfoTO educationInfo : educationInfoList) {
				switch (educationInfo.getStatus()) {
				case "insert":
					educationInfoRepository.save(educationInfo);
					break;
				case "update":
					educationInfoRepository.save(educationInfo);
					break;
				case "delete":
					educationInfoRepository.delete(educationInfo);
					break;
				}
			}
		}		
		
		if (emp.getLicenseInfoList() != null && emp.getLicenseInfoList().size() > 0) {
			List<LicenseInfoTO> licenseInfoList = emp.getLicenseInfoList();
			for (LicenseInfoTO licenseInfo : licenseInfoList) {
				switch (licenseInfo.getStatus()) {
				case "insert":
					licenseInfoRepository.save(licenseInfo);
					break;
				case "update":
					licenseInfoRepository.save(licenseInfo);
					break;
				case "delete":
					licenseInfoRepository.delete(licenseInfo);
					break;
				}
			}
		}		
		
		if (emp.getFamilyInfoList() != null && emp.getFamilyInfoList().size() > 0) {
			List<FamilyInfoTO> familyInfoList = emp.getFamilyInfoList();
			for (FamilyInfoTO familyInfo : familyInfoList) {
				switch (familyInfo.getStatus()) {
				case "insert":
					familyInfoRepository.save(familyInfo);
					break;
				case "update":
					familyInfoRepository.save(familyInfo);
					break;
				case "delete":
					familyInfoRepository.delete(familyInfo);
					break;
				}
			}
		}
	}
	@Override
	public void deleteEmpList(ArrayList<EmpTO> empList) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		  for(EmpTO emp : empList){
			  map.put("empCode",emp.getEmpCode());
			  //??????????????? ??????
			  empDAO.deleteEmployee(map);
			  baseApplicationService.deleteEmpCode(emp);
		  } 
	} 
	
	@Override
	public ArrayList<DeptTO> findDeptList() {
		ArrayList<DeptTO> deptList = (ArrayList<DeptTO>) deptRepository.findAll();
		return deptList;
	}

	@Override
	public ArrayList<PositionTO> selectPositionList() {
		ArrayList<PositionTO> positionList = (ArrayList<PositionTO>) positionRepository.findAll();
		return positionList;
	}

	public String getEmpCode(String name) {
		EmpTO empTO = empRepository.findEmpInfoByEmpName(name);
		String empCode= empTO.getEmpCode();
		return empCode;
	}

	@Override
	   public RecordFamilyInfoTO findRecordEmpInfo(String empCode) { /* ????????? ?????? ?????? ????????? onRowClicked ?????? */
	      RecordFamilyInfoTO recordempTO = new RecordFamilyInfoTO();
	      ArrayList<RecordFamilyInfoTO> familyInfoList = recordFamilyRepository.findAllByEmpCode(empCode);   //????????????
	      recordempTO.setRecordFamilyInfoList(familyInfoList); //???????????? ??????
	      return recordempTO;
	   }
	   
	   @Override
	   public void modifyRecordEmployee(List<RecordFamilyInfoTO> emp) {   /* ???????????? */
	      for (RecordFamilyInfoTO info : emp) {
	         switch (info.getStatus()) {
	            case "update":
	               recordFamilyRepository.save(info);
	               break;
	            
	            case "insert":
	               recordFamilyRepository.save(info);
	               break;
	   
	            case "delete":
	               recordFamilyRepository.deleteById(info.getFamilyCode());
	               break;
	         }
	      }
	   }
	@Override
	public ArrayList<MenuTO> findAccessableMenu(String empCode){
		return empDAO.findAccessableMenu(empCode);
	}
	@Override
	public List<EmpAuthGroupTO> findEmpAuthGroup(){
		return empAuthGroupRepository.findAll();
	}
	@Override
	public void saveEmpAuthorityGroup(ArrayList<EmpAuthGroupTO> saveEmpAuthGroup){
		empAuthGroupRepository.saveAll(saveEmpAuthGroup);
	}
	@Override
	public void deleteEmpAuthorityGroup(HashMap<String, Object> map) {
		empDAO.deleteEmpAuthorityGroup(map);
	}

	@Override
	public ArrayList<EmpTO> listByMonthSal(String value) {
		return empDAO.selectEmpByMonthSal(value);
	}
}