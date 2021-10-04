package kr.co.yooooon.base.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FileUploadController {

	private ModelMap modelMap = new ModelMap();

	String newFileName;
	String originalFileExtension;
	String contentType;
	String uploadFileName;
	String fileNameExtension;
	String[] fileName;

	@RequestMapping(value = "/base/fileUpload")
	public ModelAndView fileUpload(HttpServletRequest request, HttpServletResponse response) {

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		String root = "C:\\Apache2\\htdocs\\file\\";

		File file = new File(root);
		if (file.exists() == false) {
			file.mkdirs();
		}

		List<MultipartFile> multipartFile = multipartHttpServletRequest.getFiles("uploadImgFile");

		for (MultipartFile mf : multipartFile) {
			if (!multipartFile.isEmpty()) {
				System.out.println(multipartFile);
				contentType = mf.getContentType();
				System.out.println("contentType ::: " + contentType);
				uploadFileName = mf.getOriginalFilename();
				System.out.println("uploadFileName ::: " + uploadFileName);
				fileName = uploadFileName.split("[.]");
				fileNameExtension = fileName[1];

				if (contentType.contains("/")) {
					originalFileExtension = "." + fileNameExtension;
				}
			}

			newFileName = "uploaded_" + fileName[0] + originalFileExtension;
			file = new File(root, newFileName);
			System.out.println("file->" + file);

			try {
				mf.transferTo(file);

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		modelMap.clear();

		modelMap.put("imgUrl", "/" + newFileName);
		modelMap.put("errorCode", 0);
		modelMap.put("errorMsg", "error~");
		System.out.println(modelMap);
		return new ModelAndView("redirect:http://localhost/base/notice.html", modelMap);
	}
}
