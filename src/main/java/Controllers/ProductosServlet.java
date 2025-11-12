package Controllers;
/*
Descripción: Esta clase se encarga de mostrar los productos disponibles en formato HTML.
Si el usuario está autenticado, también muestra los precios de los productos.
Si no está autenticado, oculta los precios.
Autor: Dilan Salazar
Fecha: 2025/11/11
*/
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.LoginService;
import services.LoginServiceSessionImpl;
import services.ProductoService;
import services.ProductoServiceImpl;
import models.Producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos", "/productos.html", "/producto.json" })
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Instancia del servicio que maneja la lógica de productos
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar(); // Obtiene la lista de productos

        // Servicio para verificar si el usuario está autenticado (sesión activa)
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        // Configura el tipo de contenido HTML
        resp.setContentType("text/html;charset=UTF-8");

        // Generación dinámica del contenido HTML
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Productos</title>");
            // Estilos CSS modernos
            out.println("<style>");
            out.println(":root {");
            out.println("  --primary: #4361ee;");
            out.println("  --primary-dark: #3a56d4;");
            out.println("  --secondary: #7209b7;");
            out.println("  --success: #4cc9f0;");
            out.println("  --light: #f8f9fa;");
            out.println("  --dark: #212529;");
            out.println("  --gray: #6c757d;");
            out.println("  --border: #dee2e6;");
            out.println("  --shadow: 0 4px 6px rgba(0, 0, 0, 0.1);");
            out.println("  --shadow-hover: 0 8px 15px rgba(0, 0, 0, 0.15);");
            out.println("}");
            out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
            out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; padding: 20px; color: var(--dark); }");
            out.println(".container { max-width: 1200px; margin: 0 auto; background: rgba(255, 255, 255, 0.95); backdrop-filter: blur(10px); border-radius: 20px; padding: 40px; box-shadow: var(--shadow); border: 1px solid rgba(255, 255, 255, 0.2); }");
            out.println(".header { text-align: center; margin-bottom: 40px; padding-bottom: 20px; border-bottom: 2px solid var(--border); }");
            out.println("h1 { color: var(--primary); font-size: 2.5rem; margin-bottom: 10px; background: linear-gradient(135deg, var(--primary), var(--secondary)); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }");
            out.println(".subtitle { color: var(--gray); font-size: 1.1rem; margin-bottom: 20px; }");
            out.println(".welcome { background: linear-gradient(135deg, var(--success), var(--primary)); color: white; padding: 15px 25px; border-radius: 50px; display: inline-block; margin: 15px 0; font-weight: 600; box-shadow: var(--shadow); animation: pulse 2s infinite; }");
            out.println("@keyframes pulse { 0% { transform: scale(1); } 50% { transform: scale(1.05); } 100% { transform: scale(1); } }");
            out.println(".table-container { overflow-x: auto; border-radius: 15px; box-shadow: var(--shadow); margin: 30px 0; }");
            out.println("table { width: 100%; border-collapse: collapse; background: white; border-radius: 15px; overflow: hidden; }");
            out.println("th { background: linear-gradient(135deg, var(--primary), var(--secondary)); color: white; padding: 18px 15px; text-align: center; font-weight: 600; font-size: 1rem; text-transform: uppercase; letter-spacing: 0.5px; }");
            out.println("td { padding: 16px 15px; text-align: center; border-bottom: 1px solid var(--border); transition: all 0.3s ease; }");
            out.println("tr { transition: all 0.3s ease; }");
            out.println("tr:hover { background-color: rgba(67, 97, 238, 0.05); transform: translateY(-2px); box-shadow: var(--shadow-hover); }");
            out.println("tr:nth-child(even) { background-color: rgba(0, 0, 0, 0.02); }");
            out.println("tr:nth-child(even):hover { background-color: rgba(67, 97, 238, 0.08); }");
            out.println(".btn { background: linear-gradient(135deg, var(--primary), var(--primary-dark)); color: white; border: none; padding: 12px 28px; border-radius: 50px; cursor: pointer; font-size: 1rem; font-weight: 600; transition: all 0.3s ease; text-decoration: none; display: inline-block; box-shadow: var(--shadow); }");
            out.println(".btn:hover { transform: translateY(-3px); box-shadow: var(--shadow-hover); background: linear-gradient(135deg, var(--primary-dark), var(--secondary)); }");
            out.println(".btn-secondary { background: linear-gradient(135deg, var(--gray), #5a6268); }");
            out.println(".btn-secondary:hover { background: linear-gradient(135deg, #5a6268, #495057); }");
            out.println(".action-btn { background: linear-gradient(135deg, var(--success), #00b4d8); color: white; border: none; padding: 8px 16px; border-radius: 25px; cursor: pointer; font-size: 0.9rem; font-weight: 500; transition: all 0.3s ease; text-decoration: none; display: inline-block; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
            out.println(".action-btn:hover { transform: translateY(-2px); box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); background: linear-gradient(135deg, #00b4d8, #0096c7); }");
            out.println(".price { color: var(--primary); font-weight: 700; font-size: 1.1rem; }");
            out.println(".footer { text-align: center; margin-top: 40px; padding-top: 20px; border-top: 2px solid var(--border); }");
            out.println(".stats { display: flex; justify-content: center; gap: 30px; margin: 20px 0; flex-wrap: wrap; }");
            out.println(".stat-item { background: var(--light); padding: 15px 25px; border-radius: 15px; box-shadow: var(--shadow); }");
            out.println(".stat-number { font-size: 1.5rem; font-weight: 700; color: var(--primary); }");
            out.println(".stat-label { font-size: 0.9rem; color: var(--gray); }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='header'>");
            out.println("<h1>🛍️ Listado de Productos</h1>");
            out.println("<p class='subtitle'>Descubre nuestra amplia selección de productos de calidad</p>");

            // Si el usuario está logueado, muestra un mensaje de bienvenida
            if (usernameOptional.isPresent()) {
                out.println("<div class='welcome'>¡Bienvenido de nuevo, " + usernameOptional.get() + "! 👋</div>");
            }
            out.println("</div>");

            // Estadísticas rápidas
            out.println("<div class='stats'>");
            out.println("<div class='stat-item'><div class='stat-number'>" + productos.size() + "</div><div class='stat-label'>Productos Totales</div></div>");
            if (usernameOptional.isPresent()) {
                out.println("<div class='stat-item'><div class='stat-number'>" + productos.stream().mapToDouble(Producto::getPrecio).average().orElse(0) + "</div><div class='stat-label'>Precio Promedio</div></div>");
            }
            out.println("</div>");

            // Tabla de productos
            out.println("<div class='table-container'>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Tipo</th>");
            // Solo muestra el precio si el usuario está autenticado
            if (usernameOptional.isPresent()) {
                out.println("<th>Precio</th>");
                out.println("<th>Acciones</th>");
            }
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Itera sobre los productos y los muestra en la tabla
            productos.forEach(producto -> {
                out.println("<tr>");
                out.println("<td><strong>" + producto.getId() + "</strong></td>");
                out.println("<td>" + producto.getNombre() + "</td>");
                out.println("<td>" + producto.getTipo() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("<td class='price'>$" + String.format("%.2f", producto.getPrecio()) + "</td>");
                    out.println("<td><a class='action-btn' href=\"" + req.getContextPath() + "/agregar-carro?id=" + producto.getId() + "\">🛒 Agregar</a></td>");
                }
                out.println("</tr>");
            });

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            // Botones de acción
            out.println("<div class='footer'>");
            out.println("<button class='btn' onclick=\"location.href='" + req.getContextPath() + "/index.html'\">🏠 Volver al Inicio</button>");
            if (usernameOptional.isPresent()) {
                out.println("<button class='btn btn-secondary' onclick=\"location.href='" + req.getContextPath() + "/carro'\">🛒 Ver Carrito</button>");
            }
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}