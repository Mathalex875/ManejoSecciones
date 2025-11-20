package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {  // ✅ Clase con "C" mayúscula

    private static final String URL = "jdbc:mysql://localhost:3306/basededatosventas?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver MySQL cargado correctamente");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: Driver MySQL no encontrado");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {  // ✅ Método estático
        System.out.println("🔗 Intentando conectar a: " + URL);
        Connection conn = DriverManager.getConnection(URL, USER, PASS);

        if (conn != null) {
            System.out.println("✅ CONEXIÓN EXITOSA a basededatosventas");
        }
        return conn;
    }
}