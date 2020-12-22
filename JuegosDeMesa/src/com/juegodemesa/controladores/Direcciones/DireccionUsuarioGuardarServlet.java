package com.juegodemesa.controladores.Direcciones;

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
import com.juegodemesa.modelos.ComunidadAutonoma;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Mecanica;
import com.juegodemesa.modelos.Provincia;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;

@WebServlet("/user/guardarDireccion")
public class DireccionUsuarioGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    			// 1. Recepción de parámetros
    			String id = request.getParameter("id");
    			String nombre = request.getParameter("nombre");
    			String apellidos = request.getParameter("apellidos");
    			String direccion = request.getParameter("direccion");
    			String codigoPostal = request.getParameter("codigoPostal");
    			String ciudad = request.getParameter("ciudad");
    			String telefono = request.getParameter("telefono");
    			String email = request.getParameter("email");
    			String stringComunidad= request.getParameter("comunidad");
    			String stringProvincia = request.getParameter("provincia");
    			String activeString = "1";
    			

    			// 2. Empaquetar en objeto del modelo (entidad)
    			
    			Boolean active = Boolean.parseBoolean(activeString);
    			System.out.println("Boolean actuve" + active);
    			Long comunidadId = Long.parseLong(stringComunidad);
    			Long provinciaId = Long.parseLong(stringProvincia);
    			
    			Usuario usuario = Configuracion.daoUsuario.obtenerPorEmail(email);
    			Long idUsuario = usuario.getId();
    			
    			
    			
    			
    			Direccion direccionUsuario = new Direccion(id,nombre, apellidos, direccion, codigoPostal, ciudad,telefono,email,idUsuario,provinciaId,comunidadId,active);
    			System.out.println(direccionUsuario);

    			// 3. Tomar decisiones en base a los datos recibidos

    			if (!direccionUsuario.isCorrecto()) {
    				request.setAttribute("direccion", direccion);
    				request.getRequestDispatcher("/WEB-INF/vistas/usuario/direccion.jsp").forward(request, response);
    				return;
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
    			response.sendRedirect(request.getContextPath() + "/admin/listadoDirecciones");
	}
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	
}
