<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Dominio.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error en la aprobacion del prestamo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css"/>
	<link rel="stylesheet" type="text/css" href="css/portalAdministradores.css"/>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<% 
         	Usuario usuario = (Usuario)session.getAttribute("usuario");
         	if(usuario == null || !usuario.getAcceso().equals("Administrador")) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");   
				requestDispatcher.forward(request, response);
				return;
         	}
         %>
         
	<h1>Portal Administradores - Usuario: <%=usuario.toString()%></h1>

    		<jsp:include page="navbarAdministradores.html"></jsp:include>
    
    <div class="container mt-5">
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Error en la aprobacion del prestamo</h4>
            <p>Lo sentimos, no se pudo completar el prestamo debido a que la cuenta está inactivada.</p>
            <hr>
            <p class="mb-0">Por favor, active la cuenta o rechace el prestamo.</p>
        </div>

        <input class="btn btn-primary" type="button" value="Volver" onclick="history.back();">
    </div>
</body>
</html>
