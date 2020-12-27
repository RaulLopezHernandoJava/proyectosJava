package com.juegodemesa.accesodatos.MetodosUtilidades;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.ComunidadAutonoma;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Provincia;
import com.juegodemesa.modelos.Usuario;

public class UtilidadesDirecciones {

	public static void RedireccionarRedireccionNoCorrecta(HttpServletRequest request, HttpServletResponse response,
			Direccion direccionUsuario) throws ServletException, IOException {
		request.setAttribute("direccion", direccionUsuario);
		request.getRequestDispatcher("/WEB-INF/vistas/usuario/direccion.jsp").forward(request, response);
	}

	public static Direccion formularioDireccion(HttpServletRequest request) {

		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String direccion = request.getParameter("direccion");
		String codigoPostal = request.getParameter("codigoPostal");
		String ciudad = request.getParameter("ciudad");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		String stringComunidad = request.getParameter("comunidad");
		String stringProvincia = request.getParameter("provincia");
		String activeString = "1";

		// 2. Empaquetar en objeto del modelo (entidad)

		Boolean active = Boolean.parseBoolean(activeString);

		Usuario usuario = Configuracion.daoUsuario.obtenerPorEmail(email);
		System.out.println(usuario);
		Long idUsuario = usuario.getId();
		Provincia provincia = Configuracion.daoDireccion.obtenerProvinciaPorNombre(stringProvincia);

		ComunidadAutonoma comunidad = Configuracion.daoDireccion.obtenerComunidadAutonomaPorNombre(stringComunidad);

		Direccion direccionUsuario = new Direccion(id, nombre, apellidos, direccion, codigoPostal, ciudad, telefono,
				email, idUsuario, provincia, comunidad, active);

		return direccionUsuario;
	}

}
