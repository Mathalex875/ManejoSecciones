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
            session.invalidate(); // Esto hará que la próxima vez cuente como nueva visita
        }

        // Redirige al usuario a la página principal
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}