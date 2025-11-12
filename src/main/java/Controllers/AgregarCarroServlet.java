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

        ProductoService service = new ProductoServiceImpl();

        Long id = Long.parseLong(String.valueOf(req.getDateHeader("id")));

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
        resp.sendRedirect(req.getContextPath()+ "/ver-carro.jsp");
    }
}