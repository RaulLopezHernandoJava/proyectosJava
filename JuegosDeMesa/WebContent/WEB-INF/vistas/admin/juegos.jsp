<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="container mt-4">
	<!-- Zona de busqueda -->
	<div class="row ">
		<div class="col">
			<h4>Busqueda</h4>
			<form action="admin/listado" method="post">
				<div class="mb-3">
					<label for="autor" class="form-label">Autor</label> <input
						type="text" class="form-control" name="autor" id="autor"
						placeholder="Inserte un autor..">
				</div>
				<div class="mb-3">
					<label for="editorial" class="form-label">Editorial</label> <input
						type="text" class="form-control" name="editorial"
						placeholder="Inserte una editorial..." id="editorial">
				</div>
				<div class="mb-3">

					<label for="mecanica">Mecanicas</label> <select
						class="form-control" name="mecanica">
						<option value="">Elige una mecanica</option>
						<option value="Roll and Write">Roll and Write</option>
						<option value="Colocacion de Trabajadores">Colocacion de
							Trabajadores</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="precio" class="form-label">Precio</label> <input
						type="number" class="form-control" name="min"
						placeholder="Inserte el precio minimo..." id="min"> <input
						type="number" class="form-control" name="max"
						placeholder="Inserte el precio maximo..." id="max">
				</div>
				<button type="submit" class="btn btn-primary">Buscar</button>
			</form>
		</div>
	</div>

	<div class="row ">
		<div class="col d-flex justify-content-between flex-wrap">
			<!--  Zona de Formulario -->
			<c:forEach items="${juegos}" var="juego">
				<div class="card-img-top  tarjeta-juego" style="width: 20rem;">
				<img src="imgs-juegos/${juego.id}.jpg" class="card-img-top" alt="">
					
					<div class="card-body">
						<h5 class="card-title">${juego.nombre}</h5>
						<h6 class="card-subtitle mb-2 text-muted">Autor
							${juego.autor}</h6>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">Editorial ${juego.editorial}</li>
						<li class="list-group-item">Mecanica ${juego.mecanica.nombre}</li>
						<li class="list-group-item">Precio ${juego.precio}</li>
						<li class="list-group-item">Fecha Publicacion
							${juego.fechaPublicacion}</li>
					</ul>

					<c:if test="${sessionScope.email == 'raullopezhernando@gmail.com'}">
						<div class="card-body">

							<form class="form-inline" action="admin/juego" method="post">
								<a class="btn btn-primary" href="admin/juego?id=${juego.id}">Editar</a>
								<a class="btn btn-danger"
									onclick="return confirm('¿Estás seguro?')"
									href="admin/borrar?id=${juego.id}">Borrar</a>
							</form>
						</div>
					</c:if>

					<c:if test="${sessionScope.email != 'raullopezhernando@gmail.com'}">
						<div class="card-body">

							<form class="form-inline" action="user/reservaServlet" method="post">
								<label for="copia">Numero Copias</label>
								 <select
									class="form-control" name="copia" id ="copia">
									<option value="1"selected>1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									
								
								</select>
								<input type="hidden" name="id" value="${juego.id}">
								<button type="submit" class="btn btn-primary">Añadir al Carrito</button>
						</form>
								 		
						</div>
					</c:if>

				</div>
			</c:forEach>
		</div>
	</div>




	<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>