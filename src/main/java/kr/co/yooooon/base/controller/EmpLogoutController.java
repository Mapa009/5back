package kr.co.yooooon.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpLogoutController {
	private ModelAndView mav = null;
	private ModelMap map = new ModelMap();
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		String viewName="redirect:loginForm.html"; //redirect 할 때는 property에 등록된 key 값과 동일해야 한다
		
		try{
			request.getSession().invalidate();
		}catch(Exception e){
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
			viewName="error";
		}
		
		mav = new ModelAndView(viewName , map);
		return mav;
	}
}

