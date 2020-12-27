package com.juegodemesa.controladores.Direcciones;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.accesodatos.DaoMySql.DireccionesDaoMySql;
import com.juegodemesa.modelos.Direccion;

@WebServlet("/user/datosDireccion")

public class DireccionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Visualización de la pantalla de detalles de la direccion del usuario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email  = request.getSession().getAttribute("email").toString();
		
		if(email != null) {
		
			Direccion direccion = DireccionesDaoMySql.getInstancia().obtenerPorEmail(email);

			System.out.println("Mostrar direccion" + direccion);
		
			
			request.setAttribute("direccion", direccion);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/usuario/direccion.jsp");
		requestDispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	

}

