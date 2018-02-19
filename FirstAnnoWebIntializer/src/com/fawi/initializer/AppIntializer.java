package com.fawi.initializer;

import javax.servlet.Registration;
import javax.servlet.Registration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppIntializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		XmlWebApplicationContext rootAppContext=null;
		ContextLoaderListener cxtLoaderListener=null;
		
		XmlWebApplicationContext webContext=null;
		DispatcherServlet dispatcherServlet=null;
		
		rootAppContext=new XmlWebApplicationContext();
		rootAppContext.setConfigLocation("/WEB-INF/application-context.xml");
		cxtLoaderListener=new ContextLoaderListener(rootAppContext);
		servletContext.addListener(cxtLoaderListener);
		
		webContext=new XmlWebApplicationContext();
		webContext.setConfigLocation("/WEB-INF/dispatcher-servlet.xml");
		dispatcherServlet=new DispatcherServlet(webContext);
		ServletRegistration.Dynamic dynamic=servletContext.addServlet("dispatcherServlet", dispatcherServlet);
		dynamic.addMapping("*.htm");
	}

}
