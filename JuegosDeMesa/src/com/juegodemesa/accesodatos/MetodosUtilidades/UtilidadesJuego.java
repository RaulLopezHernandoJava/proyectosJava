package com.juegodemesa.accesodatos.MetodosUtilidades;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;

public class UtilidadesJuego {
	
	public static void redireccionarFormularioJuego(HttpServletRequest request, HttpServletResponse response, Juego juego)
			throws ServletException, IOException {
		request.setAttribute("juego", juego);
		request.getRequestDispatcher("/WEB-INF/vistas/admin/juego.jsp").forward(request, response);
	}
	
	

	public static Juego formularioJuego(HttpServletRequest request) {
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		String mecanicaTexto = request.getParameter("mecanica");
		String precio = request.getParameter("precio");
		String fechaPublicacion = request.getParameter("fecha-publicacion");

		Long mecanicaId = Long.parseLong(mecanicaTexto);

		Mecanica mecanica = Configuracion.daoMecanica.obtenerPorId(mecanicaId);

		Juego juego = new Juego(id, nombre, autor, editorial, mecanica, precio, fechaPublicacion);
		return juego;
	}
}
