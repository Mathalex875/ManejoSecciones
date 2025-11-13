package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.LoginService;
import services.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "123456";
    private static int contadorVisitas = 0; // Contador global de visitas

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean visitado = (Boolean) session.getAttribute("visitado");

        // Incrementa el contador solo la primera vez que el usuario entra en la sesión
        if (visitado == null || !visitado) {
            contadorVisitas++;
            session.setAttribute("visitado", true);
        }

        // Comprueba si el usuario está autenticado
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        // Configura el tipo de contenido HTML
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            // Si el usuario está logueado, muestra su panel con el contador
            if (usernameOptional.isPresent()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Hola " + usernameOptional.get() + "</title>");
                // Estilos CSS
                out.println("<style>");
                out.println("body {font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); margin: 0; padding: 0; min-height: 100vh; display: flex; align-items: center; justify-content: center;}");
                out.println(".container {max-width: 500px; width: 90%; background: rgba(255, 255, 255, 0.95); padding: 40px 30px; border-radius: 20px; text-align: center; box-shadow: 0 15px 35px rgba(0,0,0,0.1); backdrop-filter: blur(10px);}");
                out.println("h1 {color: #2c3e50; margin-bottom: 20px; font-weight: 600; font-size: 2.2em;}");
                out.println("p {color: #7f8c8d; font-size: 16px; line-height: 1.6; margin: 10px 0;}");
                out.println(".contador {background: #3498db; color: white; padding: 12px 20px; border-radius: 25px; display: inline-block; font-weight: bold; margin: 15px 0; font-size: 1.2em; box-shadow: 0 4px 15px rgba(52, 152, 219, 0.3);}");
                out.println(".btn {display: inline-block; background: linear-gradient(45deg, #3498db, #2980b9); color: white; border: none; padding: 12px 25px; margin: 10px 8px; cursor: pointer; border-radius: 25px; text-decoration: none; font-size: 14px; font-weight: 500; transition: all 0.3s ease; box-shadow: 0 4px 15px rgba(52, 152, 219, 0.3);}");
                out.println(".btn:hover {transform: translateY(-2px); box-shadow: 0 6px 20px rgba(52, 152, 219, 0.4);}");
                out.println(".btn-logout {background: linear-gradient(45deg, #e74c3c, #c0392b); box-shadow: 0 4px 15px rgba(231, 76, 60, 0.3);}");
                out.println(".btn-logout:hover {box-shadow: 0 6px 20px rgba(231, 76, 60, 0.4);}");
                out.println(".btn-products {background: linear-gradient(45deg, #27ae60, #219a52); box-shadow: 0 4px 15px rgba(39, 174, 96, 0.3);}");
                out.println(".btn-products:hover {box-shadow: 0 6px 20px rgba(39, 174, 96, 0.4);}");
                out.println("</style>");
                // Script JS para confirmar cierre de sesión
                out.println("<script>");
                out.println("function confirmarCerrarSesion() {");
                out.println("  if(confirm('¿Seguro que deseas cerrar sesión?')) {");
                out.println("    window.location.href = '" + req.getContextPath() + "/logout';");
                out.println("  }");
                out.println("}");
                out.println("</script>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>¡Bienvenido " + usernameOptional.get() + "! 👋</h1>");
                out.println("<p>Has iniciado sesión con éxito en Rum Rum</p>");
                out.println("<div class='contador'>");
                out.println("   🎯 Contador de visitas: <b>" + contadorVisitas + "</b>");
                out.println("</div>");
                out.println("<p style='color: #7f8c8d; font-size: 14px;'>Este contador muestra cuántas veces los usuarios han visitado la página de login</p>");
                // Botones de navegación
                out.println("<div style='margin-top: 25px;'>");
                out.println("<button class='btn btn-products' onclick=\"location.href='" + req.getContextPath() + "/productos'\">🏪 Ver Productos</button>");
                out.println("<button class='btn' onclick=\"location.href='" + req.getContextPath() + "/index.html'\">🏠 Volver al Inicio</button>");
                out.println("<button class='btn btn-logout' onclick='confirmarCerrarSesion()'>🚪 Cerrar Sesión</button>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // Si no está logueado, muestra el formulario de login
                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            // Redirige al mismo login para mostrar el contador
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Muestra error en el formulario
            req.setAttribute("error", "Credenciales incorrectas. Usa: admin / 123456");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}