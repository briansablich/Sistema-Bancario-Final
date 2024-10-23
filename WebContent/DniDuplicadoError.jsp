<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro Fallido</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <h1>Registro Fallido: DNI DUPLICADO</h1>
    <div class="alert alert-danger" role="alert">
        <c:if test="${not empty errorMensaje}">
            ${errorMensaje}
        </c:if>
    </div>
    <a href="adminClientes.jsp" class="btn btn-primary">Intentar de Nuevo</a>
</div>
</body>
</html>

