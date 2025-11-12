/*
 * Nombre: Alexis González
 * Fecha: 11/11/2025
 * Descripción: Servlet encargado de gestionar el cierre de sesión (logout) del usuario.
 * Verifica si existe una sesión activa y la invalida, eliminando los datos almacenados.
 * Luego muestra un mensaje de confirmación visual y redirige al usuario a la página principal (index.html).
 */

package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.LoginService;
import services.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

// Mapeo del servlet: responde a la ruta /logout
@WebServlet({"/logout"})
public class LogoutServlet extends HttpServlet {

    /*
     * Método doGet: se ejecuta cuando el usuario accede a la URL /logout.
     * Si hay un usuario con sesión activa, se invalida la sesión y se muestra
     * un mensaje visual de cierre exitoso antes de redirigir al inicio.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Crear instancia del servicio de autenticación
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = Optional.empty();

        try {
            username = auth.getUsername(req); // intenta obtener usuario autenticado
        } catch (Exception e) {
            System.err.println("Error verificando sesión: " + e.getMessage());
        }

        // 2. Si existe sesión activa, se invalida
        if (username.isPresent()) {
            HttpSession session = req.getSession(false); // no crea nueva sesión
            if (session != null) {
                session.invalidate(); // destruye la sesión actual
                System.out.println("Sesión cerrada para el usuario: " + username.get());
            }
        }

        // 3. Mostrar página visual de cierre de sesión y redirigir al inicio
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Sesión Cerrada</title>");
            out.println("    <meta http-equiv='refresh' content='3;url=" + req.getContextPath() + "/index.html'>");
            out.println("    <style>");
            out.println("        body {");
            out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
            out.println("            background: linear-gradient(135deg, #74ebd5, #ACB6E5);");
            out.println("            display: flex;");
            out.println("            justify-content: center;");
            out.println("            align-items: center;");
            out.println("            height: 100vh;");
            out.println("            margin: 0;");
            out.println("        }");
            out.println("        .logout-box {");
            out.println("            background-color: #ffffff;");
            out.println("            padding: 40px;");
            out.println("            border-radius: 15px;");
            out.println("            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);");
            out.println("            text-align: center;");
            out.println("            width: 400px;");
            out.println("        }");
            out.println("        h2 { color: #0078D7; }");
            out.println("        p { color: #333; margin-top: 10px; }");
            out.println("        .redirect { color: #555; font-size: 14px; margin-top: 15px; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='logout-box'>");
            out.println("        <h2>¡Sesión cerrada con éxito!</h2>");
            out.println("        <p>Gracias por visitar nuestra aplicación.</p>");
            out.println("        <p class='redirect'>Serás redirigido al inicio en unos segundos...</p>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
