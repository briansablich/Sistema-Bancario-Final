<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="Dominio.Cliente"%>
<%@ page import="Dominio.Provincia"%>
<%@ page import="Dominio.Localidad"%>
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
				<input type="text" name="dni" id="dni" <% if (cAux != null) { %> value="<%= cAux.getDni() %>" <% } %> required >
				<br>
				<label>CUIL</label>
				<input type="text" name="cuil" id="cuil" <% if (cAux != null) { %> value="<%= cAux.getCuil() %>" <% } %> required>
				<br>
				<label>Nombre</label>
				<input type="text" name="nombre" id="nombre" <% if (cAux != null) { %> value="<%= cAux.getNombre() %>" <% } %> required>
				<br>
				<label>Apellido</label>
				<input type="text" name="apellido" id="apellido" <% if (cAux != null) { %> value="<%= cAux.getApellido() %>" <% } %> required>
				<br>
				<label>Sexo</label>
					<select name="sexo" id="sexo">
						<option value="Masculino" <% if (cAux != null) { if (cAux.getSexo().equals(Cliente.SEXO.Masculino)) { %>selected <% } } else { %> selected <%} %>>Masculino</option>
						<option value="Femenino" <% if (cAux != null) { if (cAux.getSexo().equals(Cliente.SEXO.Femenino)) { %> selected <% } }%>>Femenino</option>
					</select>
				<!-- <input type="text" name="sexo" id="sexo" <% if (cAux != null) { %> value="<%= cAux.getSexo() %>" <% } %> required> -->
				<br>
				<label>Nacionalidad</label>
				<input type="text" name="nacionalidad" id="nacionalidad" <% if (cAux != null) { %> value="<%= cAux.getNacionalidad() %>" <% } %> required>
				<br>
				<label>Fecha Nacimiento</label>
				<input type="date" name="fechaNacimiento" id="fechaNacimiento" <% if (cAux != null) { %> value="<%= cAux.getFechaNacimiento().toString() %>" <% } %> required>
				<br>
				<label>Direccion</label>
				<input type="text" name="direccion" id="direccion" <% if (cAux != null) { %> value="<%= cAux.getDireccion() %>" <% } %> required>
				<br>
				
				<!-------------------  DESPLEGABLE DE PROVINCIA  -------------------->
				<label>Provincia</label>
				<select name="provincia" id="provincia">
					<% if(request.getAttribute("listadoProvincias") != null){ 
						List<Provincia> listadoProvincias =  (List<Provincia>)request.getAttribute("listadoProvincias");
						for (Provincia p : listadoProvincias){
					%>	
					<option value="<%= p.getId_provincia()%>"  <% if (cAux != null && cAux.getProvincia().getId_provincia()== p.getId_provincia()){ %> selected <%} %>><%= p.toString() %> </option>
			
					<%}
					}%>
				</select>
				<br>

				<label>Localidad</label>
					<select name="localidad" id="localidad">
						<% if(request.getAttribute("listadoLocalidades") != null){ 
						List<Localidad> listadoLocalidades =  (List<Localidad>)request.getAttribute("listadoLocalidades");
						for (Localidad l : listadoLocalidades){
					%>	
					<option value="<%= l.getId_localidad()%>"  <% if (cAux != null && cAux.getLocalidad().getId_localidad()== l.getId_localidad()){ %> selected <%} %>><%= l.toString() %> </option>
			
					<%}
					}%>
					</select>
				<!-- <input type="text" name="localidad" id="localidad" <% if (cAux != null) { %> value="<%= cAux.getLocalidad() %>" <% } %> required> -->

				<br>
				<label>E-mail</label>
				<input type="text" name="email" id="email" <% if (cAux != null) { %> value="<%= cAux.getCorreoElectronico() %>" <% } %> required>
				<br>
				<label>Telefono primario</label>
				<input type="text" name="telefonoPrimario" id="telefonoPrimario" <% if (cAux != null && !(cAux.getTelefonos()).isEmpty()) { %> value="<%= (cAux.getTelefonos()).get(0) %>" <% } %> required>
				<br>
				<label>Telefono secundario</label>
				<input type="text" name="telefonoSecundario" id="telefonoSecundario" <% if (cAux != null && !(cAux.getTelefonos()).isEmpty()) { %> value="<%= (cAux.getTelefonos()).get(1) %>" <% } %> required>
				<br>

				<% if(request.getAttribute("cliente") != null){ %>
					<button type="submit" class="btn btn-primary" name="crearModificarCliente" id="crearModificarCliente" value="ModificarCliente" >Modificar</button>
					<input type="hidden" name="idModificar" value="<%= cAux.getId() %>" />
				<%} else { %>
					<button type="submit" class="btn btn-primary" name="crearModificarCliente" id="crearModificarCliente" value="CrearCliente">Guardar</button>
				<%} %>
				<br>
				<a class="btn btn-primary" href="adminClientesServlet">Cancelar</a>
	    </div>
		</div>
	
	</form>

	<script>
			//SE CARGAN TODAS LAS LOCALIDADES
		<% List<Localidad> listadoLocalidades =  (List<Localidad>)request.getAttribute("listadoLocalidades");%>
			//SE AGREGA EL EVENTO CHANGE EN EL DESPLEGABLE DE PROVINCIA
		 // Convertir el listado de localidades a JSON para que JavaScript pueda usarlo
         const localidades = [
	        <% for (int i = 0; i < listadoLocalidades.size(); i++) { 
		            Localidad l = listadoLocalidades.get(i); %>
		        {
		            id_localidad: <%= l.getId_localidad() %>,
		            nombre_localidad: "<%= l.getNombreLocalidad() %>",
		            id_provincia: <%= l.getProvincia().getId_provincia() %>
		        }<%= (i < listadoLocalidades.size() - 1) ? "," : "" %>
		        <% } %>
	    	];
        console.log(localidades);

    // Agregar el evento al select de provincias
    document.getElementById("provincia").addEventListener("change", function () {
        const provinciaSeleccionada = this.value;

        // Filtrar localidades por la provincia seleccionada
        const localidadesFiltradas = localidades.filter(l => l.id_provincia == provinciaSeleccionada);

        // Limpiar el select de localidades
        const localidadesSelect = document.getElementById("localidad");
        localidadesSelect.innerHTML = "";

        // Rellenar el select de localidades
        localidadesFiltradas.forEach(localidad => {
            const option = document.createElement("option");
            option.value = localidad.id_localidad;
            option.textContent = localidad.nombre_localidad;
            localidadesSelect.appendChild(option);
        });
    });
    </script>

</body>
</html>

