/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cl.disma.cl.emedpro.controller;

import cl.disma.cl.emedpro.Conexion;
import cl.disma.cl.emedpro.model.BloqueMedico;
import cl.disma.cl.emedpro.model.Medico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devmarin
 */
public class BloquesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BloquesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BloquesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Medico> medicos = obtenerMedicos();
        List<BloqueMedico> bloques = obtenerBloquesMedicos();

        request.setAttribute("medicos", medicos);
        request.setAttribute("bloques", bloques);

        request.getRequestDispatcher("nuevoBloqueMedico.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
   String action = request.getParameter("action");
        if ("insertar".equals(action)) {
            insertarBloque(request, response);
        } else if ("eliminar".equals(action)) {
            eliminarBloque(Integer.parseInt(request.getParameter("id")));
            doGet(request, response); // Redirigir después de eliminar
        }else if ("procesar".equals(action)) {
            procesarBloque(Integer.parseInt(request.getParameter("id")));
            doGet(request, response); // Redirigir después de eliminar
        } 
        else {
            doGet(request, response);
        }
}

  private void insertarBloque(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idMedico = Integer.parseInt(request.getParameter("medico"));
        String desdeStr = request.getParameter("desde");
        String hastaStr = request.getParameter("hasta");
        String horaInicialStr = request.getParameter("hora_inicial");
        String horaFinalStr = request.getParameter("hora_final");
        int intervalo = Integer.parseInt(request.getParameter("intervalo"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try {
            java.sql.Date desde = new java.sql.Date(dateFormat.parse(desdeStr).getTime());
            java.sql.Date hasta = new java.sql.Date(dateFormat.parse(hastaStr).getTime());
            java.sql.Time horaInicial = new java.sql.Time(timeFormat.parse(horaInicialStr).getTime());
            java.sql.Time horaFinal = new java.sql.Time(timeFormat.parse(horaFinalStr).getTime());

            String checkSql = "SELECT COUNT(*) FROM emedpro_bloques WHERE idmedico = ? AND desde = ? AND hasta = ? AND hora_inicial = ? AND hora_final = ?";
            String insertSql = "INSERT INTO emedpro_bloques (idmedico, desde, hasta, hora_inicial, hora_final, intervalo) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = Conexion.getConexion();
                 PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                 PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                checkStmt.setInt(1, idMedico);
                checkStmt.setDate(2, desde);
                checkStmt.setDate(3, hasta);
                checkStmt.setTime(4, horaInicial);
                checkStmt.setTime(5, horaFinal);

                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    request.setAttribute("mensaje", "El bloque médico ya existe.");
                } else {
                    insertStmt.setInt(1, idMedico);
                    insertStmt.setDate(2, desde);
                    insertStmt.setDate(3, hasta);
                    insertStmt.setTime(4, horaInicial);
                    insertStmt.setTime(5, horaFinal);
                    insertStmt.setInt(6, intervalo);

                    int filasAfectadas = insertStmt.executeUpdate();
                    if (filasAfectadas > 0) {
                        request.setAttribute("mensaje", "Bloque registrado exitosamente.");
                    } else {
                        request.setAttribute("mensaje", "Error al registrar el bloque.");
                    }
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }
        doGet(request,response);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<BloqueMedico> obtenerBloquesMedicos() {
        List<BloqueMedico> listaBloques = new ArrayList<>();
        String sql = "SELECT id, idmedico, desde, hasta, hora_inicial, hora_final, intervalo FROM emedpro_bloques";

        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BloqueMedico bloque = new BloqueMedico(
                        rs.getInt("id"),
                        rs.getInt("idmedico"),
                        rs.getDate("desde"),
                        rs.getDate("hasta"),
                        rs.getTime("hora_inicial"),
                        rs.getTime("hora_final"),
                        rs.getInt("intervalo")
                );
                listaBloques.add(bloque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Conexion.cerrarConexion();
        }
        return listaBloques;
    }

    private List<Medico> obtenerMedicos() {
        List<Medico> listaMedicos = new ArrayList<>();
        String sql = "SELECT id, rut, nombres FROM emedpro_medicos";

        try (Connection conn = Conexion.getConexion(); // Asegúrate de tener una clase Conexion para obtener la conexión
                 PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico(rs.getInt("id"), rs.getString("rut"), rs.getString("nombres"));
                listaMedicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
        return listaMedicos;
    }
    
    
  

    private void eliminarBloque(int id) {
        String sql = "DELETE FROM emedpro_bloques WHERE id = ?";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 private void procesarBloque(int idBloque) {
    String bloqueSql = "SELECT b.id, b.idmedico, m.nombres, b.desde, b.hasta, b.hora_inicial, b.hora_final, b.intervalo " +
                       "FROM emedpro_bloques b JOIN emedpro_medicos m ON b.idmedico = m.id WHERE b.id = ?";

    String insertReservaSql = "INSERT INTO emedpro_reservas (title, start, end) VALUES (?, ?, ?)";

    try (Connection conn = Conexion.getConexion();
         PreparedStatement bloqueStmt = conn.prepareStatement(bloqueSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertReservaSql)) {

        bloqueStmt.setInt(1, idBloque);
        ResultSet rs = bloqueStmt.executeQuery();

        if (rs.next()) {
            int idMedico = rs.getInt("idmedico");
            String nombreMedico = rs.getString("nombres");
            Date desde = rs.getDate("desde");
            Date hasta = rs.getDate("hasta");
            Time horaInicial = rs.getTime("hora_inicial");
            Time horaFinal = rs.getTime("hora_final");
            int intervalo = rs.getInt("intervalo");

            // Recorrer cada día
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(desde);

            java.util.Calendar hastaCalendar = java.util.Calendar.getInstance();
            hastaCalendar.setTime(hasta);

            while (!calendar.after(hastaCalendar)) {
                Date diaActual = new Date(calendar.getTimeInMillis());

                // Construir horarios dentro del día
                java.util.Calendar horaIni = java.util.Calendar.getInstance();
                horaIni.setTime(horaInicial);

                java.util.Calendar horaFin = java.util.Calendar.getInstance();
                horaFin.setTime(horaFinal);

                java.util.Calendar horaActual = (java.util.Calendar) horaIni.clone();

                while (!horaActual.after(horaFin)) {
                    java.util.Calendar horaSiguiente = (java.util.Calendar) horaActual.clone();
                    horaSiguiente.add(java.util.Calendar.MINUTE, intervalo);

                    if (!horaSiguiente.after(horaFin)) {
                        // Crear start y end en tipo DATETIME
                        Timestamp start = Timestamp.valueOf(diaActual.toString() + " " +
                                String.format("%02d:%02d:00", horaActual.get(java.util.Calendar.HOUR_OF_DAY), horaActual.get(java.util.Calendar.MINUTE)));

                        Timestamp end = Timestamp.valueOf(diaActual.toString() + " " +
                                String.format("%02d:%02d:00", horaSiguiente.get(java.util.Calendar.HOUR_OF_DAY), horaSiguiente.get(java.util.Calendar.MINUTE)));

                        // Insertar la reserva
                        insertStmt.setString(1, nombreMedico);
                        insertStmt.setTimestamp(2, start);
                        insertStmt.setTimestamp(3, end);
                        insertStmt.executeUpdate();
                    }

                    horaActual.add(java.util.Calendar.MINUTE, intervalo);
                }

                calendar.add(java.util.Calendar.DAY_OF_MONTH, 1); // siguiente día
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    

}
