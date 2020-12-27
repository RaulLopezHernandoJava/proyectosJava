package com.juegodemesa.controladores.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.accesodatos.DaoTreeMap.RolesDaoTreeMap;
import com.juegodemesa.accesodatos.MetodosUtilidades.UtilidadesUsuario;
import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;

/**
 * Servlet implementation class UsuarioGuardarServlet
 */
@WebServlet("/user/guardar")
public class UsuarioGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = FormularioUsuario(request);

		if (!usuario.isCorrecto()) {
			UtilidadesUsuario.redireccionarFormularioUsuario(request, response, usuario);
		}

		String alertaMensaje, alertaTipo, op = null;

		try {
			if (usuario.getId() == null) {
				op = "inserción";
				Configuracion.daoUsuario.insertar(usuario);

			} else {
				op = "modificación";
				Configuracion.daoUsuario.modificar(usuario);
			}

			alertaMensaje = "La " + op + " se ha hecho correctamente";
			alertaTipo = "success";
		} catch (AccesoDatosException e) {
			alertaMensaje = "Ha habido un error en la " + op + ": " + e.getMessage();
			alertaTipo = "danger";
			e.printStackTrace();
		}

		// 4. Preparar el modelo para la siguiente pantalla
		HttpSession session = request.getSession();

		session.setAttribute("alertamensaje", alertaMensaje);
		session.setAttribute("alertatipo", alertaTipo);

		// 5. Redireccionar a la siguiente pantalla
		response.sendRedirect(request.getContextPath() + "/admin/listadoUsuarios");
	}



	public Usuario FormularioUsuario(HttpServletRequest request) {
		// 1. Recepción de parámetros
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String edad = request.getParameter("edad");
		String fechaRegistro = request.getParameter("fecha-registro");

		String passwordEncriptado = BCrypt.hashpw(password, BCrypt.gensalt(10));

		// 2. Empaquetar en objeto del modelo (entidad)

		Rol rolUsuario = RolesDaoTreeMap.getInstancia().obtenerPorId(2L);
		// Rol rolUsuario = new Rol(2L, null, null);

		Usuario usuario = new Usuario(nombre, apellidos, email, passwordEncriptado, rolUsuario, edad, fechaRegistro);
		System.out.println(usuario);
		return usuario;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
}
