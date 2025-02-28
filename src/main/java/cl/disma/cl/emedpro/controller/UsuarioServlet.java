/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.controller;



import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.dao.UsuarioDAO;
import cl.disma.cl.emedpro.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String emailBusqueda = request.getParameter("emailBusqueda"); // Obtenemos el parámetro de búsqueda

        if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario usuario = obtenerUsuarioPorId(id);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
        } else if ("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            eliminarUsuario(id);
            response.sendRedirect("UsuarioServlet");  // Redirige para que se recargue la lista
        } else {
            // Si hay una búsqueda por email
            List<Usuario> listaUsuarios;
            if (emailBusqueda != null && !emailBusqueda.isEmpty()) {
                listaUsuarios = buscarUsuariosPorEmail(emailBusqueda);
            } else {
                // Si no hay búsqueda, obtener todos los usuarios
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                listaUsuarios = usuarioDAO.obtenerUsuarios();  // Se obtiene la lista completa
            }
            request.setAttribute("listaUsuarios", listaUsuarios);
            request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");

            // Encriptar solo si se ingresa una nueva contraseña
            String passEncriptado = (pass == null || pass.isEmpty()) ? null : encriptarMD5(pass);

            actualizarUsuario(id, email, passEncriptado);
            response.sendRedirect("UsuarioServlet");
        }
    }

    // Método para obtener un usuario por ID
    private Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("pass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return usuario;
    }

    // Método para eliminar un usuario
    private void eliminarUsuario(int id) {
        Connection con = Conexion.getConexion();
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    // Método para buscar usuarios por email
    private List<Usuario> buscarUsuariosPorEmail(String emailBusqueda) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarUsuariosPorEmail(emailBusqueda); // Llamamos al método en el DAO
    }

    // Método para actualizar usuario
    private void actualizarUsuario(int id, String email, String pass) {
        Connection con = Conexion.getConexion();
        String sql = pass != null
                ? "UPDATE usuarios SET email = ?, pass = ? WHERE id = ?"
                : "UPDATE usuarios SET email = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            if (pass != null) {
                ps.setString(2, pass);
                ps.setInt(3, id);
            } else {
                ps.setInt(2, id);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    // Método para encriptar con MD5
    private String encriptarMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
