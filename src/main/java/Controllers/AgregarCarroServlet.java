package Controllers;

import services.ProductoService;
import services.ProductoServiceImpl; // ✅ Temporal con datos de prueba
import models.DetalleCarro;
import models.ItemCarro;
import models.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));

            // ✅ TEMPORAL: Usar implementación con datos de prueba
            ProductoService service = new ProductoServiceImpl();
            Optional<Producto> producto = service.porId(id);

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
                System.out.println("✅ Producto agregado al carrito: " + producto.get().getNombre());
            }

            resp.sendRedirect(req.getContextPath() + "/ver-carro");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto inválido");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al agregar producto al carro");
            e.printStackTrace();
        }
    }
}