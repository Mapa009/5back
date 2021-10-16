package kr.co.yooooon.hr.certificate.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class saveProofImgController {

	//영수증 이미지를 저장하는 메서드 
	@RequestMapping("/certificate/saveProofImg")
	public ModelAndView saveProofImg(@RequestAttribute("reqData") PlatformData reqData,
				 		   @RequestAttribute("resData") PlatformData resData) throws Exception {
		
		
		DataSet dataset = reqData.getDataSet("ds_img");
		FileOutputStream out=null; //파일로 바이트 단위의 출력을 내보내는 클래스 
		String fileName = (String) dataset.getObject(0, "PROOF_FILE_NAME");
		System.out.println("================ EmpImg : "+fileName);
		String filePath ="C:\\dev\\http\\httpd-2.4.48-win64-VS16\\Apache24\\htdocs\\proofImg\\";
		try {
			if (fileName != null) {
			out = new FileOutputStream(filePath+fileName); //파일 입출력 
			byte[] file = dataset.getBlob(0, "IMG_FILE_DATA"); //Blob : Binary Large Objects 의약자임  [이미지, 사운드, 동영상 등 대용량 바이너리 데이터 가져오기] 
			BufferedOutputStream  bufferedOut=new BufferedOutputStream(out); // FileOutputStream 값을 입출력 
			bufferedOut.write(file); 
			bufferedOut.flush(); //현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다. 
			bufferedOut.close();
			out.close();
			bufferedOut=null;
			out = null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return null;
	}
}
