package models;
/*
Descripcion: Clase que representa un producto dentro del sistema.
Contiene los atributos básicos de un producto y sus métodos de acceso.
Autor: Alexis González
Fecha: 2025/11/12
 */

public class Producto {
    private Long id;
    private String nombre;
    private String tipo;
    private double precio;

    // Constructor vacío (necesario para frameworks o instanciación manual sin parámetros)
    public Producto() {
    }

    // Constructor que inicializa todos los atributos del producto
    public Producto(Long id, String nombre, String tipo, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    // Getter y Setter para cada atributo, permitiendo el acceso y modificación de los mismos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
