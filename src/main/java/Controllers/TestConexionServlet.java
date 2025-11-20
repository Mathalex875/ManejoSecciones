package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/testConexion")
public class TestConexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Prueba Directa de Conexión</h1>");

        try {
            // Intento directo sin clases externas
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("<p>✅ Driver cargado</p>");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/basededatosventas",
                    "root",
                    ""
            );

            out.println("<p style='color:green;'>✅ ¡CONEXIÓN EXITOSA!</p>");
            out.println("<p>Base de datos: " + conn.getCatalog() + "</p>");

            conn.close();

        } catch (ClassNotFoundException e) {
            out.println("<p style='color:red;'>❌ Driver no encontrado: " + e.getMessage() + "</p>");
            out.println("<p>Descarga mysql-connector-j-8.0.x.jar y colócalo en WEB-INF/lib/</p>");
        } catch (SQLException e) {
            out.println("<p style='color:red;'>❌ Error SQL: " + e.getMessage() + "</p>");
            out.println("<p>Código: " + e.getErrorCode() + "</p>");
            out.println("<p>Estado: " + e.getSQLState() + "</p>");
        } catch (Exception e) {
            out.println("<p style='color:red;'>❌ Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}