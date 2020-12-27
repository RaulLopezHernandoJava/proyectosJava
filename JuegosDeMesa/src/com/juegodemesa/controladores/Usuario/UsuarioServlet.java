package com.juegodemesa.controladores.Usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.accesodatos.DaoMySql.UsuariosDaoMySql;
import com.juegodemesa.controladores.*;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Usuario;

@WebServlet("/user/datosUsuario")

public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Visualización de la pantalla de detalles del usuario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email  = request.getSession().getAttribute("email").toString();
		
		if(email != null) {
		
			Usuario usuario = UsuariosDaoMySql.getInstancia().obtenerPorEmail(email);
			
			request.setAttribute("usuario", usuario);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/usuario.jsp");
		requestDispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	

}
