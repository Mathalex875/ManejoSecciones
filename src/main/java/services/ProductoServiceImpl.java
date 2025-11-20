package services;

import models.Producto;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "Laptop Gamer", "Tecnología", 1200.00),
                new Producto(2L, "Mouse Inalámbrico", "Tecnología", 25.50),
                new Producto(3L, "Teclado Mecánico", "Tecnología", 89.99),
                new Producto(4L, "Monitor 24\"", "Tecnología", 199.99)
        );
    }

    @Override
    public Optional<Producto> porid(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}