<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-bordered table-hover table-sm mt-6">
	<thead class="thead-dark ">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Email</th>
			<th>Juego</th>
			<th>Precio</th>
			<th>Imagen Juego</th>
			<th>Nº Juegos Pedidos</th>
			<th>Total Reserva</th>
			<th>Opciones</th>
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
				<td> <img src="${reserva.juego.imagen}" alt="imagenJuego" width="200" height="200"> </td> 
				<td>${reserva.cantidad}</td>
				<td>${reserva.total}</td>
				<td>
					<form class="form-inline" action="admin/pelicula" method="post">
<%-- 						<a class="btn btn-primary" href="admin/pelicula?id=${pelicula.id}">Editar</a> --%>

						<a class="btn btn-danger"
							onclick="return confirm('¿Estás seguro de querer eliminar esta Reserva?')" href="user/BorrarReserva?id=${reserva.id}">Eliminar Reserva</a>

					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
