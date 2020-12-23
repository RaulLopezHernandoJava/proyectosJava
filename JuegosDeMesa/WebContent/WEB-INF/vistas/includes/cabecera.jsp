<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<base href="${pageContext.request.contextPath}/" />

<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="https://kit.fontawesome.com/f7c18cd652.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet" href="css/estilos.css" />

<title>Tyny BoardGames</title>
</head>
<body>
	<nav id="menu-principal"
		class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
		<a class="navbar-brand" href="#">Tiny Boardgames</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto ">
				<c:if test="${sessionScope.rol == 'administrador'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Administrador - ${email} </a>

						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="admin/juego">Añadir
									Juego</a></li>
							<li><a class="dropdown-item" href="admin/listado">Listado
									de Juegos</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="logout">Salir</a></li>
						</ul></li>
				</c:if>
				
			</ul>
			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${sessionScope.rol == 'usuario'}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								Usuario - ${email} </a>

							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="user/datosUsuario">Datos
										Usuario</a></li>
								<li><a class="dropdown-item" href="user/datosDireccion">Direccion
										Envio</a></li>
								<li><a class="dropdown-item" href="#">Pedidos</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="logout">Salir</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="login">Login</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>

	<%-- <nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Inicio</a></li>
			<li class="breadcrumb-item"><a href="#">Segundo nivel</a></li>
			<li class="breadcrumb-item active" aria-current="page">Bootstrap</li>
		</ol>
	</nav>--%>
	<c:if test="${alertamensaje != null}">
		<div class="alert alert-${alertatipo} alert-dismissible fade show"
			role="alert">
			${alertamensaje}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<%
			session.removeAttribute("alertamensaje");
		session.removeAttribute("alertatipo");
		%>
	</c:if>

	<%-- ${pageContext.request.contextPath} --%>

	<main class="container">