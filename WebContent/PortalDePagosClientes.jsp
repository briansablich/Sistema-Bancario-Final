<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Pago"%>
<%@ page import="Dominio.CuotasPrestamo"%>
<%@ page import="Negocio.CuentaNegocio"%>
<%@ page import="Dominio.Usuario"%>
<%@ page import="Dominio.Cuenta"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Pagos</title>
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
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
	    		<script type="text/javascript">
			$(document).ready(function() {
				$('#tablaCuotas').DataTable();
			});
		</script>
		

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
	            <h1>Pagos</h1>
	        </div>
	
	        <div class="info-section">
	            <h2>Pagos para el Prestamo: <%= request.getAttribute("idPrestamo") %> </h2>
	            <br>
				<form action="PortalPagosBancoServlet" method="get">
				<input type="hidden" name="idPrestamo" value="<%= request.getAttribute("idPrestamo")%>" />
	            <label>Seleccionar cuenta para pagar:</label>
	            <select name="cbuCuenta">
	            <% CuentaNegocio cuentaNegocio = new CuentaNegocio();
	            	ArrayList<Cuenta> listaCuentasCliente = cuentaNegocio.getListaCuentasPorCliente(usuario.getCliente().getId());
	            	for(Cuenta cuenta : listaCuentasCliente){
	            		if(cuenta.getEstado() == Cuenta.ESTADO.True){
	            	%>
	            		<option value="<%=cuenta.getCbu() %>" > <%=cuenta.getNumeroCuenta() %> - <%=cuenta.getTipoCuenta().getTipoCuenta() %></option>
	            	<%}
	            		}%>
	            	
	            </select>
	            <table id="tablaCuotas">
	        <thead>
	            <tr>
	                <th>Id Prestamo</th>
	                <th>Numero Cuota</th>
	                <th>Monto Cuota</th>
	                <th>Fecha Vencimiento</th>
	                <th>Fecha Pago</th>
	                <th>Estado</th>
	                <th>Accion</th>
	                
	            </tr>
	        </thead>
	        <tbody>
	            <% 
	            ArrayList<CuotasPrestamo> listaCuotasPorPrestamo = (ArrayList<CuotasPrestamo>)request.getAttribute("listaCuotasPorPrestamo");
	            	if(listaCuotasPorPrestamo != null) {
	            		for(CuotasPrestamo cuota : listaCuotasPorPrestamo) {
	            %>
	            		
	                <tr>
	                    <td><%= cuota.getId_prestamo() %></td>
	                    <td><%= cuota.getNumero_cuota()%></td>
	                    <td><%= cuota.getMonto_cuota()%></td>  
	                    <td><%= cuota.getFecha_vencimiento()%></td>
	                    <td> <%if(cuota.getFecha_pago()!=null){ %> <%= cuota.getFecha_pago()%> <%}else{ %>N/A<%} %> </td>
	                    <td><%= cuota.getEstado() %></td>
	                    
					    <td class="action-buttons" >
						    <div style="display:flex">
						    <%if(cuota.getEstado() != CuotasPrestamo.ESTADO.Pagada){ %>
						    	<input type="hidden" name="CuotaId" value="<%= cuota.getId_cuota()%>" />
					            <input type="submit" name="btnPagarCuota" value="Pagar Cuota" />
					            <%} %>
						    </div>
					    </td>
					</tr>	
				<% }
	            			
				} %>

	        </tbody>
	    </table>
	    <input type="hidden" name="listaCuotasPorPrestamo" value="<%= listaCuotasPorPrestamo%>" />
		</form>
	        </div>
	    </div>
	</body>
</html>