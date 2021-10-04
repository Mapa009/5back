package kr.co.yooooon;

import javax.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import kr.co.yooooon.common.filter.SiteMeshFilter;

@SpringBootApplication
public class InsaBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsaBootApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<Filter> siteMeshFilter() {
	FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
	filter.setFilter(new SiteMeshFilter());

	return filter;
	}

}
