package com.juegodemesa.controladores.Direcciones;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.accesodatos.MetodosUtilidades.UtilidadesDirecciones;
import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.Direccion;

@WebServlet("/user/guardarDireccion")
public class DireccionUsuarioGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Direccion direccionUsuario = UtilidadesDirecciones.formularioDireccion(request);

		if (!direccionUsuario.isCorrecto()) {
			UtilidadesDirecciones.RedireccionarRedireccionNoCorrecta(request, response, direccionUsuario);

		}

		String alertaMensaje, alertaTipo, op = null;

		try {
			if (direccionUsuario.getId() == null) {
				op = "inserción";
				Configuracion.daoDireccion.insertar(direccionUsuario);

			} else {
				op = "modificación";
				Configuracion.daoDireccion.modificar(direccionUsuario);
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
		response.sendRedirect(request.getContextPath() + "/admin/listado");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

}
