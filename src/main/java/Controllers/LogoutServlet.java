package Controllers;
/*
Descripción: Esta clase se encarga de manejar el cierre de sesión de los usuarios.
Si el usuario está autenticado, invalida la sesión y redirige a la página principal
Autor: Alexis González
Fecha: 2025/11/12
*/
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.LoginService;
import services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Servicio de autenticación para verificar usuario activo
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        // Si hay un usuario logueado, invalida la sesión
        if(username.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate(); // Cierra sesión eliminando datos de la sesión para que se vuelva a crear y asi funcione el contador
        }

        // Redirige al usuario a la página principal
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
