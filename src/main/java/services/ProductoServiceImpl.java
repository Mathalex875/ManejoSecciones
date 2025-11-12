/*
 * Nombre: Alexis González
 * Fecha: 11/11/2025
 * Descripción:
 * Esta clase implementa la interfaz ProductoService y proporciona
 * una lista de productos de ejemplo.
 * Simula la obtención de datos desde una fuente externa (como una base de datos),
 * devolviendo una lista de objetos Producto con información precargada.
 */

package services;

import models.Producto;
import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    /*
     * Método: listar
     * Retorna: List<Producto> - Una lista con objetos de tipo Producto.
     * Descripción:
     * Este método devuelve una lista estática de productos con datos de ejemplo.
     * En una aplicación real, esta información podría provenir de una base de datos
     * o de una API externa. Aquí se utiliza Arrays.asList() para crear y retornar
     * una lista inmutable con algunos productos definidos manualmente.
     */
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "Laptop", "Electrónico", 256.20),
                new Producto(2L, "Computadora", "Electrónico", 230.60),
                new Producto(3L, "Cocina", "Electrodoméstico", 300.40)
        );
    }
}
