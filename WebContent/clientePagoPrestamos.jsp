<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Prestamo"%>
<%@ page import="Dominio.Usuario"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
		<link rel="stylesheet" type="text/css" href="css/navbar.css"/>	
		<style>
		table {
		   width: 100%;
		   border: 1px solid #000;
		
		}
		th, td {
		   width: 25%;
		   text-align: center;
		   vertical-align: top;
		   border: 1px solid #000;
		   border-spacing: 0;
		   border-collapse: collapse;
		   background: #fff;
		   color: #000;
		}
		
		caption {
		   padding: 0.3em;
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
	    <div class="container">
	        <div class="header">
	            <h1>Préstamos</h1>
	        </div>
	
	        <div class="info-section">
	            <h2>Prestamos asociados a la cuenta</h2>
	            
	            <table>
	        <thead>
	            <tr>
	                <th>Fecha de solicitud</th>
	                <th>Importe solicitado</th>
	                <th>Importe a pagar</th>
	                <th>Cantidad de Cuotas</th>
	                <th>Monto Mensual</th>
	                <th>Estado</th>
	                
	            </tr>
	        </thead>
	        <tbody>
	            <% 
	            ArrayList<Prestamo> listadoPrestamos = (ArrayList<Prestamo>)request.getAttribute("listadoPrestamosPorCliente");
	            	if(listadoPrestamos != null) {
	            		for(Prestamo prestamo : listadoPrestamos) {
	            %>
	            		
	                <tr>
	                    <td><%= prestamo.getFechaAlta().toString() %></td>
	                    <td><%= prestamo.getImporteSolicitado() %></td>
	                    <td><%= prestamo.getImporteApagar() %></td>
	                    <td><%= prestamo.getCuotas() %></td>
	                    <td><%= prestamo.getMontoMensual() %></td>
	                    <td><%= prestamo.getEstado().name() %></td>
	                    
					    <td class="action-buttons" >
					    <div style="display:flex">
					        <form action="PortalPagosBancoServlet" method="get">
					        <%if(prestamo.getEstado().equals(Prestamo.ESTADO.Aprobado)){ %>
					        	<input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>" />
					            <input type="submit" name="btnPagarPrestamo" value="PAGAR" />
					            <%} %>
					        </form>
					    </div>
					    </td>
					</tr>	
				<% }
	            			
				} %>

	        </tbody>
	    </table>

	        </div>
	    </div>
	</body>
</html>