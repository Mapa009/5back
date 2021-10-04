package kr.co.yooooon.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.common.util.FileUploadUtil;

@Controller
public class EmpImgController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	private ModelMap modelMap = new ModelMap();

	@PostMapping("/base/empImg")
	public ModelMap handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {

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
					System.out.println("-------------- file end --------------\n"); 
				}
		}

		String empCode = request.getParameter("empCode");
		String empImgUrl = null;
		try {
			if((multipartFile.getName() != null) && (multipartFile.getSize() > 0)){
				empImgUrl = FileUploadUtil.doFileUpload(request, multipartFile, empCode);
				String imgExtend = empImgUrl.substring(empImgUrl.lastIndexOf(".")+1);
				HashMap<String,Object> map = new HashMap<>();
				map.put("empCode",empCode);
				map.put("imgExtend",imgExtend);
				baseServiceFacade.registEmpImg(empCode , imgExtend);
				System.out.println("empImgUrl: "+empImgUrl);
			}
			modelMap.put("empImgUrl", empImgUrl);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "사진등록이 성공적으로 이루어졌습니다 . ");
		} catch (IOException e){
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "사진이 등록이 안 된다고");
		}
		return modelMap;
	}
}