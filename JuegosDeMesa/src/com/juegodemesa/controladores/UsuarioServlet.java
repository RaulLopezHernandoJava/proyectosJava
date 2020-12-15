package com.juegodemesa.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.controladores.*;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Usuario;

@WebServlet("/registro")
/**
 * Servlet para el mantenimiento de una juego, facilitando las operaciones CRUD básicas
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Visualización de la pantalla de detalles del usuario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("id");
		
		if(sId != null) {
			Long id = Long.parseLong(sId);
		
			Usuario usuario = Configuracion.daoUsuario.obtenerPorId(id);
			
			request.setAttribute("usuario", usuario);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/usuario.jsp");
		requestDispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	

}
