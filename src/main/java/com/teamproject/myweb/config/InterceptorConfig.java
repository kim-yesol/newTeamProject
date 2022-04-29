package com.teamproject.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.teamproject.myweb.interceptor.CheckHandler;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Bean
	public CheckHandler checkHandler() {
		return new CheckHandler();
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor( checkHandler() )
				.addPathPatterns("/user/*")
				.addPathPatterns("/board/review*")
				.excludePathPatterns("/board/reviewBoard")
				.excludePathPatterns("/board/reviewDetail")
				.excludePathPatterns("/user/userLogin")
				.excludePathPatterns("/user/userFind")
				.excludePathPatterns("/user/userCheck");
		 
	}
	
	
}
