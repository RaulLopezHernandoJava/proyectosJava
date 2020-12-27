<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<h1 class="col-12">Direccion del Envio</h1>

	<div class="col-12">
		<form action="user/guardarDireccion" method="post">
			<input type="hidden" name="id" value="${direccion.id}" />

			<%-- <div class="form-group row">
				<label for="id" class="col-sm-2 col-form-label">Id</label>
				<div class="col-sm-10">
					<input type="number" class="form-control ${pelicula.errorId != null ? 'is-invalid' : '' }" id="id" name="id"
						value="${pelicula.id}" readonly>
					<div class="invalid-feedback">${pelicula.errorId}</div>
				</div>
			</div> --%>
			
			<!-- NOMBRE -->
			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorNombre != null ? 'is-invalid' : '' }"
						id="nombre" name="nombre" placeholder="Inserte su nombre ..."
						value="${direccion.nombre}">
					<div class="invalid-feedback">${direccion.errorNombre}</div>
				</div>
			</div>
			<!-- APELLIDOS -->
			<div class="form-group row">
				<label for="apellidos" class="col-sm-2 col-form-label">Apellidos</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorApellidos != null ? 'is-invalid' : '' }"
						id="apellidos" name="apellidos" placeholder="Inserte sus apellidos ... "
						value="${direccion.apellidos}">
					<div class="invalid-feedback">${direccion.errorApellidos}</div>
				</div>
			</div>
			
			<!-- COMUNIDAD AUTONOMA -->
			<div class="form-group row">
				<label for="comunidad" class="col-sm-2 col-form-label">Comunidad Autonoma</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorComunidadAutonoma != null ? 'is-invalid' : '' }"
						id="comunidad" name="comunidad" placeholder="Inserte su comunidad autonoma ... "
						value="${direccion.comunidadAutonoma.nombre}">
					<div class="invalid-feedback">${direccion.errorComunidadAutonoma}</div>
				</div>
			</div>
			
			<!-- PROVINCIA -->
			<div class="form-group row">
				<label for="provincia" clasS="col-sm-2 col-form-label">Provincia</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorProvincia != null ? 'is-invalid' : '' }"
						id="provincia" name="provincia" placeholder="Inserte su provincia ... "
						value="${direccion.provincia.nombre}">
					<div class="invalid-feedback">${direccion.errorProvincia}</div>
				</div>
			</div>
			
			
			<!-- CIUDAD -->
			<div class="form-group row">
				<label for="ciudad" class="col-sm-2 col-form-label">Ciudad</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorCiudad != null ? 'is-invalid' : '' }"
						id="ciudad" name="ciudad"
						placeholder="Inserte la ciudad ..." value="${direccion.ciudad}">
					<div class="invalid-feedback">${direccion.errorCiudad}</div>
				</div>
			</div>
			
			<!-- DIRECCION -->
			<div class="form-group row">
				<label for="direccion" class="col-sm-2 col-form-label">Direccion</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorDireccion != null ? 'is-invalid' : '' }"
						id="direccion" name="direccion"
						placeholder="Inserte su direccion ..."
						value="${direccion.direccion}">
					<div class="invalid-feedback">${direccion.errorDireccion}</div>
				</div>
			</div>
			
			<!-- CODIGO POSTAL -->
			<div class="form-group row">
				<label for="codigoPostal" class="col-sm-2 col-form-label">Codigo Postal</label>
				<div class="col-sm-10">
					<input type="number"
						class="form-control ${direccion.errorCodigoPostal != null ? 'is-invalid' : '' }"
						id="codigoPostal" name="codigoPostal"
						placeholder="Inserte el codigo postal"
						value="${direccion.codigoPostal}">
					<div class="invalid-feedback">${direccion.errorCodigoPostal}</div>
				</div>
			</div>
			
			<!--TELEFONO-->
			<div class="form-group row">
				<label for="telefono" class="col-sm-2 col-form-label">Telefono</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorTelefono != null ? 'is-invalid' : '' }"
						id="telefono" name="telefono"
						placeholder="Inserte su telefono ..."
						value="${direccion.telefono}">
					<div class="invalid-feedback">${direccion.errorTelefono}</div>
				</div>
			</div>
			
		    <!--EMAIL-->
			<div class="form-group row">
				<label for="email" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${direccion.errorEmail != null ? 'is-invalid' : '' }"
						id="email" name="email"
						placeholder="Inserte su email ..."
						value="${direccion.email}">
					<div class="invalid-feedback">${direccion.errorEmail}</div>
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