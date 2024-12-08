<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Dominio.Cuenta"%>
<%@ page import="Dominio.Usuario"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Transferencia de Dinero</title>

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
    <div class="container mt-5">
        <h1 class="text-center mb-4">Transferencia de Dinero</h1>
        <form action="TransferenciasServlet" method="post">
            <div class="form-group">
                <label for="cbuOrigen">CBU de Origen:</label>
				<select name="cbuOrigen" id="cbuOrigen">
				<% if(request.getAttribute("listaCuentas") != null){ 
					ArrayList<Cuenta> listadoCuentas =  (ArrayList<Cuenta>)request.getAttribute("listaCuentas");
				for (Cuenta c : listadoCuentas){
					%>
				
				<option value="<%= c.getCbu() %>"><%= c.getCbu() %></option>
				
				<%}
				}%>
				</select>
		
		
		
            </div>
            <div class="form-group">
                <label for="cbuDestino">CBU de Destino:</label>
                <input type="text" class="form-control" id="cbuDestino" name="cbuDestino" required>
            </div>
            <div class="form-group">
                <label for="monto">Monto:</label>
                <input type="number" class="form-control" id="monto" name="monto" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block" <% ArrayList<Cuenta> listadoCuentas =  (ArrayList<Cuenta>)request.getAttribute("listaCuentas");
            if(listadoCuentas.isEmpty()) {%>disabled<%} %>>Realizar Transferencia</button>
        </form>
    </div>
</body>
</html>