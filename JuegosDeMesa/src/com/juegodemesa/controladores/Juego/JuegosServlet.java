package com.juegodemesa.controladores.Juego;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.controladores.Configuracion;

@WebServlet("/admin/listado")
public class JuegosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("juegos", Configuracion.dao.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/admin/juegos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		String mecanica = request.getParameter("mecanica");
		String precioMinimo = request.getParameter("min");
		String precioMaximo = request.getParameter("max");

		if (autor != null && autor.trim().length() > 0) {

			request.setAttribute("juegos", Configuracion.dao.filtrarJuegosAutor(autor));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/admin/juegos.jsp");
			requestDispatcher.forward(request, response);

		}

		if (editorial != null && editorial.trim().length() > 0) {
			request.setAttribute("juegos", Configuracion.dao.filtrarJuegosEditorial(editorial));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/admin/juegos.jsp");
			requestDispatcher.forward(request, response);

		}

		if (mecanica != null && mecanica.trim().length() > 0) {
			request.setAttribute("juegos", Configuracion.dao.filtrarJuegosMecanica(mecanica));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/vistas/admin/juegos.jsp");
			requestDispatcher.forward(request, response);
		}

		if (precioMinimo != null && precioMaximo == "") {


			Integer min = Integer.parseInt(precioMinimo);
			request.setAttribute("juegos", Configuracion.dao.filtrarJuegosPrecioMinimo(min));
			request.getRequestDispatcher("/WEB-INF/vistas/admin/juegos.jsp").forward(request, response);

		}

		if (precioMinimo == "" && precioMaximo != null) {

			Integer max = Integer.parseInt(precioMaximo);
			request.setAttribute("juegos", Configuracion.dao.filtrarJuegosPrecioMaximo(max));
			request.getRequestDispatcher("/WEB-INF/vistas/admin/juegos.jsp").forward(request, response);

		}

		if (precioMinimo != null && precioMaximo != null) {

			Integer min = Integer.parseInt(precioMinimo);
			Integer max = Integer.parseInt(precioMaximo);
			request.setAttribute("juegos", Configuracion.dao.filtrarJuegosEntreDosPrecios(min, max));
			request.getRequestDispatcher("/WEB-INF/vistas/admin/juegos.jsp").forward(request, response);

		}

	}
}
