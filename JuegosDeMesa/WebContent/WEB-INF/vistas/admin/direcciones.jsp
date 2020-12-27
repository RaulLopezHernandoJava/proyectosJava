<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-bordered table-hover table-sm">
	<thead class="thead-dark">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>CodigoPostal</th>
			<th>Ciudad</th>
			<th>Comunidad</th>
			<th>Provincia</th>
			<th>Telefono</th>
			<th>Email</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${direcciones}" var="direccion">
			<tr>
				<th>${direccion.id}</th>
				<td>${direccion.nombre}</td>
				<td>${direccion.apellidos}</td>
				<td>${direccion.codigoPostal}</td>
				<td>${direccion.ciudad}</td>
				<td>${direccion.comunidadAutonoma.nombre}</td>
				<td>${direccion.provincia.nombre}</td>
				<td>${direccion.telefono}</td>
				<td>${direccion.email}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>