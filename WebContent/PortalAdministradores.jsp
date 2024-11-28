<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Usuario"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
		<link rel="stylesheet" type="text/css" href="css/navbar.css"/>
		<link rel="stylesheet" type="text/css" href="css/portalAdministradores.css"/>
	</head>
	<body>
	<%Usuario usuario = (Usuario)session.getAttribute("usuario");
         	if(usuario == null || !usuario.getAcceso().equals("Administrador")) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");   
				requestDispatcher.forward(request, response);
				return;
         	}
         %>
         
	<h1>Portal Administradores - Usuario: <%=usuario.toString()%></h1>

		<jsp:include page="navbarAdministradores.html"></jsp:include>
    
	</body>
</html>