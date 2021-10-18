package kr.co.yooooon.common.interceptor;

import com.tobesoft.xplatform.data.DataSetList;
import com.tobesoft.xplatform.data.Debugger;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import com.tobesoft.xplatform.tx.HttpPlatformRequest;
import com.tobesoft.xplatform.tx.HttpPlatformResponse;
import com.tobesoft.xplatform.tx.PlatformType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
@Component
public class XplatformInterceptor extends HandlerInterceptorAdapter {
	
	//컨트롤러 실행되기 전에 호출
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HTTP 요청으로부터 데이터(PlatformData)를 수신받는다
	    System.out.println("@@@@@@@@@@@@XplatformInterceptor preHandle메서드 작동");
        HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
        
        /*
        송수신 형식(contentType)의 설정되지 않은 경우 HTTP 헤더의 ContentType 값으로부터 판단하며,
        HTTP 헤더의 ContentType         적용되는 송수신 형식(contentType)
        text/xml                      PlatformType.CONTENT_TYPE_XML
        application/octet-stream      PlatformType.CONTENT_TYPE_BINARY
        그 외                          PlatformType.DEFAULT_CONTENT_TYPE
         */
        
        //정보를 추출해서 객체를 바꾼다.
        httpPlatformRequest.receiveData();
        
        //엑플에서 transaction 요청할 때, 입력값으로 보낼 Dataset의 ID들
        PlatformData reqData = httpPlatformRequest.getData();
        
        // transaction 처리 결과를 받을 Dataset의 ID들
        PlatformData resData = new PlatformData();
        
        debug(reqData.getDataSetList(), reqData.getVariableList());
        System.out.println("========================"+reqData.getDataSetList()+"=====================찍히는값");
        
<<<<<<< HEAD
        request.setAttribute("resData", resData);
        request.setAttribute("variableList", reqData.getVariableList());
        request.setAttribute("reqData", reqData);
=======
        request.setAttribute("reqData", reqData);
        request.setAttribute("variableList", reqData.getVariableList());
        request.setAttribute("resData", resData);
>>>>>>> min
       
        System.out.println("@@@@@@@@@@@@XplatformInterceptor preHandle메서드 종료");
        return true;
    }
    
    //컨트롤러 실행된 후에 호출
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       System.out.println("@@@@@@@@@@@@XplatformInterceptor postHandle메서드 접근");
       super.postHandle(request, response, handler, modelAndView);
    }
    
    //뷰에서 최종결과가 생성하는 일을 포함한 모든 일이 완료 -> 실행
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
       System.out.println("@@@@@@@@@@@@XplatformInterceptor afterCompletion메서드 접근");
       PlatformData resData = (PlatformData) request.getAttribute("resData");
        if(resData==null){
           return;
        }
        VariableList variableList = resData.getVariableList();
        if (exception != null) {
            exception.printStackTrace();
            variableList.add("ErrorCode", -1);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@Error : "+exception.getMessage());
            variableList.add("ErrorMsg", exception.getMessage().split(":")[1]);
        } else {
            variableList.add("ErrorCode", 0);
            variableList.add("ErrorMsg", "success");
        }
        HttpPlatformResponse httpPlatformResponse = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
        httpPlatformResponse.setData(resData);
        httpPlatformResponse.sendData();
        debug(resData.getDataSetList(), resData.getVariableList());
        resData = null;
        super.afterCompletion(request, response, handler, exception);
       System.out.println("@@@@@@@@@@@@XplatformInterceptor afterCompletion메서드 종료");
    }
   
    private void debug(DataSetList dataSetList, VariableList variableList) {
       System.out.println("@@@@@@@@@@@@XplatformInterceptor debug메서드 접근");
        Debugger debugger = new Debugger();
        // DEBUG - DataSet
        for (int n = 0; n < dataSetList.size(); n++) {
            System.out.println("debug11@@@@@@"+debugger.detail(dataSetList.get(n)));
        }
        // DEBUG - VariableList
        for (int n = 0; n < variableList.size(); n++) {
            System.out.println("debug22@@@@@@"+debugger.detail(variableList.get(n)));
        }
       System.out.println("@@@@@@@@@@@@XplatformInterceptor debug메서드 종료");
    }
}