package org.zerock.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	

	//한글 깨짐 해결 
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("utf-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] {characterEncodingFilter};
	}

	//멀티파트리졸버 설정 
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("ThrowExceptionNoHandlerFound", "true");
		MultipartConfigElement multiconfig = new MultipartConfigElement(DEFAULT_SERVLET_NAME, 0, 0, 0);
		registration.setMultipartConfig(multiconfig);
	}
	
	//멀티파트리졸버 설정 
	
	
	
	
}
