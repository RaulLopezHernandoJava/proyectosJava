package com.juegodemesa.controladores.Reserva;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.controladores.Configuracion;

@WebServlet("/user/BorrarReserva")
public class ReservaBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReservaBorrarServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String sId = request.getParameter("id");
	System.out.println("Recojo el parametro para borrar" + sId);
		
		Long idReserva = Long.parseLong(sId);
		
		String alertaMensaje, alertaTipo;
		
		try {
			Configuracion.daoReserva.borrarReserva(idReserva);
			
			alertaMensaje = "Reserva" + idReserva + " borrada correctamente";
			alertaTipo = "success";
		} catch (AccesoDatosException e) {
			alertaMensaje = "La reserva  a borrar no existe";
			alertaTipo = "warning";
		}
		
		HttpSession session = request.getSession();
		
		session.setAttribute("alertatipo", alertaTipo);
		session.setAttribute("alertamensaje", alertaMensaje);
		
		response.sendRedirect(request.getContextPath() + "/user/reservasCarrito");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
