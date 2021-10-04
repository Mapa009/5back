package kr.co.yooooon.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtil2 {
	public static String doFileUpload(HttpServletRequest request, MultipartFile multipartFile, String cashCode)
			throws FileNotFoundException, IOException {

			InputStream in = multipartFile.getInputStream();
			String fileName = multipartFile.getName().substring(multipartFile.getName().lastIndexOf("\\")+1);
			System.out.println(multipartFile.getName()+"@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(fileName+"@@@@@@@!!!!!!!!!##############");
			String fileExt = fileName.substring(fileName.lastIndexOf("."));
			System.out.println(fileExt+"@@@@@@@@@#############!AAAAAAAAAAAAAAAAAAAAA");
			System.out.println(cashCode+"@@@@@@@@@@###aaaaaaaaaadddddddddddd");

			String saveFileName = cashCode + fileExt;
			System.out.println(saveFileName);
			
			String path1 = "C:\\Users\\kid42\\Desktop\\진영\\insa\\WebContent\\profile\\" + saveFileName;
			String path2 = "C:\\Users\\kid42\\insa\\insa2\\WebContent\\profile\\" + saveFileName;
			String path3 = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\insa2\\profile\\" + saveFileName;
			
			FileOutputStream fout1 = new FileOutputStream(path1);
			FileOutputStream fout2 = new FileOutputStream(path2);
			FileOutputStream fout3 = new FileOutputStream(path3);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				fout1.write(buffer, 0, bytesRead);
				fout2.write(buffer, 0, bytesRead);
				fout3.write(buffer, 0, bytesRead);
			}
			in.close();
			fout1.close();
			fout2.close();
			fout3.close();

			return "C:\\Users\\kid42\\insa\\insa2\\WebContent\\profile\\" + saveFileName;
		}
}
