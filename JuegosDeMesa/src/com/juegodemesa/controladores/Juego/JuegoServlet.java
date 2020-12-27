package com.juegodemesa.controladores.Juego;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.controladores.*;
import com.juegodemesa.modelos.Juego;

@WebServlet("/admin/juego")
/**
 * Servlet para el mantenimiento de una juego, facilitando las operaciones CRUD básicas
 */
public class JuegoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Visualización de la pantalla de detalles del juego
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("id");
		
		if(sId != null) {
			Long id = Long.parseLong(sId);
			Juego juego = Configuracion.dao.obtenerPorId(id);
			System.out.println(juego);
			
			request.setAttribute("juego", juego);
		}
		
		request.setAttribute("mecanicas", Configuracion.daoMecanica.obtenerTodos());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/admin/juego.jsp");
		requestDispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	

}
