<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="Dominio.Cliente"%>
<%@ page import="Dominio.Provincia"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
		<link rel="stylesheet" type="text/css" href="css/navbar.css"/>
	</head>
	<body>

		<h1>Portal Administradores</h1>

		<jsp:include page="navbarAdministradores.html"></jsp:include>

		<h2>Administracion de Clientes</h2>
		
		<h3>Crear cliente</h3>
		
		<form method="get" action="crearModificarClienteServlet">
	
			<% Cliente cAux = (Cliente)request.getAttribute("cliente"); %>
		
			<div class="form-container">
           		<div class="form-group">    
		
				<label>DNI</label>
				<input type="text" name="dni" id="dni" <% if (cAux != null) { %> value="<%= cAux.getDni() %>" <% } %> >
				<br>
				<label>CUIL</label>
				<input type="text" name="cuil" id="cuil" <% if (cAux != null) { %> value="<%= cAux.getCuil() %>" <% } %>>
				<br>
				<label>Nombre</label>
				<input type="text" name="nombre" id="nombre" <% if (cAux != null) { %> value="<%= cAux.getNombre() %>" <% } %>>
				<br>
				<label>Apellido</label>
				<input type="text" name="apellido" id="apellido" <% if (cAux != null) { %> value="<%= cAux.getApellido() %>" <% } %>>
				<br>
				<label>Sexo</label>
				<input type="text" name="sexo" id="sexo" <% if (cAux != null) { %> value="<%= cAux.getSexo() %>" <% } %>>
				<br>
				<label>Nacionalidad</label>
				<input type="text" name="nacionalidad" id="nacionalidad" <% if (cAux != null) { %> value="<%= cAux.getNacionalidad() %>" <% } %>>
				<br>
				<label>Fecha Nacimiento</label>
				<input type="text" name="fechaNacimiento" id="fechaNacimiento" <% if (cAux != null) { %> value="<%= cAux.getFechaNacimiento().toString() %>" <% } %>>
				<br>
				<label>Direccion</label>
				<input type="text" name="direccion" id="direccion" <% if (cAux != null) { %> value="<%= cAux.getDireccion() %>" <% } %>>
				<br>
				<label>Localidad</label>
				<input type="text" name="localidad" id="localidad" <% if (cAux != null) { %> value="<%= cAux.getLocalidad() %>" <% } %>>
				<br>	
				<label>Provincia</label>
				<select name="provincia" id="provincia">
					<% if(request.getAttribute("listadoProvincias") != null){ 
						List<Provincia> listadoProvincias =  (List<Provincia>)request.getAttribute("listadoProvincias");
						for (Provincia p : listadoProvincias){
					%>	
					<option value="<%= p.getId_provincia()%>"><%= p.toString() %></option>
			
					<%}
					}%>
				</select>

		
		<!-- 
		<input type="text" name="provincia" id="provincia" <% if (cAux != null) { %> value="<%= cAux.getProvincia() %>" <% } %>>
		 -->
				<br>
				<label>E-mail</label>
				<input type="text" name="email" id="email" <% if (cAux != null) { %> value="<%= cAux.getCorreoElectronico() %>" <% } %>>
				<br>
				<label>Telefono primario</label>
				<input type="text" name="telefonoPrimario" id="telefonoPrimario" <% if (cAux != null && !(cAux.getTelefonos()).isEmpty()) { %> value="<%= (cAux.getTelefonos()).get(0) %>" <% } %>>
				<br>
				<label>Telefono secundario</label>
				<input type="text" name="telefonoSecundario" id="telefonoSecundario" <% if (cAux != null && !(cAux.getTelefonos()).isEmpty()) { %> value="<%= (cAux.getTelefonos()).get(1) %>" <% } %>>
				<br>

				<% if(request.getAttribute("cliente") != null){ %>
					<button type="submit" class="btn btn-primary" name="crearModificarCliente" id="crearModificarCliente" value="ModificarCliente" >Guardar</button>
					<input type="hidden" name="idModificar" value="<%= cAux.getId() %>" />
				<%} else { %>
					<button type="submit" class="btn btn-primary" name="crearModificarCliente" id="crearModificarCliente" value="CrearCliente">Guardar</button>
				<%} %>
				<br>
				<a class="btn btn-primary" href="adminClientesServlet">Cancelar</a>
	    </div>
		</div>
	
	</form>


</body>
</html>

