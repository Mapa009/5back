package kr.co.yooooon.common.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class homeController extends WebMvcConfigurerAdapter{
	
	  
	/*
	 * @Value("${resources.location}") private String resourcesLocation;
	 * 
	 * @Value("${resources.uri_path:}") private String resourcesUriPath;
	 */
	 
	
       @Override
       public void addViewControllers(ViewControllerRegistry registry ) {
           registry.addViewController( "/" ).setViewName( "redirect:/loginForm.html" ); //welcome page
           registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
          
       }

		/*
		 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 * // TODO Auto-generated method stub
		 * registry.addResourceHandler(resourcesUriPath +
		 * "/**").addResourceLocations("file://" + resourcesLocation); }
		 */

}