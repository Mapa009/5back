package kr.co.yooooon.base.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.ReportSalaryTO;
import kr.co.yooooon.base.to.ReportTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class EmpReportController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	ModelMap modelMap = new ModelMap();

	@RequestMapping(value = "/base/empReport")
	public ModelMap requestEmployment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("empCode") String empCode, @RequestParam("usage") String usage,
			@RequestParam("requestDay") String requestDay, @RequestParam("useDay") String useDay) { // 재직증명서 신청

		try {
			ReportTO to = baseServiceFacade.viewReport(empCode);
			JasperReport jasperReport = JasperCompileManager
					.compileReport((request.getServletContext().getRealPath("/report/employment.jrxml")));
			// JasperCompileManager = 네트워크전송을위한보고서디자인개체를얻기위한클래스
			// 보고서 컴파일 기능을 제공

			JRDataSource datasource = new JREmptyDataSource();
			// 내부에 지정된 수의 가상 레코드로 데이터소스를 시뮬레이트하는 클래스

			modelMap.put("empName", to.getEmpName());
			modelMap.put("hiredate", to.getHiredate());
			modelMap.put("occupation", to.getOccupation());
			modelMap.put("employmentType", to.getEmploymentType());
			modelMap.put("position", to.getPosition());
			modelMap.put("address", to.getAddress());
			modelMap.put("detailAddress", to.getDetailAddress());
			modelMap.put("deptName", to.getDeptName());
			modelMap.put("usage", usage);
			modelMap.put("date", requestDay);
			modelMap.put("end", useDay);

			OutputStream outputStream = null;

			// 결과가 올바로 넘어 왔는지 출력으로 확인
			for (String key : modelMap.keySet()) {
				System.out.println(key);
				System.out.println(modelMap.get(key));
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, modelMap, datasource);
			// 맵에 담았던 값과 제스퍼 리포트를 인자값에 넣어서 JasperPrint를 실행한다.
			// JasperFillManager= 보고서디자인에 데이터를 채우는 클래스
			// fillReport (jasperReport형식, Map형식, dataSource형식)

			outputStream = response.getOutputStream(); // 응답되어진것에대한적합한 OutputStream을 반환해줌
			response.setContentType("application/pdf"); // PDF형식으로 변환!
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\sts11\\Spring11\\insaMyBatis2\\src\\main\\webapp\\report\\test01.pdf"); // 본인 이클립스 경로
			JasperExportManager.exportReportToPdfFile(jasperPrint,(request.getServletContext().getRealPath("/report/test01.pdf")));
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			// JasperExportManager = 생성된보고서를 pdf,html,xml 형식으로 내보내는 class
			// 첫번째 매개변수로 생성 된 보고서를 pdf 형식으로 두번째 매개변수로 지정된 출력스트림에 사용함
			outputStream.flush();
		} catch (Exception e) {
			System.out.println("오류 발생 !");
			System.out.println(e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/base/empsalReport", params = "empCode")
	public ModelMap requestMonthSalary(HttpServletRequest request, HttpServletResponse response, 
		@RequestParam("empCode") String empCode, @RequestParam("applyMonth") String applyMonth) { // 월급여신

		try {
			ReportSalaryTO to = baseServiceFacade.viewSalaryReport(empCode, applyMonth);

			JasperReport jasperReport = JasperCompileManager.compileReport((request.getServletContext().getRealPath("/report/SalaryStatement.jrxml")));

			JRDataSource datasource = new JREmptyDataSource();

			modelMap.put("empName", to.getEmpName());
			modelMap.put("position", to.getPosition());
			modelMap.put("deptName", to.getDeptName());
			modelMap.put("hiredate", to.getHiredate());
			modelMap.put("applyYearMonth", to.getApplyYearMonth());
			modelMap.put("totalExtSal", to.getTotalExtSal());
			modelMap.put("totalDeduction", to.getTotalDeduction());
			modelMap.put("totalPayment", to.getTotalPayment());
			modelMap.put("realSalary", to.getRealSalary());
			modelMap.put("salary", to.getSalary());
			modelMap.put("cost", to.getCost());
			modelMap.put("healthIns", to.getHealthIns());
			modelMap.put("goyongIns", to.getGoyongIns());
			modelMap.put("janggiIns", to.getJanggiIns());
			modelMap.put("gukmin", to.getGukmin());

			OutputStream outputStream = null;
			// 결과가 올바로 넘어 왔는지 출력으로 확인
			for (String key : modelMap.keySet()) {
				System.out.println(key);
				System.out.println(modelMap.get(key));
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, modelMap, datasource); // 맵에 담았던 값과 제스퍼 리포트를 인자값에 넣어서 JasperPrint를  실행한다.

			outputStream = response.getOutputStream(); // 응답되어진것에대한적합한 OutputStream을 반환해줌
			response.setContentType("application/pdf"); // PDF형식으로 변환!
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			outputStream.flush();

		} catch (Exception e) {
			System.out.println("오류 발생 !");
			System.out.println(e.getMessage());
		}
		return modelMap;
	}
}