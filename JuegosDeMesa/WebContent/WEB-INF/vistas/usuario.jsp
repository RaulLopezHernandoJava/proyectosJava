<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<div class="col-12">
		<form action="UsuarioGuardarServlet" method="post">
			<input type="hidden" name="id" value="${usuario.id}" />

			<%-- <div class="form-group row">
				<label for="id" class="col-sm-2 col-form-label">Id</label>
				<div class="col-sm-10">
					<input type="number" class="form-control ${pelicula.errorId != null ? 'is-invalid' : '' }" id="id" name="id"
						value="${pelicula.id}" readonly>
					<div class="invalid-feedback">${pelicula.errorId}</div>
				</div>
			</div> --%>
			<h1 class="m-2">Perfil del Usuario </h1>
			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${pelicula.errorNombre != null ? 'is-invalid' : '' }"
						id="nombre" name="nombre" placeholder="Inserte su nombre" readonly
						value="${usuario.nombre} ">
					<div class="invalid-feedback">${usuario.errorNombre}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="apellidos" class="col-sm-2 col-form-label">Apellidos</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${usuario.errorApellidos != null ? 'is-invalid' : '' }"
						id="apellidos" name="apellidos"
						placeholder="Inserte sus apellidos" readonly value="${usuario.apellidos}">
					<div class="invalid-feedback">${usuario.errorApellidos}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="email" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${usuario.errorEmail != null ? 'is-invalid' : '' }"
						id="email" name="email" readonly placeholder="Inserte su email"
						value="${usuario.email}">
					<div class="invalid-feedback">${usuario.errorEmail}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="email" class="col-sm-2 col-form-label">Contraseña</label>
				<div class="col-sm-10">
					<input type="password"
						class="form-control ${usuario.errorPassword != null ? 'is-invalid' : '' }"
						id="password" name="password" readonly placeholder="Inserte su contraseña"
						value="${usuario.password}">
					<div class="invalid-feedback">${usuario.errorPassword}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="email" class="col-sm-2 col-form-label">Edad</label>
				<div class="col-sm-10">
					<input type="number"
						class="form-control ${usuario.errorEdad != null ? 'is-invalid' : '' }"
						id="edad" name="edad" readonly placeholder="Inserte su edad ( Opcional )"
						value="${usuario.edad}">
					<div class="invalid-feedback">${usuario.errorEdad}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="fecha-regsitro" class="col-sm-2 col-form-label">Fecha
					de Registro</label>
				<div class="col-sm-10">
					<input type="date"
						class="form-control ${juego.errorFechaRegistro != null ? 'is-invalid' : '' }"
						id="fecha-registro" name="fecha-registro" readonly
						value="${usuario.fechaRegistro}">
					<div class="invalid-feedback">${juego.errorFechaRegistro}</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-10">
					<a class="btn btn-primary" href="admin/listado">Pagina Principal</a>
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
