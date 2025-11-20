package services;
/*
Descripcion: Interfaz para el servicio de productos que define las operaciones disponibles.
Autor: Alexis González
Fecha: 2025/11/12
 */
import models.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    /*
     * Retorna una lista de todos los productos disponibles.
     * @return Lista de objetos Producto.
     */
    List<Producto> listar();
    Optional<Producto> porid(Long id);

    Optional<Producto> porId(Long id);
}

