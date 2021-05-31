<%@page import="co.edu.ufps.web.model.*"%>
<%@page import="co.edu.ufps.web.DAO.*"%>
<%@page import="java.util.List"%>
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
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.10/css/all.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../style.css">
<title>Registrar Votante</title>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #38C953">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"><h1>Registrar
						Votantes</h1> Votantes Management App </a>
			</div>
		</nav>
	</header>


	<hr>
	<div id="botones" class="container text-left">
		
		<a href="<%=request.getContextPath()%>/VotanteServlet?action=index" class="btn btn-success">Ir al
			menú</a> <a href="<%=request.getContextPath()%>/vistaVotantes/mostrar.jsp"
			class="btn btn-success">Listar Votantes</a>
	</div>

	<div class="container">
		<div class="card">
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/VotanteServlet?action=register" method="post">
					<div class="form-group">
						<label for="validation01">Id</label> <input type="number"
							class="form-control" placeholder="Id" name="id" >
					</div>
					<div class="form-group">
						<select>
      				 	<option>Seleccionar estamento</option> 
       					 <% for(Estamento e: new EstamentoDAO().list()){%>
          			 	<option>${e.getDescripcion()} </option>
       						 <% }%>
       					<input type="hidden" name="estamento" />
					</select>
					</div>
					<div class="form-group">
						<label for="validation01">Nombre</label> <input type="text"
							class="form-control" placeholder="Nombre" name="nombre">
					</div>
					<div class="form-group">
						<label for="validation01">Email</label> <input type="email"
							class="form-control" placeholder="Email" name="email">
					</div>
					<div class="form-group">
						<select>
      				 	<option>Tipo de documento</option> 
       					 <% for(TipoDocumento t: new TipoDocumentoDAO().list()){%>
          			 	<option>${t.getDescripcion()} </option>
       						 <% }%>
       					<input type="hidden" name="tipodocumento" />
					</select>
					</div>
					<div class="form-group">
						<label for="validation01">Documento</label> <input type="text"
							class="form-control" placeholder="Documento" name="documento">
					</div>
					<div class="form-group">
						<select>
      				 	<option>Seleccionar eleccion</option> 
       					 <% for(Eleccion e: new EleccionDAO().list()){%>
          			 	<option>${e.getNombre()} </option>
       						 <% }%>
       					<input type="hidden" name="eleccion" />
					</select>
					</div>
					
						<input type="submit" class="btn btn-success" value="Agregar"
							name="agregar">
					
				</form>
			</div>
		</div>
	</div>

	<footer>
        <div class="row">
            <div class="col-md-3">
                <ul id="ul-con" type="none">
                    <b>Contacto</b>
                    <li>Email: juansebastiansp@ufps.edu.co</li>
                    <li>Cel: +57 3219810616</li>
                </ul>
            </div>
        </div>
        <hr>
        <p>2021 © All Rights Reserved. Desarrollado por JUAN SEBASTIAN SANCHEZ PRADA </p>
    </footer>
</body>
</html>