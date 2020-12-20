package com.juegodemesa.controladores.DireccionesServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.accesodatos.RolesDaoTreeMap;
import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;

@WebServlet("/user/guardarDireccion")
public class DireccionUsuarioGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    			// 1. Recepción de parámetros
    			String nombre = request.getParameter("nombre");
    			String apellidos = request.getParameter("apellidos");
    			String pais = request.getParameter("pais");
    			String direccion = request.getParameter("direccion");
    			String codigoPostal = request.getParameter("codigoPostal");
    			String ciudad = request.getParameter("ciudad");
    			String provincia = request.getParameter("provincia");
    			String telefono = request.getParameter("telefono");
    			String email = request.getParameter("email");
    			

    			// 2. Empaquetar en objeto del modelo (entidad)
    			

    			Direccion direccionUsuario = new Direccion(nombre, apellidos, pais, direccion, codigoPostal, ciudad, provincia,telefono,email);
    			System.out.println(direccionUsuario);

    			// 3. Tomar decisiones en base a los datos recibidos

    			if (!direccionUsuario.isCorrecto()) {
    				request.setAttribute("direccion", direccionUsuario);
    				request.getRequestDispatcher("/WEB-INF/vistas/usuario.jsp").forward(request, response);
    				return;
    			}

    			String alertaMensaje, alertaTipo, op = null;

    			try {
    				if (direccionUsuario.getId() == null) {
    					op = "inserción";
    					Configuracion.daoUsuario.insertar(direccionUsuario);

    				} else {
    					op = "modificación";
    					Configuracion.daoUsuario.modificar(direccionUsuario);
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
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	
}
