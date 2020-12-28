package com.juegodemesa.controladores.Juego;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.accesodatos.MetodosUtilidades.UtilidadesJuego;
import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mensaje;

@WebServlet("/admin/guardar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class JuegoGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "imgs-juegos";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Juego juego = UtilidadesJuego.formularioJuego(request);

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		String fileName;

		Part part = request.getPart("imagen");

		fileName = part.getSubmittedFileName();

		String fichero = uploadPath + File.separator + juego.getId() + ".jpg";
		part.write(fichero);

		Mensaje mensaje = new Mensaje();

		if (!juego.isCorrecto()) {
			UtilidadesJuego.redireccionarFormularioJuego(request, response, juego);
		}

		String op = null;

		try {
			if (juego.getId() == null) {
				op = "inserción";
				Configuracion.dao.insertar(juego);

			} else {
				op = "modificación";
				Configuracion.dao.modificar(juego);
			}

			mensaje.setTextoMensaje("La" + op + "se ha realizado correctamente");
			mensaje.setTipoMensaje("success");
			// alertaMensaje = "La " + op + " se ha hecho correctamente";
			// alertaTipo = "success";

		} catch (AccesoDatosException e) {
			mensaje.setTextoMensaje("Ha habido un error en la " + op);
			mensaje.setTipoMensaje("danger");
			// alertaMensaje = "Ha habido un error en la " + op + ": " + e.getMessage();
			// alertaTipo = "danger";
			e.printStackTrace();
		}

		// 5. Preparar el modelo para la siguiente pantalla
		HttpSession session = request.getSession();

		session.setAttribute("alertamensaje", mensaje.getTextoMensaje());
		session.setAttribute("alertatipo", mensaje.getTipoMensaje());

		// 6.. Redireccionar a la siguiente pantalla
		response.sendRedirect(request.getContextPath() + "/admin/listado");
	}



}
