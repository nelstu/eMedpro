package cl.disma.cl.emedpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://186.64.123.171:3306/Rodalin?useSSL=false";
    private static final String USUARIO = "tecadmin";
    private static final String PASSWORD = "NSloteria2015";

    private static Connection conexion = null;

    // Método para obtener la conexión
    public static Connection getConexion() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        Connection con = getConexion();
        if (con != null) {
            System.out.println("Prueba de conexión realizada con éxito.");
            cerrarConexion();
        } else {
            System.err.println("No se pudo establecer conexión con la base de datos.");
        }
    }
}
