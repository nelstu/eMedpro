/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.dao;

import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

  // Método para obtener todos los usuarios
    public List<Paciente> obtenerPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM emedpro_pacientes"; // Consulta para obtener todos los usuarios

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente(rs.getInt("id"), rs.getString("rut"), rs.getString("nombres"),null,null);
                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return pacientes;
    }

    // Método para buscar usuarios por email
    public List<Paciente> buscarPacientesPorRut(String emailBusqueda) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM emedpro_pacientes WHERE rut LIKE ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + emailBusqueda + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Paciente paciente = new Paciente(rs.getInt("id"), rs.getString("rut"), rs.getString("nombres"),null,null);
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pacientes;
    }
}
