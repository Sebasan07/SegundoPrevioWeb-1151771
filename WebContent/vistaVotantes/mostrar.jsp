<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>Mostrar Candidatos</title>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #38C953">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"><h1>Listar
						Candidatos</h1> Candidatos Management App </a>
			</div>
		</nav>
	</header>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/vistaCandidatos/registro.jsp"
					class="btn btn-success">Registrar nuevo Candidato</a> <a
					href="<%=request.getContextPath()%>/CandidatoServlet?action=mostrar"
					class="btn btn-success">Mostrar listado de Candidatos</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<td>ID</td>
						<td>NOMBRE</td>
						<td>APELLIDO</td>
						<td>DOCUMENTO</td>
						<td>ELECCION</td>
						<td>NUMERO</td>
						<td>ACTION</td>

					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="candidato" items="${lista}">
						<tr>
							<td><c:out value="${candidato.id}" /></td>
							<td><c:out value="${candidato.documento}" /></td>
							<td><c:out value="${candidato.nombre}" /></td>
							<td><c:out value="${candidato.apellido}" /></td>
							<td><c:out value="${candidato.eleccion}" /></td>
							<td><c:out value="${candidato.numero}" /></td>
							<td><a
								href="<%=request.getContextPath()%>/CandidatoServlet?action=showedit&id=<c:out value="${candidato.id}" />">Editar</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
								href="CandidatoServlet?action=eliminar&id=<c:out value="${candidato.id}"/>">Eliminar</a>
							</td>

						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
