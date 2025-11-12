/*
 * Nombre: Alexis González
 * Fecha: 11/11/2025
 * Descripción: Clase modelo que representa un producto dentro del sistema.
 * Contiene los atributos básicos de un producto como id, nombre, tipo y precio.
 * Implementa encapsulamiento mediante el uso de atributos privados y métodos
 * getters y setters para el acceso controlado a cada campo.
 */

package models;

public class Producto {
    // Encapsulamiento: se declaran las variables privadas del objeto Producto
    private Long idProducto; // Identificador único del producto
    private String nombre;   // Nombre del producto
    private String Tipo;     // Tipo o categoría del producto
    private double precio;   // Precio del producto

    // Constructor vacío: permite crear un producto sin inicializar los valores
    public Producto() {}

    /*
     * Constructor con parámetros: inicializa los valores del producto
     * al momento de crear el objeto con datos específicos.
     */
    public Producto(Long idProducto, String nombre, String Tipo, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.Tipo = Tipo;
        this.precio = precio;
    }

    // Métodos getter y setter para acceder y modificar los atributos del producto

    // Obtiene el ID del producto
    public Long getIdProducto() {
        return idProducto;
    }

    // Establece el ID del producto
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    // Obtiene el nombre del producto
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre del producto
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Obtiene el tipo o categoría del producto
    public String getTipo() {
        return Tipo;
    }

    // Establece el tipo o categoría del producto
    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    // Obtiene el precio del producto
    public double getPrecio() {
        return precio;
    }

    // Establece el precio del producto
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

