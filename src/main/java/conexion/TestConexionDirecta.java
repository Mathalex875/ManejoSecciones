package conexion;

import java.sql.*;

public class TestConexionDirecta {

    public static void main(String[] args) {
        System.out.println("🚀 INICIANDO PRUEBA DIRECTA DE CONEXIÓN");

        String url = "jdbc:mysql://localhost:3306/basededatosventas";
        String user = "root";
        String password = "";

        try {
            // 1. Cargar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver cargado");

            // 2. Intentar conexión
            System.out.println("🔗 Conectando a: " + url);
            Connection conn = DriverManager.getConnection(url, user, password);

            // 3. Verificar conexión
            if (conn != null) {
                System.out.println("✅ ¡CONEXIÓN EXITOSA!");
                System.out.println("📊 Base de datos: " + conn.getCatalog());

                // 4. Mostrar tablas
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});

                System.out.println("📋 Tablas encontradas:");
                while (tables.next()) {
                    System.out.println("   - " + tables.getString("TABLE_NAME"));
                }

                conn.close();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Error SQL: " + e.getMessage());
            System.err.println("   Código: " + e.getErrorCode());
            System.err.println("   Estado: " + e.getSQLState());
        }
    }
}