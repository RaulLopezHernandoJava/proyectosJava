package com.juegodemesa.controladores.Direcciones;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.controladores.Configuracion;

@WebServlet("/admin/listadoDirecciones")
public class DireccionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DireccionesServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("direcciones", Configuracion.daoDireccion.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/admin/direcciones.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
