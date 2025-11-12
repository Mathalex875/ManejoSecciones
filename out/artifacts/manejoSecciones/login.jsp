<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/11/2025
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Formulario</title>
    <link rel="stylesheet" href="css/login.css">
    <script>
        function validarFormulario() {
            const usuario = document.getElementById("user").value.trim();
            const clave = document.getElementById("password").value.trim();
            let mensaje = "";

            if (usuario === "") {
                mensaje = "Por favor, ingrese su nombre de usuario.";
            } else if (clave === "") {
                mensaje = "Por favor, ingrese su contraseña.";
            }

            if (mensaje !== "") {
                document.getElementById("error").innerText = mensaje;
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1>Formulario de Inicio de Sesión</h1>
<div class="container">
    <form method="post" action="/login" onsubmit="return validarFormulario();">
        <div class="campo">
            <label for="user">Usuario :</label>
            <input type="text" id="user" name="username" placeholder="Ingrese su usuario">
        </div>
        <div class="campo">
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" placeholder="Ingrese su contraseña">
        </div>
        <div class="campo">
            <input type="submit" class="btn" value="Entrar">
        </div>
        <div id="error" class="error"></div>
    </form>
    <button class="btn volver" onclick="location.href='index.html'">Volver al Inicio</button>
</div>
</body>
</html>
