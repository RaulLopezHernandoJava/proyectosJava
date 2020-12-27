package com.juegodemesa.controladores.Reserva;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.accesodatos.DaoMySql.UsuariosDaoMySql;
import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mensaje;
import com.juegodemesa.modelos.Usuario;

@WebServlet("/user/reservaServlet")
public class ReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReservaServlet() {

	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Los dos parametros que necesitamos para sacar todos los datos
		
		String idJuego = request.getParameter("id");
		String emailUser = request.getSession().getAttribute("email").toString();
		String copia = request.getParameter("copia");

	
		
		if (idJuego != null && emailUser!=null) {
			
			// Obtenemos juego y Usuario
			Long id = Long.parseLong(idJuego);
			Juego juego = Configuracion.dao.obtenerPorId(id);
			Usuario usuario = UsuariosDaoMySql.getInstancia().obtenerPorEmail(emailUser);
			
			
			Mensaje mensaje = new Mensaje();

			try {
				if (juego.getId() != null) {
					
					Configuracion.daoReserva.insertarReserva(usuario,juego,copia);

		   }

				mensaje.setTextoMensaje("La inserccion se ha realizado correctamente");
				mensaje.setTipoMensaje("success");
		

			} catch (AccesoDatosException e) {
				mensaje.setTextoMensaje("Ha habido un error en la  inserccion");
				mensaje.setTipoMensaje("danger");
				e.printStackTrace();
			}
			
			// 4. Preparar el modelo para la siguiente pantalla
			HttpSession session = request.getSession();

			session.setAttribute("alertamensaje", mensaje.getTextoMensaje());
			session.setAttribute("alertatipo", mensaje.getTipoMensaje());

			// 5. Redireccionar a la siguiente pantalla
			response.sendRedirect(request.getContextPath() + "/user/reservasCarrito");
			
		}
	}

}
