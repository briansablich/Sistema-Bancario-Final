<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Error en la Transferencia</title>

</head>
<body>

    		<jsp:include page="navbarClientes.html"></jsp:include>
           <jsp:include page="ClienteNombreApellido.jsp"></jsp:include>
    
    <div class="container mt-5">
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Error en la Transferencia</h4>
            <p>Lo sentimos, no se pudo completar su transferencia debido a que no dispone de saldo.</p>
            <hr>
            <p class="mb-0">Por favor, intente de nuevo o cargue saldo</p>
        </div>
        <a href="clientePerfilServlet" class="btn btn-secondary">Volver al Inicio</a>
    </div>
</body>
</html>
