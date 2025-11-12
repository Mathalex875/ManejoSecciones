package services;
/*
Descripcion: Clase que implementa el servicio de productos.
Proporcionando una lista de productos predefinidos.
Autor: Alexis González
Fecha: 2025/11/12
 */
import models.Producto;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {
    /*
     * Retorna una lista simulada de productos (modo estático, sin base de datos).
     * @return Lista de objetos Producto con datos de ejemplo.
     */
    @Override
    public List<Producto> listar() {
        //Se retorna una lista de productos predefinidos
        return Arrays.asList(new Producto(1L,"laptop","Computacion", 874.21),
                new Producto(2L, "Mouse", "Inalambrico", 20.00),
                new Producto(3L,"Samsung", "Samsung", 2000.00),
                new Producto(4L,"Impresora", "Electronico",500.00),
                new Producto(5L,"Cocina","Electrodomestico",450.00));
    }

    @Override
    public Optional<Producto> porid(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }
}
