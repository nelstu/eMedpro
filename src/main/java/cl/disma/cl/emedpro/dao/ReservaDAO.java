/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.dao;



import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.model.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM emedpro_reservas"; // Ajusta si quieres campos específicos

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva();
                
             //   reserva.setId(rs.getInt("id")); // Asumiendo que tu tabla tiene columna 'id'
                reserva.setTitle(rs.getString("title")); // Asegúrate que existan estos campos
                reserva.setStart(rs.getString("start"));
                reserva.setEnd(rs.getString("end"));
                
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return reservas;
    }
    
    
    
    
    
}
