<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-bordered table-hover table-sm">
	<thead class="thead-dark">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Rol</th>
			<th>Edad</th>
			<th>Fecha Registro</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<th>${usuario.id}</th>
				<td>${usuario.nombre}</td>
				<td>${usuario.apellidos}</td>
				<td>${usuario.rol.nombre}</td>
				<td>${usuario.edad}</td>
				<td>${usuario.fechaRegistro}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
