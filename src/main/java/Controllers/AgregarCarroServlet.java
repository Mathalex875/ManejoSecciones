package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.DetalleCarro;
import models.ItemCarro;
import models.Producto;
import services.ProductoService;
import services.ProductoServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            ProductoService service = new ProductoServiceImpl();
            Optional<Producto> producto = service.porid(id);

            if (producto.isPresent()) {
                ItemCarro item = new ItemCarro(1, producto.get());
                HttpSession session = req.getSession();
                DetalleCarro detalleCarro;

                if (session.getAttribute("carro") == null) {
                    detalleCarro = new DetalleCarro();
                    session.setAttribute("carro", detalleCarro);
                } else {
                    detalleCarro = (DetalleCarro) session.getAttribute("carro");
                }
                detalleCarro.addItemCarro(item);
            }
            // Redirige al servlet en lugar del JSP directamente
            resp.sendRedirect(req.getContextPath() + "/ver-carro");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/productos");
        }
    }
}