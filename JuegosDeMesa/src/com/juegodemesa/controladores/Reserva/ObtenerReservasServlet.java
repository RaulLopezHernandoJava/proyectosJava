package com.juegodemesa.controladores.Reserva;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.controladores.Configuracion;

@WebServlet("/user/reservasCarrito")
public class ObtenerReservasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ObtenerReservasServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailUser = request.getSession().getAttribute("email").toString();
		
		if(emailUser == "raullopezhernando@gmail.com") {
		request.setAttribute("reservas", Configuracion.daoReserva.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/usuario/carrito.jsp").forward(request, response);
		}
		
		if(emailUser != "raullopezhernando@gmail.com") {
			request.setAttribute("reservas", Configuracion.daoReserva.obtenerReservasPorEmail(emailUser));
			request.getRequestDispatcher("/WEB-INF/vistas/usuario/carrito.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
