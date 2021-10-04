package kr.co.yooooon.base.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.yooooon.common.util.FileUploadUtil2;
import kr.co.yooooon.hr.certificate.sf.CertificateServiceFacade;



@RestController
public class proofImgController {
	@Autowired
	private  CertificateServiceFacade certificateServiceFacade;

	private ModelMap modelMap = new ModelMap();

	/* private HashMap<String,Object> map = new HashMap<>(); */
	@PostMapping("/certificate/proofImg")
	public ModelMap handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws FileUploadException {
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)request; 
		Iterator<String> iterator = mhsr.getFileNames(); 
		MultipartFile multipartFile = null; 
		while(iterator.hasNext()){ 
			multipartFile = mhsr.getFile(iterator.next());
			if(multipartFile.isEmpty() == false){ 
				System.out.println("------------- file start -------------");
				System.out.println("name : "+multipartFile.getName());
				System.out.println("filename : "+multipartFile.getOriginalFilename());
				System.out.println("size : "+multipartFile.getSize());
				System.out.println("-------------- file end --------------\n"); }

		}
		String cash = request.getParameter("cashCode");
		String empImgUrl = null;
		String proofImg = null;
        try {
        	
        	if((multipartFile.getName() != null) && (multipartFile.getSize() > 0)){
        		System.out.println(cash+"---------------------------------");
				empImgUrl = FileUploadUtil2.doFileUpload(request, multipartFile, cash);
				System.out.println(empImgUrl+"-------------------------------");
				proofImg = empImgUrl.substring(empImgUrl.lastIndexOf(".")+1);
				System.out.println(proofImg+"------------------------");
				
				System.out.println(certificateServiceFacade+"@#@#@#@#");
				System.out.println(cash);
				System.out.println(proofImg);
				certificateServiceFacade.rsgistProofImg(cash,proofImg);
				System.out.println("@@@#???");
				
			}
	        
	        
	       modelMap.put("empImgUrl", empImgUrl);
	       modelMap.put("errorCode", 0);
	       modelMap.put("errorMsg", "?���? ???��?�� ?��공했?��?��?��");
       
        } catch (IOException e){
        	
        	modelMap.put("errorCode", -1);
        	modelMap.put("errorMsg", "?���? ???��?�� ?��?��?��?��?��?��");
        }
 
       
		return modelMap;
	}

}