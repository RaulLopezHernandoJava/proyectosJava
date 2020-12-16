package com.juegodemesa.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.accesodatos.UsuariosDaoMySql;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Usuario;

@WebServlet("/user/carritoServlet")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CarritoServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Los dos parametros que necesitamos para sacar todos los datos
		String idJuego = request.getParameter("id");
		String emailUser = request.getParameter("email");
		
		ArrayList<Juego> juegosCarrito = new ArrayList<Juego>();
	
		// Vamos cogiendo los juegos que se van añadiendo en el carrito y los metemos en un
		// Arraylist "Juegos Carrito
		
		if (idJuego != null && emailUser!=null) {
			
			Long id = Long.parseLong(idJuego);
			Juego juego = Configuracion.dao.obtenerPorId(id);
			Usuario usuario = UsuariosDaoMySql.getInstancia().obtenerPorEmail(emailUser);
			juegosCarrito.add(juego);
			request.setAttribute("juegosCarrito", juegosCarrito);
			request.setAttribute("usuarioCarrito",usuario);
		}

		request.setAttribute("mecanicas", Configuracion.daoMecanica.obtenerTodos());
		

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/user/carrito.jsp");
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
