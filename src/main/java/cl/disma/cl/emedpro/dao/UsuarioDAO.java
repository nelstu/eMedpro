package cl.disma.cl.emedpro.dao;

import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

  // Método para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios"; // Consulta para obtener todos los usuarios

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("pass"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return usuarios;
    }

    // Método para buscar usuarios por email
    public List<Usuario> buscarUsuariosPorEmail(String emailBusqueda) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE email LIKE ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + emailBusqueda + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("pass"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
}
