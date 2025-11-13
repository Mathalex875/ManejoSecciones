<%@ page import="models.DetalleCarro" %>
<%@ page import="models.ItemCarro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DetalleCarro detalleCarro = (DetalleCarro) session.getAttribute("carro");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras - Rum Rum</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
    <style>
        /* Estilos para impresión */
        @media print {
            .botones {
                display: none !important;
            }
            body {
                font-size: 12pt;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #000;
                padding: 8px;
                text-align: left;
            }
        }
    </style>
    <script>
        function generarPDF() {
            window.print();
        }
    </script>
</head>

<body>
<div class="container">
    <h1>Carrito de Compras</h1>
    <h2>Rum Rum - Tienda en Línea</h2>

    <%
        if (detalleCarro == null || detalleCarro.getItem().isEmpty()) {
    %>
    <p>Tu carrito está vacío.</p>
    <%
    } else {
    %>

    <table>
        <thead>
        <tr>
            <th>ID Producto</th>
            <th>Nombre</th>
            <th>Precio Unitario</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (ItemCarro item : detalleCarro.getItem()) {
        %>
        <tr>
            <td><%= item.getProducto().getId() %></td>
            <td><%= item.getProducto().getNombre() %></td>
            <td>$<%= String.format("%.2f", item.getProducto().getPrecio()) %></td>
            <td><%= item.getCantidad() %></td>
            <td>$<%= String.format("%.2f", item.getSubtotal()) %></td>
        </tr>
        <%
            }
        %>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4" style="text-align: right;"><strong>Subtotal:</strong></td>
            <td>$<%= String.format("%.2f", detalleCarro.getTotal()) %></td>
        </tr>
        <tr>
            <td colspan="4" style="text-align: right;"><strong>IVA (15%):</strong></td>
            <td>$<%= String.format("%.2f", detalleCarro.getIva()) %></td>
        </tr>
        <tr>
            <td colspan="4" style="text-align: right;"><strong>Total:</strong></td>
            <td><strong>$<%= String.format("%.2f", detalleCarro.getTotalConIva()) %></strong></td>
        </tr>
        </tfoot>
    </table>

    <div class="botones">
        <button onclick="generarPDF()" class="btn">Descargar o Imprimir PDF</button>
        <button class="btn" onclick="location.href='<%= request.getContextPath() %>/productos'">Seguir comprando</button>
        <button class="btn" onclick="location.href='<%= request.getContextPath() %>/index.html'">Volver al inicio</button>
    </div>

    <%
        }
    %>
</div>
</body>
</html>