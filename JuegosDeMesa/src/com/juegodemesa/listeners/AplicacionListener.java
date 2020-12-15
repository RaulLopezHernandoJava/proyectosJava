package com.juegodemesa.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class AplicacionListener
 *
 */
@WebListener
public class AplicacionListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Aplicación nueva arrancada");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Aplicación parada");		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Sesión creada");
		System.out.println(se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Sesión destruída");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println(se.getName());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	
}
