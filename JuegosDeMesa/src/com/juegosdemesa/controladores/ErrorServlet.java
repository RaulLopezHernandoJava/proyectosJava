package com.juegosdemesa.controladores;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ErrorServlet.class.getName());
	// https://docs.oracle.com/javase/8/docs/api/java/util/logging/Level.html

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorStatusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();

		Object tipoExcepcion = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);

		String errorExceptionType = null;
		if (tipoExcepcion != null) {
			errorExceptionType = tipoExcepcion.toString();
		}

		String errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();

		LOGGER.severe(errorStatusCode);

		if (tipoExcepcion != null) {
			LOGGER.severe(errorExceptionType);
		}
		
		LOGGER.severe(errorMessage);
		//LOGGER.log(Level.SEVERE, errorMessage);

		// log.severe(((Exception)
		// request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).getStackTrace().toString());

		request.getRequestDispatcher("/WEB-INF/vistas/error.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
