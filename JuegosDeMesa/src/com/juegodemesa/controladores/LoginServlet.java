package com.juegodemesa.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.juegodemesa.accesodatos.UsuariosDaoMySql;
import com.juegodemesa.modelos.Usuario;

@WebServlet("/login") //{"/", "/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		Usuario usuario = UsuariosDaoMySql.getInstancia().obtenerPorEmail(email);
		System.out.println("Usuario en funcion del email" + usuario);
		
		if(usuario == null) {
			request.setAttribute("alertamensaje", "El usuario o contraseña son incorrectos");
			request.setAttribute("alertatipo", "danger");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		}
		
		if(usuario!=null) {
			String hashedPassword = usuario.getPassword();
			System.out.println("Pasword hasheado" + hashedPassword);
			
			if(BCrypt.checkpw(password, hashedPassword)) {
				request.getSession().setAttribute("email", email);
				response.sendRedirect(request.getContextPath() + "/admin/listado");
				//request.getRequestDispatcher(request.getContextPath() + "/admin/listado").forward(request, response);
				
			}else {
				request.setAttribute("alertamensaje", "El usuario o contraseña son incorrectos");
				request.setAttribute("alertatipo", "danger");
				request.setAttribute("email", email);
				request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
				
			}
			
		}
	
		
		
//		if(email.equals(email) && password.equals(password)) {
//			request.getSession().setAttribute("email", email);
//			response.sendRedirect(request.getContextPath() + "/admin/listado");
//		} else {
//			request.setAttribute("alertamensaje", "El usuario o contraseña son incorrectos");
//			request.setAttribute("alertatipo", "danger");
//			request.setAttribute("email", email);
//			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
//		}
	}

}
