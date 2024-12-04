<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Dominio.Cuenta"%>
<%@ page import="Dominio.Usuario"%>
<%@ page import="Dao.CuentaDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
		<link rel="stylesheet" type="text/css" href="css/navbar.css"/>
<style>
    .detalle-cuenta {
        background-color: #f9f9f9;
        padding: 15px;
        margin-bottom: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }
    .detalle-cuenta strong {
        display: block;
        margin-bottom: 5px;
    }
</style>

	</head>
	<body>
	<% 
         	Usuario usuario = (Usuario)session.getAttribute("usuario");
         	if(usuario == null  || !usuario.getAcceso().equals("Cliente")) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");   
				requestDispatcher.forward(request, response);
				return;
         	}
         %>
		<jsp:include page="navbarClientes.html"></jsp:include>
        <jsp:include page="ClienteNombreApellido.jsp"></jsp:include>
	
	
	<% if(request.getAttribute("mensajePrestamo") != null){
	%>
	<h2 style="color:green;"> <%= request.getAttribute("mensajePrestamo") %> </h2>
	<% }%>
	
	
    	<div class="container">
        	<div class="header">
            	<h1>Bienvenido</h1>
        	</div>
        
        
        	<div class="info-section">
            	<h2>Datos del Cliente</h2>
             	<p><strong>Email:</strong> <%= session.getAttribute("perfilClienteEmail") %></p>
            	<p><strong>Teléfono:</strong><%= session.getAttribute("perfilClienteTelefono") %></p>
        	  
        	
        	</div>
        
        	<div class="info-section">
            	<h2>Detalles de la Cuenta</h2>
            	
                <p><strong>CUENTAS:</strong> 
               <% if(request.getAttribute("listadoCuentas") != null){ 
					List<Cuenta> listadoCuentas =  (List<Cuenta>)request.getAttribute("listadoCuentas");
					for (Cuenta c : listadoCuentas){
				%>
                
  				<div class="detalle-cuenta">    
	                <p><strong>Numero de cuenta: </strong><%= c.getNumeroCuenta() %> </p>
	                <p> <strong>Cbu: </strong><%= c.getCbu() %> </p>
	                <p><strong>Saldo:</strong> $ <%= c.getSaldo() %> </p>
	                <p><strong>Tipo de cuenta:</strong> $ <%= c.getTipoCuenta().getTipoCuenta() %> </p>
                </div>
  					<%}
				}%>
        	</div>


	        <div class="info-section">
	        	<% if(request.getAttribute("listadoCuentas") != null){ %>
	            <h2>Seleccionar Cuenta para Ver Movimientos</h2>
	            <form action="MovimientosServlet" method="get">
	                <label for="cuenta">Seleccionar Cuenta:</label>
	                <select name="cuenta_seleccionada" id="cuenta">
	                	<%
	                	List<Cuenta> listadoCuentas =  (List<Cuenta>)request.getAttribute("listadoCuentas");
	                	for (Cuenta c : listadoCuentas){%>
	                    	<option value="<%= c.getIdCuenta()  %>"><%= c.getCbu()%> " - " <%= c.getTipoCuenta().getTipoCuenta()%></option>
						<%} %>
	                </select>
	                <input type="submit" value="Ver Movimientos">
	            </form>
	            <% }else{ %>
	            <h2>Aun no posee Cuentas para realizar Movimientos</h2>
	            <%} %>
	        </div>
    	</div>
    	
    	<div class="container">
    		<form action="ClientePrestamoServlet" method="get">
				<input type="submit" value="Solicitar Préstamo!" id="botonCrearPrestamo" name="botonCrearPrestamo">
			</form>
    	</div>
    	
	</body>
</html>