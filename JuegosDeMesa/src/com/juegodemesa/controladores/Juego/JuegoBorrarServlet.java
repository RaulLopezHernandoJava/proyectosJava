package com.juegodemesa.controladores.Juego;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.controladores.Configuracion;

@WebServlet("/admin/borrar")
public class JuegoBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("id");
		
		
		Long id = Long.parseLong(sId);
		
		String alertaMensaje, alertaTipo;
		
		try {
			Configuracion.dao.borrar(id);
			
			alertaMensaje = "Registro " + id + " borrado correctamente";
			alertaTipo = "success";
		} catch (AccesoDatosException e) {
			alertaMensaje = "El registro a borrar no existe";
			alertaTipo = "warning";
		}
		
		HttpSession session = request.getSession();
		
		session.setAttribute("alertatipo", alertaTipo);
		session.setAttribute("alertamensaje", alertaMensaje);
		
		response.sendRedirect(request.getContextPath() + "/admin/listado");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
