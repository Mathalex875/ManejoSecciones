package repository;

import models.Categoria;
import models.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImplement implements Repositorio<Producto> {

    private Connection conn;

    public ProductoRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, c.nombre as categoria_nombre FROM producto p " +
                "INNER JOIN categoria c ON p.categoria_id = c.id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        String sql = "SELECT p.*, c.nombre as categoria_nombre FROM producto p " +
                "INNER JOIN categoria c ON p.categoria_id = c.id WHERE p.id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));

        // Configurar categoría
        Categoria cat = new Categoria();
        cat.setId(rs.getLong("categoria_id"));
        cat.setNombre(rs.getString("categoria_nombre"));
        p.setCategoria(cat);

        // Fechas
        Date fechaElab = rs.getDate("fecha_elaboracion");
        if (fechaElab != null) {
            p.setFechaElaboracion(fechaElab.toLocalDate());
        }

        Date fechaCad = rs.getDate("fecha_caducidad");
        if (fechaCad != null) {
            p.setFechaCaducidad(fechaCad.toLocalDate());
        }

        p.setEstado(rs.getInt("estado"));
        return p;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        // Implementar según necesidad
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // Implementar según necesidad
    }
}