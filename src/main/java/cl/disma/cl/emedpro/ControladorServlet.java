/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cl.disma.cl.emedpro;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.*;

/**
 *
 * @author nelsonstuardo
 */
public class ControladorServlet extends HttpServlet {
    
    @Resource(name="jdbc/Productos")
    private DataSource miPool;
    
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
            out.println("<title>Servlet ControladorServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorServlet at " + request.getContextPath() + "</h1>");
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
      /*  
        String [] productos={"Destonillador","Serrucho","Tornillo"};
        request.setAttribute("lista_productos",productos);
        RequestDispatcher miDispatcher=request.getRequestDispatcher("/VistaJSP.jsp");
        miDispatcher.forward(request, response);
        */
          PrintWriter salida=response.getWriter();
          response.setContentType("text/plain");


        // Intentar conectar
        try {
            // Cargar el driver MySQL
            Class.forName("com.mysql.jdbc.Driver");
            
            
            // Establecer conexión
            Connection conexion = Conexion.getConexion();
            System.out.println("✅ Conexión exitosa a la base de datos");

            // Crear una consulta SQL
            String sql = "SELECT * FROM clientes WHERE rut LIKE '%RUBIO%' OR nombre LIKE '%RUBIO%'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Mostrar los resultados
            while (rs.next()) {
                salida.println("Cliente: " + rs.getString("nombre"));
            }

            // Cerrar conexión
            rs.close();
            stmt.close();
            Conexion.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
  
        
        
        
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

}
