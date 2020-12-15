package com.juegodemesa.listeners;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestUtf8Listener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent sre) {
	}

	public void requestInitialized(ServletRequestEvent sre) {
		try {
			System.out.println("Cambio de codificación UTF8 para request");
			sre.getServletRequest().setCharacterEncoding("utf8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Codificación no soportada");
		}
	}

}
