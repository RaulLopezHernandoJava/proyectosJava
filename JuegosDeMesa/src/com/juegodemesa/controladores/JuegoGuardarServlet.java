package com.juegodemesa.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;
import com.juegodemesa.modelos.Mensaje;

@WebServlet("/admin/guardar")
public class JuegoGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar la codificación a la hora de leer todos los parámetros a UTF8
		// request.setCharacterEncoding("utf8");

		// 1. Recepción de parámetros
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		String mecanicaTexto = request.getParameter("mecanica");
		System.out.println("Esto es mecanica texto " + mecanicaTexto);
		String imagen = request.getParameter("imagen");
		String fechaPublicacion = request.getParameter("fecha-publicacion");

		// 2. Empaquetar en objeto del modelo (entidad)

		Long mecanicaId = Long.parseLong(mecanicaTexto);

		Mecanica mecanica = Configuracion.daoMecanica.obtenerPorId(mecanicaId);

		Juego juego = new Juego(id, nombre, autor, editorial, mecanica, imagen,
				fechaPublicacion);
		Mensaje mensaje = new Mensaje();

		// 3. Tomar decisiones en base a los datos recibidos

		if (!juego.isCorrecto()) {
			request.setAttribute("juego", juego);
			request.getRequestDispatcher("/WEB-INF/vistas/admin/juego.jsp").forward(request, response);
			return;
		}

		String op = null;

		try {
			if (juego.getId() == null) {
				op = "inserción";
				Configuracion.dao.insertar(juego);

			} else {
				op = "modificación";
				Configuracion.dao.modificar(juego);
			}

			mensaje.setTextoMensaje("La" + op + "se ha realizado correctamente");
			mensaje.setTipoMensaje("success");
			// alertaMensaje = "La " + op + " se ha hecho correctamente";
			// alertaTipo = "success";

		} catch (AccesoDatosException e) {
			mensaje.setTextoMensaje("Ha habido un error en la " + op);
			mensaje.setTipoMensaje("danger");
			// alertaMensaje = "Ha habido un error en la " + op + ": " + e.getMessage();
			// alertaTipo = "danger";
			e.printStackTrace();
		}

		// 4. Preparar el modelo para la siguiente pantalla
		HttpSession session = request.getSession();

		session.setAttribute("alertamensaje", mensaje.getTextoMensaje());
		session.setAttribute("alertatipo", mensaje.getTipoMensaje());

		// 5. Redireccionar a la siguiente pantalla
		response.sendRedirect(request.getContextPath() + "/admin/listado");
	}

}
