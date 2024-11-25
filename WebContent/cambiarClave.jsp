<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Cliente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Cambio Clave</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            width: 300px;
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ddd;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #007bff;
            margin-bottom: 20px;
        }
        label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="password"],
        input[type="submit"],
        input[type="button"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        input[type="button"]:hover {
            background-color: #e0e0e0;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Cambio de clave</h2>
    <form action="crearModificarClienteServlet" method="get">
    <% Cliente cliente = (Cliente)request.getAttribute("cliente");%>
        <label for="username">User:</label>
        <input type="text" id="username" name="username" value="<%=cliente.getCorreoElectronico() %>" readonly /><br/>
        <label for="password">Nueva Password:</label>
        <input type="password" id="password" name="password" required/><br/>
        <input type="submit" name="btnClaveNueva" value="CAMBIAR CLAVE" />
    </form>
        <input type="button" value="Cancelar" onclick="history.back();">
</div>

</body>
</html>