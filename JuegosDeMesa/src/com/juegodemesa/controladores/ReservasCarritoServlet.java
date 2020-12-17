package com.juegodemesa.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/reservasCarrito")
public class ReservasCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReservasCarritoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("reservas", Configuracion.daoReserva.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/usuario/carrito.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
