<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Dominio.ClienteSaldo"%>
    <%@ page import="Dominio.Cliente"%>
    <%@ page import="Dominio.Cuenta"%>
    <%@ page import="Dominio.Movimiento"%>
    <%@ page import="Negocio.ClienteNegocio"%>
    <%@ page import="Negocio.CuentaNegocio"%>
    <%@ page import="Dominio.Provincia"%>
    <%@ page import="Dominio.Usuario"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal bancario UTN</title>
<link rel="stylesheet" type="text/css"
		href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
		
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript" charset="utf8"
			src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$('#tablaInformes').DataTable();
			});
		</script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }
        .container {
            width: 60%;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            background-color: #f2f2f2;
            padding: 20px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        .navbar {
            overflow: hidden;
            background-color: #333;
            margin-bottom: 20px;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        .info-section {
            margin-bottom: 20px;
        }
        .info-section h2 {
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .info-section p {
            margin: 10px 0;
        }
        .info-section table {
            width: 100%;
            border-collapse: collapse;
        }
        .info-section table, .info-section th, .info-section td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .info-section th {
            background-color: #f2f2f2;
        }
        table {
		   width: 100%;
		   border: 1px solid #000;
		}
		th, td {
		   width: 15%;
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
                     .navbar .right {
            float: right; 
    </style>
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

<div class="navbar">
    <a href="PortalAdministradores.jsp">Inicio</a>
    <a href="adminClientesServlet">Clientes</a>
    <a href="adminCuentasServlet">Cuentas</a>
    <a href="adminPrestamosServlet">Prestamos</a>
    <a href="adminInformes.jsp">Informes</a>
    <a href="Login.jsp" class="right">Salir de sesion</a>
</div>
    
    <h2>Administracion de Informes</h2>
    
    <% ClienteNegocio cNeg = new ClienteNegocio();
    	List<Cliente> listaClientes = cNeg.ListarConEstadoTrue();
    	CuentaNegocio cuentaNeg = new CuentaNegocio();
    	List<Cuenta> listaCuentas = cuentaNeg.Listar();
    %>
    
    <div>
	    <form method="get" action="adminInformesServlet">
	    	<h3>Buscar Movimientos entre fechas:</h3>
	    	<label>Seleccionar cliente:</label>
		    <select name="cliente" id="cliente">
					<% if(listaClientes != null){ 
						for (Cliente c : listaClientes){
					%>	
					<option value="<%= c.getId()%>"><%=c.getDni() %> - <%= c.getApellido()%></option>
						<%}
					}%>
			</select>
		    <br>
		    <label>Seleccionar cuenta:</label>
		    <select name="cuentaSeleccionada" id="cuentaSeleccionada">

			</select>
			<br>
				<label>Fecha Inicio</label>
				<input type="date" name="fecha_inicio" id="fecha_inicio" required>
			<br>
				<label>Fecha Fin</label>
				<input type="date" name="fecha_fin" id="fecha_fin" required>
			<br>
		    <input type="submit" id="btnInforme1" name="btnInforme1" value="Ver">
		    <br>
		    
		    <a href="adminInformes.jsp">LIMPIAR</a>
		 </form>
    </div>
    
    <% List<Movimiento> listadoMovimientos = (List<Movimiento>)request.getAttribute("listadoMovimientos");
   		 if(listadoMovimientos != null){ %>
    <table id="tablaInformes">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>Fecha</th>
	                <th>Concepto</th>
	                <th>Importe</th>
	                <th>Tipo movimiento</th>
	                <th>Cuenta origen</th>
	                <th>Cuenta destino</th>
	            </tr>
	        </thead>
		<tbody>
		    	<% for(Movimiento mov : listadoMovimientos){ %>
			<tr>
				<td><%=mov.getId() %> </td>
				<td><%=mov.getFecha() %> </td>
				<td><%=mov.getConcepto() %> </td>
				<td><%=mov.getImporte() %> </td>
				<td><%=mov.getTipoMovimiento().getDescripcion() %> </td>    
				<td><%=mov.getId_cuenta_origen() %> </td>    
				<td><%=mov.getId_cuenta_destino() %> </td>    
		    </tr>
		    <% } %>
		</tbody>
	</table>
		   <%}%>
		   <!-- HASTA ACÁ TABLA PARA MOSTRAR INFORME -->
	
	<script>
	
			//SE AGREGA EL EVENTO CHANGE EN EL DESPLEGABLE DE CLIENTES
		 // Convertir el listado de CUENTAS a JSON para que JavaScript pueda usarlo
         const cuentas = [
	        <% for (int i = 0; i < listaCuentas.size(); i++) {
		            Cuenta c = listaCuentas.get(i); %>
		        {
		            idCuenta: <%= c.getIdCuenta() %>,
		            numeroCuenta: "<%= c.getNumeroCuenta()%>",
		            tipoCuenta: "<%= c.getTipoCuenta().getTipoCuenta() %>",
		            idCliente: <%= c.getCliente().getId() %>
		        }<%= (i < listaCuentas.size() - 1) ? "," : "" %>
		        <% } %>
	    	];

         // Función para cargar las cuentas según el cliente seleccionado
         function cargarCuentas(clienteSeleccionado) {
             // Filtrar cuentas por el cliente seleccionado
             const cuentasFiltradas = cuentas.filter(c => c.idCliente == clienteSeleccionado);

             // Limpiar el select de cuentas
             const cuentasSelect = document.getElementById("cuentaSeleccionada");
             cuentasSelect.innerHTML = ""; // Limpiar las opciones previas

             // Rellenar el select de cuentas
             cuentasFiltradas.forEach(cuentaSeleccionada => {
                 const option = document.createElement("option");
                 option.value = cuentaSeleccionada.idCuenta;
                 option.textContent = cuentaSeleccionada.numeroCuenta + " - " + cuentaSeleccionada.tipoCuenta;
                 cuentasSelect.appendChild(option);
             });
         }

         // Cargar cuentas al cambiar el cliente
         document.getElementById("cliente").addEventListener("change", function () {
             const clienteSeleccionado = this.value;
             cargarCuentas(clienteSeleccionado);
         });

         // Cargar cuentas por defecto al cargar la página, usando la provincia seleccionada
         window.addEventListener("load", function () {
             const clienteSeleccionado = document.getElementById("cliente").value;
             
             
             cargarCuentas(clienteSeleccionado); // Carga las cuentas del cliente seleccionado al inicio
         });
    </script>
</body>
</html>