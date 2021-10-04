package kr.co.yooooon.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtil {
	public static String doFileUpload(HttpServletRequest request, MultipartFile multipartFile, String empCode) throws  IOException {
		
		InputStream in = multipartFile.getInputStream();
		String fileName = multipartFile.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf("."));
 
		String saveFileName = empCode + fileExt;

		File file = new File("C:\\dev\\Apache24\\htdocs\\insa4th\\profile\\"); 
		if( file.exists() ){ //�뤃�뜑 議댁옱�뿬遺��솗�씤 
			if(file.isDirectory()){ //�뙆�씪�씠 �뵒�젆�넗由ъ씤吏� �솗�씤 
				File[] files = file.listFiles(); 
				for( int i=0; i<files.length; i++){ 
					String fullName = files[i].getName();
					String fName = fullName.substring(0, fullName.lastIndexOf("."));
					if(fName.equals(empCode)){
						files[i].delete();
					}
				} 
			}
		}
		String path2 = "C:\\dev\\Project\\Insa\\Insa_boot\\src\\main\\webapp\\profile\\" + saveFileName;
		FileOutputStream fout2 = new FileOutputStream(path2);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
			//fout1.write(buffer, 0, bytesRead);
			fout2.write(buffer, 0, bytesRead);
		}
		in.close();
		fout2.close();
		System.out.println("zzzzzzzzADASDQWDQWDQW@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return "C:\\dev\\Project\\Insa\\Insa_boot\\src\\main\\webapp\\profile\\" + saveFileName;
	}
}
