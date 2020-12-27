package com.juegodemesa.accesodatos.MetodosUtilidades;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.juegodemesa.accesodatos.DaoTreeMap.RolesDaoTreeMap;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;


public class UtilidadesUsuario {

	public static void redireccionarFormularioUsuario(HttpServletRequest request, HttpServletResponse response,
			Usuario usuario) throws ServletException, IOException {
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("/WEB-INF/vistas/usuario.jsp").forward(request, response);
	}

	
	
	public static Usuario FormularioUsuario(HttpServletRequest request) {

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String edad = request.getParameter("edad");
		String fechaRegistro = request.getParameter("fecha-registro");

		String passwordEncriptado = BCrypt.hashpw(password, BCrypt.gensalt(10));

		Rol rolUsuario = RolesDaoTreeMap.getInstancia().obtenerPorId(2L);

		Usuario usuario = new Usuario(nombre, apellidos, email, passwordEncriptado, rolUsuario, edad, fechaRegistro);

		return usuario;
	}
	

	
	
}
