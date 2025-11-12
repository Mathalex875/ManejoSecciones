/*
 * Nombre: Alexis González
 * Fecha: 11/11/2025
 * Descripción: Servlet encargado de mostrar la lista de productos disponibles en el sistema.
 * Obtiene los datos desde la capa de servicios y genera una página HTML dinámica.
 * Si el usuario tiene sesión activa, se le muestra la columna de precios; de lo contrario,
 * los precios se ocultan. También muestra un mensaje de bienvenida al usuario logueado.
 */

package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import services.LoginService;
import services.LoginServiceSessionImpl;
import services.ProductoService;
import services.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

// Mapeo del servlet: responde a las rutas /productos y /productos.html
@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    /*
     * Método doGet: se ejecuta al acceder mediante el navegador.
     * Obtiene la lista de productos y genera una página HTML con una tabla.
     * Si el usuario ha iniciado sesión, se muestra también el precio de cada producto.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Obtener la lista de productos desde el servicio correspondiente
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        // 2. Verificar si el usuario tiene una sesión activa
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = Optional.empty();

        try {
            usernameOptional = auth.getUsername(req);
        } catch (Exception e) {
            System.err.println("Error obteniendo usuario: " + e.getMessage());
        }

        // 3. Configurar el tipo de contenido de la respuesta
        resp.setContentType("text/html;charset=UTF-8");

        // 4. Generar el contenido HTML dinámico
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Listado de Productos</title>");
            out.println("    <style>");
            out.println("        body {");
            out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
            out.println("            background: linear-gradient(135deg, #f0f4ff, #dfe9f3);");
            out.println("            margin: 0;");
            out.println("            padding: 0;");
            out.println("        }");
            out.println("        h1 {");
            out.println("            text-align: center;");
            out.println("            color: #2C3E50;");
            out.println("            margin-top: 30px;");
            out.println("        }");
            out.println("        .bienvenida {");
            out.println("            text-align: center;");
            out.println("            color: #0078D7;");
            out.println("            font-weight: bold;");
            out.println("            margin-bottom: 20px;");
            out.println("            font-size: 1.1em;");
            out.println("        }");
            out.println("        table {");
            out.println("            border-collapse: collapse;");
            out.println("            width: 85%;");
            out.println("            margin: 20px auto;");
            out.println("            background-color: #fff;");
            out.println("            box-shadow: 0 4px 15px rgba(0,0,0,0.1);");
            out.println("            border-radius: 10px;");
            out.println("            overflow: hidden;");
            out.println("        }");
            out.println("        th, td {");
            out.println("            border-bottom: 1px solid #ddd;");
            out.println("            padding: 12px;");
            out.println("            text-align: center;");
            out.println("        }");
            out.println("        th {");
            out.println("            background-color: #0078D7;");
            out.println("            color: white;");
            out.println("            text-transform: uppercase;");
            out.println("            letter-spacing: 0.03em;");
            out.println("        }");
            out.println("        tr:hover { background-color: #f5f5f5; }");
            out.println("        .boton-volver {");
            out.println("            display: block;");
            out.println("            width: 220px;");
            out.println("            margin: 30px auto;");
            out.println("            text-align: center;");
            out.println("            background-color: #0078D7;");
            out.println("            color: white;");
            out.println("            padding: 12px;");
            out.println("            border-radius: 10px;");
            out.println("            text-decoration: none;");
            out.println("            font-weight: bold;");
            out.println("            transition: background-color 0.3s ease;");
            out.println("        }");
            out.println("        .boton-volver:hover { background-color: #005fa3; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h1>Listado de Productos</h1>");

            // 5. Mostrar mensaje de bienvenida si el usuario está logueado
            usernameOptional.ifPresent(username ->
                    out.println("    <div class='bienvenida'>Bienvenido, <strong>" + username + "</strong> 👋</div>")
            );

            // 6. Crear tabla de productos
            out.println("    <table>");
            out.println("        <tr>");
            out.println("            <th>ID</th>");
            out.println("            <th>Nombre</th>");
            out.println("            <th>Tipo</th>");

            // Mostrar la columna de precios solo si hay sesión activa
            if (usernameOptional.isPresent()) {
                out.println("            <th>Precio</th>");
            }

            out.println("        </tr>");

            // 7. Rellenar la tabla con los productos obtenidos del servicio
            for (Producto p : productos) {
                out.println("        <tr>");
                out.println("            <td>" + p.getIdProducto() + "</td>");
                out.println("            <td>" + p.getNombre() + "</td>");
                out.println("            <td>" + p.getTipo() + "</td>");

                if (usernameOptional.isPresent()) {
                    out.println("            <td>$" + p.getPrecio() + "</td>");
                }

                out.println("        </tr>");
            }

            out.println("    </table>");

            // 8. Botón para volver al inicio
            out.println("    <a href='" + req.getContextPath() + "/index.html' class='boton-volver'>Volver al inicio</a>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
