/*
 * Nombre: Alexis González
 * Fecha: 11/11/2025
 * Descripción: Servlet encargado de gestionar el inicio de sesión (login) de usuarios.
 * Verifica las credenciales ingresadas y mantiene la sesión activa del usuario.
 * Si el login es exitoso, muestra una pantalla personalizada de bienvenida con un contador
 * que indica cuántas veces ha iniciado sesión. Si las credenciales son incorrectas,
 * se muestra un mensaje de error.
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

// Mapeo del servlet: responde a las rutas /login y /login.html
@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    // Credenciales predefinidas para autenticación básica
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";

    /*
     * Método doGet: Se ejecuta al acceder mediante el navegador (GET).
     * Si el usuario ya inició sesión, muestra la página de bienvenida.
     * Si no hay sesión, redirige al formulario Login.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Servicio de autenticación que verifica sesión activa
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        // Si hay sesión activa, mostrar pantalla personalizada
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");

            // Recuperar o crear la sesión
            HttpSession session = req.getSession(false);
            Integer contador = (Integer) session.getAttribute("contadorLogin");

            if (contador == null) {
                contador = 1; // Primera vez que inicia sesión
                session.setAttribute("contadorLogin", contador);
            }

            // Diseño de la página HTML de bienvenida
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='es'>");
                out.println("<head>");
                out.println("    <meta charset='UTF-8'>");
                out.println("    <title>Bienvenido " + usernameOptional.get() + "</title>");
                out.println("    <style>");
                out.println("        body {");
                out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
                out.println("            background: linear-gradient(120deg, #89f7fe, #66a6ff);");
                out.println("            margin: 0;");
                out.println("            height: 100vh;");
                out.println("            display: flex;");
                out.println("            justify-content: center;");
                out.println("            align-items: center;");
                out.println("        }");
                out.println("        .container {");
                out.println("            background-color: #fff;");
                out.println("            padding: 40px;");
                out.println("            border-radius: 15px;");
                out.println("            box-shadow: 0 4px 15px rgba(0,0,0,0.2);");
                out.println("            text-align: center;");
                out.println("            width: 400px;");
                out.println("        }");
                out.println("        h1 { color: #0078D7; }");
                out.println("        .contador { color: #2E8B57; font-weight: bold; margin: 15px 0; }");
                out.println("        a { display: inline-block; color: #fff; background-color: #0078D7;");
                out.println("            padding: 10px 20px; border-radius: 8px; text-decoration: none;");
                out.println("            margin: 10px; transition: background 0.3s ease; }");
                out.println("        a:hover { background-color: #005fa3; }");
                out.println("    </style>");
                out.println("</head>");
                out.println("<body>");
                out.println("    <div class='container'>");
                out.println("        <h1>¡Hola, " + usernameOptional.get() + "!</h1>");
                out.println("        <p>Has iniciado sesión con éxito.</p>");
                out.println("        <p class='contador'>Número de veces que has iniciado sesión: " + contador + "</p>");
                out.println("        <a href='" + req.getContextPath() + "/index.html'>Volver al inicio</a>");
                out.println("        <a href='" + req.getContextPath() + "/logout'>Cerrar sesión</a>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            }

        } else {
            // Si no hay sesión activa → redirigir al formulario de login
            getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }

    /*
     * Método doPost: Se ejecuta al enviar el formulario de login.
     * Valida las credenciales ingresadas y crea la sesión del usuario.
     * Si el login falla, muestra un mensaje de error.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Obtener los parámetros ingresados en el formulario
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Validar credenciales (ignorando espacios en blanco accidentales)
        if (USERNAME.equals(username.trim()) && PASSWORD.equals(password.trim())) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);

            // Contador de inicios de sesión
            Integer contador = (Integer) session.getAttribute("contadorLogin");
            if (contador == null) {
                contador = 1;
            } else {
                contador++;
            }
            session.setAttribute("contadorLogin", contador);

            // Redirigir al servlet /login para mostrar bienvenida
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Si las credenciales son incorrectas → mostrar mensaje de error con diseño
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='es'>");
                out.println("<head>");
                out.println("    <meta charset='UTF-8'>");
                out.println("    <title>Acceso Denegado</title>");
                out.println("    <style>");
                out.println("        body {");
                out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
                out.println("            background: linear-gradient(120deg, #ff9a9e, #fad0c4);");
                out.println("            display: flex;");
                out.println("            justify-content: center;");
                out.println("            align-items: center;");
                out.println("            height: 100vh;");
                out.println("            margin: 0;");
                out.println("        }");
                out.println("        .error-box {");
                out.println("            background-color: #fff;");
                out.println("            padding: 40px;");
                out.println("            border-radius: 15px;");
                out.println("            box-shadow: 0 4px 15px rgba(0,0,0,0.2);");
                out.println("            text-align: center;");
                out.println("            width: 400px;");
                out.println("        }");
                out.println("        h2 { color: #E63946; }");
                out.println("        a { color: #fff; background-color: #E63946;");
                out.println("            padding: 10px 20px; border-radius: 8px; text-decoration: none;");
                out.println("            display: inline-block; margin-top: 15px; transition: background 0.3s ease; }");
                out.println("        a:hover { background-color: #c72c41; }");
                out.println("    </style>");
                out.println("</head>");
                out.println("<body>");
                out.println("    <div class='error-box'>");
                out.println("        <h2>Credenciales incorrectas</h2>");
                out.println("        <p>Por favor, verifica tu usuario y contraseña.</p>");
                out.println("        <a href='" + req.getContextPath() + "/Login.jsp'>Intentar nuevamente</a>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
