/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.controller;




import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.dao.PacienteDAO;
import cl.disma.cl.emedpro.model.Paciente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String emailBusqueda = request.getParameter("emailBusqueda");

        if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Paciente paciente = obtenerPacientePorId(id);
            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("editarPaciente.jsp").forward(request, response);
        } else if ("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            eliminarPaciente(id);
            response.sendRedirect("PacienteServlet");
        } else {
            List<Paciente> listaPacientes;
            if (emailBusqueda != null && !emailBusqueda.isEmpty()) {
                listaPacientes = buscarPacientesPorRut(emailBusqueda);
            } else {
                PacienteDAO pacienteDAO = new PacienteDAO();
                listaPacientes = pacienteDAO.obtenerPacientes();
            }
            request.setAttribute("listaPacientes", listaPacientes);
            request.getRequestDispatcher("listaPacientes.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("accion:"+action);
        if ("insertar".equals(action)) {
            String rut = request.getParameter("rut");
            String nombres = request.getParameter("nombres");

            insertarPaciente(rut, nombres);
            response.sendRedirect("PacienteServlet");
        } else if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String rut = request.getParameter("rut");
            String nombres = request.getParameter("nombres");

            actualizarPaciente(id, rut, nombres);
            response.sendRedirect("PacienteServlet");
        }
    }

    private void insertarPaciente(String rut, String nombres) {
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO emedpro_pacientes (rut, nombres) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rut);
            ps.setString(2, nombres);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    private Paciente obtenerPacientePorId(int id) {
        Paciente paciente = null;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM emedpro_pacientes WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                paciente = new Paciente(rs.getInt("id"), rs.getString("rut"), rs.getString("nombres"), null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return paciente;
    }

    private void eliminarPaciente(int id) {
        Connection con = Conexion.getConexion();
        String sql = "DELETE FROM emedpro_pacientes WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    private List<Paciente> buscarPacientesPorRut(String emailBusqueda) {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.buscarPacientesPorRut(emailBusqueda);
    }

    private void actualizarPaciente(int id, String rut, String nombres) {
        Connection con = Conexion.getConexion();
        String sql = "UPDATE emedpro_pacientes SET rut = ?, nombres = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rut);
            ps.setString(2, nombres);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }
}
