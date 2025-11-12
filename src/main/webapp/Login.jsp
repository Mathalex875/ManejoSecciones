<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/11/2025
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio de Sesión</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            color: #333;
        }

        div {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
            color: #4a90e2;
            margin-bottom: 30px;
            font-size: 1.8em;
        }

        form div {
            margin-bottom: 20px;
            padding: 0;
            background: none;
            box-shadow: none;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #555;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 1em;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #4a90e2;
            outline: none;
        }

        input[type="submit"] {
            width: 100%;
            background-color: #5cb85c;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1.1em;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
<div>
    <h1>Inicio de Sesión</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div>
            <label for="username">Ingrese el usuario:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Ingrese contraseña:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="Iniciar Sesión">
        </div>
    </form>
</div>
</body>
</html>
