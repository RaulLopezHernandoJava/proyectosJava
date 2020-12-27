<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<h1 class="col-12">Detalle y Modificacion Juego</h1>

	<div class="col-12">
		<form action="admin/guardar" method="post">
			<input type="hidden" name="id" value="${juego.id}" />

			<%-- <div class="form-group row">
				<label for="id" class="col-sm-2 col-form-label">Id</label>
				<div class="col-sm-10">
					<input type="number" class="form-control ${pelicula.errorId != null ? 'is-invalid' : '' }" id="id" name="id"
						value="${pelicula.id}" readonly>
					<div class="invalid-feedback">${pelicula.errorId}</div>
				</div>
			</div> --%>
			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${pelicula.errorNombre != null ? 'is-invalid' : '' }"
						id="nombre" name="nombre" placeholder="El nombre del juego es"
						value="${juego.nombre}">
					<div class="invalid-feedback">${juego.errorNombre}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="autor" class="col-sm-2 col-form-label">Autor</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${juego.errorAutor != null ? 'is-invalid' : '' }"
						id="autor" name="autor" placeholder="El autor del juego es"
						value="${juego.autor}">
					<div class="invalid-feedback">${juego.errorAutor}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="editorial" class="col-sm-2 col-form-label">Editorial</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${juego.errorEditorial != null ? 'is-invalid' : '' }"
						id="editorial" name="editorial"
						placeholder="La editorial del juego es" value="${juego.editorial}">
					<div class="invalid-feedback">${juego.errorEditorial}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="mecanica" class="col-sm-2 col-form-label">Mecanica</label>
				<div class="col-sm-10">
					<select class="custom-select" id="mecanica" name="mecanica">
						<c:forEach items="${mecanicas}" var="mecanica">
							<option value="${mecanica.id}"
								${juego.mecanica.id == mecanica.id ? 'selected' : '' }>${mecanica.nombre}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label for="imagen" class="col-sm-2 col-form-label">Imagen</label>
				<div class="col-sm-10">
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="customFileLangHTML" name="imagen"> <label
							class="custom-file-label" for="customFileLangHTML"
							data-browse="Elegir Imagen">Selecciona un archivo</label>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="fecha-publicacion" class="col-sm-2 col-form-label">Fecha
					de Publicacion</label>
				<div class="col-sm-10">
					<input type="date"
						class="form-control ${juego.errorFechaPublicacion != null ? 'is-invalid' : '' }"
						id="fecha-publicacion" name="fecha-publicacion"
						value="${juego.fechaPublicacion}">
					<div class="invalid-feedback">${juego.errorFechaPublicacion}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="precio" class="col-m-2 col-form-label">Precio</label>
				<div class="col-sm-10">
					<input type="number"
						class="form-control ${juego.errorPrecio != null ? 'is-invalid' : '' }"
						id="precio" name="precio" value="${juego.precio}">
					<div class="invalid-feedback">${juego.precio}</div>
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
