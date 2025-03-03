/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.controller;

import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.dao.MedicoDAO;
import cl.disma.cl.emedpro.model.Medico;
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

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String emailBusqueda = request.getParameter("emailBusqueda");

        if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Medico medico = obtenerMedicoPorId(id);
            request.setAttribute("medico", medico);
            request.getRequestDispatcher("editarMedico.jsp").forward(request, response);
        } else if ("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            eliminarMedico(id);
            response.sendRedirect("MedicoServlet");
        } else {
            List<Medico> listaMedicos;
            if (emailBusqueda != null && !emailBusqueda.isEmpty()) {
                listaMedicos = buscarMedicosPorRut(emailBusqueda);
            } else {
                MedicoDAO medicoDAO = new MedicoDAO();
                listaMedicos = medicoDAO.obtenerMedicos();
            }
            request.setAttribute("listaMedicos", listaMedicos);
            request.getRequestDispatcher("listaMedicos.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("accion:"+action);
        if ("insertar".equals(action)) {
            String rut = request.getParameter("rut");
            String nombres = request.getParameter("nombres");

            insertarMedico(rut, nombres);
            response.sendRedirect("MedicoServlet");
        } else if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String rut = request.getParameter("rut");
            String nombres = request.getParameter("nombres");

            actualizarMedico(id, rut, nombres);
            response.sendRedirect("MedicoServlet");
        }
    }

    private void insertarMedico(String rut, String nombres) {
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO emedpro_medicos (rut, nombres) VALUES (?, ?)";

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

    private Medico obtenerMedicoPorId(int id) {
        Medico medico = null;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM emedpro_medicos WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medico = new Medico(rs.getInt("id"), rs.getString("rut"), rs.getString("nombres"), null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return medico;
    }

    private void eliminarMedico(int id) {
        Connection con = Conexion.getConexion();
        String sql = "DELETE FROM emedpro_medicos WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    private List<Medico> buscarMedicosPorRut(String emailBusqueda) {
        MedicoDAO medicoDAO = new MedicoDAO();
        return medicoDAO.buscarMedicosPorRut(emailBusqueda);
    }

    private void actualizarMedico(int id, String rut, String nombres) {
        Connection con = Conexion.getConexion();
        String sql = "UPDATE emedpro_medicos SET rut = ?, nombres = ? WHERE id = ?";

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
