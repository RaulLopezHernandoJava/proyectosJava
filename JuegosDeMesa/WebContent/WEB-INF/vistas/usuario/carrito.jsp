<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-bordered table-hover table-sm">
	<thead class="thead-dark">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Email</th>
			<th>Juego</th>
			<th>Precio</th>
			<th>Nº Juegos Pedidos</th>
			<th>Total Reserva</th>
			<th>
		</tr>
	</thead>
	<!-- Imprimimos los datos que nos interesen del carrito -->
	<tbody>
		<c:forEach items="${reservas}" var="reserva">
			<tr>
				<td>${reserva.id}</td>
				<td>${reserva.usuario.nombre}</td>
				<td>${reserva.usuario.apellidos}</td>
				<td>${reserva.usuario.email}</td>
				<td>${reserva.juego.nombre}</td>
				<td>${reserva.juego.precio}</td>
				<td>${reserva.cantidad}</td>
				<td>${reserva.total}</td>
				<td>
					<form class="form-inline" action="admin/pelicula" method="post">
<%-- 						<a class="btn btn-primary" href="admin/pelicula?id=${pelicula.id}">Editar</a> --%>

						<a class="btn btn-danger"
							onclick="return confirm('¿Estás seguro?')" href="admin/borrar?id=${pelicula.id}">Borrar Juego</a>

					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
