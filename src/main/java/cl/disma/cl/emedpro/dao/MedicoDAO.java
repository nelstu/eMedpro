package cl.disma.cl.emedpro.dao;

import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.model.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

  // Método para obtener todos los usuarios
    public List<Medico> obtenerMedicos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM emedpro_medicos"; // Consulta para obtener todos los usuarios

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico(rs.getInt("id"), rs.getString("rut"), rs.getString("nombres"),null,null);
                medicos.add(medico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return medicos;
    }

    // Método para buscar usuarios por email
    public List<Medico> buscarMedicosPorRut(String emailBusqueda) {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM emedpro_medicos WHERE rut LIKE ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + emailBusqueda + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Medico medico = new Medico(rs.getInt("id"), rs.getString("rut"), rs.getString("nombres"),null,null);
                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicos;
    }
}
