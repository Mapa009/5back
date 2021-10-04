package kr.co.yooooon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

	@Controller
	public class viewController {
	   
	   @RequestMapping("/{viewName}.html")
	   public String view(@PathVariable String viewName) {
		   System.out.println("공통객체: "+viewName);
	      return "/" + viewName;
	   }
	   
	   @RequestMapping("/{pack}/{viewName}.html")
	   public String packView(@PathVariable String pack, @PathVariable String viewName) {
	      System.out.println("공통객체: "+pack+" "+viewName);
	      return "/" + pack + "/" + viewName;
	      
	   }

	}

