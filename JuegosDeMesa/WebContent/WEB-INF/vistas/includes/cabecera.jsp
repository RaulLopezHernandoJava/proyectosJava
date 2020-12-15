<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<base href="${pageContext.request.contextPath}/" />

<link rel="stylesheet" href="css/bootstrap.min.css" />
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
			<ul class="navbar-nav mr-auto">
				<c:if test="${sessionScope.email != null}">
					<li class="nav-item"><a class="nav-link" href="admin/listado">Listado
							de juegos</a></li>
					<li class="nav-item"><a class="nav-link" href="admin/juego">Añadir
							juego</a></li>
					<li class="nav-item"><a class="nav-link" href="admin/juego">Buscador Juegos</a></li>
				</c:if>
			</ul>
			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${sessionScope.email != null}">
						<li class="nav-item navbar-text">${email}</li>
						<li class="nav-item"><a class="nav-link" href="logout">Desconectar</a></li>
					</c:when>
					<c:otherwise >
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