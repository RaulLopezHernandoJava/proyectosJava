<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<h1 class="col-12">Registro de Usuario</h1>

	<div class="col-12">
		<form action="admin/guardar" method="post">
			
			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${usuario.errorNombre != null ? 'is-invalid' : '' }"
						id="nombre" name="nombre" placeholder="Inserte su nombre">
					<div class="invalid-feedback">${usuario.errorNombre}</div>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="apellidos" class="col-sm-2 col-form-label">Apellidos</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${usuario.errorApellidos != null ? 'is-invalid' : '' }"
						id="apellidos" name="apellidos" placeholder="Inserte sus apellidos"
						value="${usuario.apellidos}">
					<div class="invalid-feedback">${usuario.apellidos}</div>
				</div>
			</div>
			
				<div class="form-group row">
				<label for="editorial" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${usuario.errorEmail != null ? 'is-invalid' : '' }"
						id="email" name="email" placeholder="Inserte su email "
						value="${usuario.email}">
					<div class="invalid-feedback">${usuario.errorEmail}</div>
				</div>
			</div>
			
			
			
			<div class="form-group row">
				<label for="imagen" class="col-sm-2 col-form-label">Edad</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${juego.errorImagen != null ? 'is-invalid' : '' }"
						id="imagen" name="imagen" placeholder="Inserta la url de la imagen del juego"
						value="${juego.imagen}">
					<div class="invalid-feedback">${juego.errorImagen}</div>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="fecha-publicacion" class="col-sm-2 col-form-label">Fecha Registro</label>
				<div class="col-sm-10">
					<input type="date"
						class="form-control ${juego.errorFechaPublicacion != null ? 'is-invalid' : '' }"
						id="fecha-publicacion" name="fecha-publicacion"
						value="${juego.fechaPublicacion}">
					<div class="invalid-feedback">${juego.errorFechaPublicacion}</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-primary">Aceptar</button>
					<a class="btn btn-danger" href="admin/listado">Cancelar</a>
				</div>
			</div>
		</form>

	</div>


</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
