package kr.co.yooooon.base.controller;

import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.common.util.EmpImgFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
public class SaveEmpImgController {

	/* empResgist.xfdl */
   // 사원의 이미지를 저장하는 메서드
    @Autowired
    EmpImgFileUploadUtil empImgFileUploadUtil;

   @RequestMapping("/base/saveEmpImg")
   public ModelAndView saveEmpImg(@RequestAttribute("reqData") PlatformData reqData) throws Exception {
      empImgFileUploadUtil.uploadFile(reqData,"emp"); //123\
      return null;
   }
}