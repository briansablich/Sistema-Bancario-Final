<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Cliente"%>
<%@ page import="Dominio.Usuario"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
		<link rel="stylesheet" type="text/css" href="css/navbar.css"/>
		<link rel="stylesheet" type="text/css" href="css/adminClientes.css"/>
	</head>
	<body>
	
         <% 
         	Usuario usuario = (Usuario)session.getAttribute("usuario");
         	if(usuario == null  || !usuario.getAcceso().equals("Administrador")) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");   
				requestDispatcher.forward(request, response);
				return;
         	}
         %>
		<h1>Portal Administradores - Usuario: <%=usuario.toString()%></h1>

		<jsp:include page="navbarAdministradores.html"></jsp:include>
    
		<h2>Administracion de Clientes</h2>
		
		<form action="crearModificarClienteServlet" method="get">
            <input type="submit" name="btnCrear" value="CREAR" />					         
        </form>
		
		<table>
	        <thead>
	            <tr>
	                <th>ID Cliente</th>
	                <th>DNI</th>
	                <th>CUIL</th>
	                <th>Nombre</th>
	                <th>Apellido</th>
	                <th>Sexo</th>
	                <th>Nacionalidad</th>
	                <th>Fecha Nacimiento</th>
	                <th>Direccion</th>
	                <th>Localidad</th>
	                <th>Provincia</th>
	                <th>E-mail</th>
	                <th>Tel. Primario</th>
	                <th>Tel. Secundario</th>
	                <th>Estado</th>
	            </tr>
	        </thead>
	        <tbody>
	            <% 
	            ArrayList<Cliente> listadoClientes = (ArrayList<Cliente>)request.getAttribute("listadoClientes");
	            	if(listadoClientes != null) {
	            		for(Cliente cliente : listadoClientes) {
	            %>
	                <tr>
	                    <td><%= cliente.getId()%></td>
	                    <td><%= cliente.getDni()%></td>
	                    <td><%= cliente.getCuil()%></td>
	                    <td><%= cliente.getNombre()%></td>
	                    <td><%= cliente.getApellido()%></td>
	                    <td><%= cliente.getSexo()%></td>
	                    <td><%= cliente.getNacionalidad()%></td>
	                    <td><%= cliente.getFechaNacimiento().toString()%></td>
	                    <td><%= cliente.getDireccion()%></td>
	                    <td><%= cliente.getLocalidad().getNombreLocalidad()%></td>
	                    <td><%= cliente.getProvincia().toString()%></td>
	                    <td><%= cliente.getCorreoElectronico()%></td>
	                    <td><% if (!(cliente.getTelefonos()).isEmpty() && cliente.getTelefonos() != null) { %> <%= (cliente.getTelefonos()).get(0) %> <% } %></td>
	                    <td><% if (!(cliente.getTelefonos()).isEmpty() && cliente.getTelefonos() != null) { %> <%= (cliente.getTelefonos()).get(1) %> <% } %></td>
	                    <td><%= cliente.getEstado()%></td>
    					<td class="action-buttons" >
    						<div style="display:flex">
      							<% if (cliente.getEstado() == Cliente.ESTADO.True ) {%>
							        <form action="eliminarClienteServlet" method="post">
							            <input type="hidden" name="clienteId" value="<%= cliente.getId() %>" />
							            <input type="submit" name="btnEliminar" value="ELIMINAR" />					         
							        </form>
								<%}  else {%>
						            <form action="activaClienteServlet" method="post">
							            <input type="hidden" name="clienteId" value="<%= cliente.getId() %>" />
							            <input type="submit" name="btnActivar" value="ACTIVAR" />
						        	</form>
								<%}%> 
						        <form action="crearModificarClienteServlet" method="get">
						        	<input type="hidden" name="clienteId" value="<%= cliente.getId() %>" />
						            <input type="submit" name="btnModificar" value="MODIFICAR" />
						        </form>
						        <form action="crearModificarClienteServlet" method="get">
						        	<input type="hidden" name="clienteId" value="<%= cliente.getId() %>" />
						            <input type="submit" name="btnCambiarClave" value="CAMBIAR CLAVE" />
						        </form>
							</div>
    					</td>
					</tr>
				<% }	
				} %>
	        </tbody>
	    </table>
	</body>
</html>