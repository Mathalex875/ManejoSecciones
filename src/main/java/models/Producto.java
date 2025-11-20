package models;

import java.time.LocalDate;

public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private double precio;
    private int stock;
    private String descripcion;
    private LocalDate fechaElaboracion;
    private LocalDate fechaCaducidad;
    private int estado;

    public Producto() {}

    // Constructor actualizado
    public Producto(Long id, String nombre, Categoria categoria, double precio,
                    int stock, String descripcion, LocalDate fechaElaboracion,
                    LocalDate fechaCaducidad, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.fechaElaboracion = fechaElaboracion;
        this.fechaCaducidad = fechaCaducidad;
        this.estado = estado;
    }

    public Producto(long l, String mouseInalámbrico, String tecnología, double v) {
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaElaboracion() { return fechaElaboracion; }
    public void setFechaElaboracion(LocalDate fechaElaboracion) { this.fechaElaboracion = fechaElaboracion; }

    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }


}