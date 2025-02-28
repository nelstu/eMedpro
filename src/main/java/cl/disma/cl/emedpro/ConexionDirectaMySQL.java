/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionDirectaMySQL {
    public static void main(String[] args) {
        // Datos de conexión
        String url = "jdbc:mysql://186.64.123.171:3306/Rodalin?useSSL=false";
        String usuario = "tecadmin";
        String contraseña = "NSloteria2015";

        // Intentar conectar
        try {
            // Cargar el driver MySQL para la versión 5.1.49
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establecer conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conexión exitosa a la base de datos");

            // Crear una consulta SQL
            String sql = "SELECT * FROM clientes WHERE rut LIKE '%RUBIO%' OR nombre LIKE '%RUBIO%'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Mostrar los resultados
            while (rs.next()) {
                System.out.println("Cliente: " + rs.getString("nombre"));
            }

            // Cerrar conexión
            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
