package Controllers;

import services.LoginService;
import services.LoginServiceSessionImpl;
import services.ProductoService;
import services.ProductoServiceImpl;
import models.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos", "/productos.html"})
public class ProductosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            ProductoService service = new ProductoServiceImpl();
            List<Producto> productos = service.listar();

            LoginService auth = new LoginServiceSessionImpl();
            Optional<String> usernameOptional = auth.getUsername(req);

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Productos</title>");
            out.println("    <style>");
            out.println("        body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("        table { border-collapse: collapse; width: 80%; margin: 20px 0; }");
            out.println("        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
            out.println("        th { background-color: #4CAF50; color: white; }");
            out.println("        .welcome { color: blue; font-weight: bold; margin: 10px 0; }");
            out.println("        .error { color: red; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h1>🛍️ Lista de Productos</h1>");

            if (usernameOptional.isPresent()) {
                out.println("    <div class='welcome'>✅ Hola " + usernameOptional.get() + " - Bienvenido!</div>");
            } else {
                out.println("    <div class='error'>⚠️ Inicia sesión para ver precios y agregar al carrito</div>");
            }

            out.println("    <table>");
            out.println("        <tr>");
            out.println("            <th>ID</th>");
            out.println("            <th>Nombre</th>");
            out.println("            <th>Tipo</th>");
            if (usernameOptional.isPresent()) {
                out.println("            <th>Precio</th>");
                out.println("            <th>Acción</th>");
            }
            out.println("        </tr>");

            for (Producto p : productos) {
                out.println("        <tr>");
                out.println("            <td>" + p.getId() + "</td>");
                out.println("            <td>" + p.getNombre() + "</td>");
                out.println("            <td>" + p.getEstado() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("            <td>$" + p.getPrecio() + "</td>");
                    out.println("            <td>");
                    out.println("                <a href='" + req.getContextPath() + "/agregar-carro?id=" + p.getId() + "'>");
                    out.println("                    🛒 Agregar al carro");
                    out.println("                </a>");
                    out.println("            </td>");
                }
                out.println("        </tr>");
            }

            out.println("    </table>");
            out.println("    <br>");
            out.println("    <a href='" + req.getContextPath() + "/index.html'>🏠 Volver al inicio</a> | ");
            out.println("    <a href='" + req.getContextPath() + "/ver-carro'>🛒 Ver carrito</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>Error al cargar productos: " + e.getMessage() + "</p>");
            out.println("</body></html>");
            e.printStackTrace();
        }
    }
}