<%@page import="cl.disma.cl.emedpro.model.Medico"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="cl.disma.cl.emedpro.model.Paciente" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Pacientes</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-5">
            <jsp:include page="menu.jsp" />
            <h2>Lista de Pacientes</h2>

            <!-- Formulario de búsqueda por email -->
            <form action="MedicoServlet" method="get">
                <label for="emailBusqueda">Buscar por Rut:</label>
                <input type="text" id="emailBusqueda" name="emailBusqueda" placeholder="Ingresa parte del rut">
                <button type="submit">Buscar</button>
            </form>
            <div class="table-responsive">

            <table  class="table table-bordered table-striped">
                <tr>
                    <th>ID</th>
                    <th>Rut</th>
                    <th>Nombres</th>
                    <th>Acciones</th>
                </tr>
                <%
                    List<Paciente> listaPacientes = (List<Paciente>) request.getAttribute("listaPacientes");
                    if (listaPacientes != null) {
                        for (Paciente paciente : listaPacientes) {
                %>
                <tr>
                    <td><%= paciente.getId()%></td>
                    <td><%= paciente.getRut()%></td>
                    <td><%= paciente.getNombres()%></td>
                    <td>
                        <a href="PacienteServlet?action=editar&id=<%= paciente.getId()%>">Editar</a>
                        <a href="PacienteServlet?action=eliminar&id=<%= paciente.getId()%>">Eliminar</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="4">No hay Pacientes disponibles</td></tr>
                <% }%>
            </table>
            </div>
            <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
            <a href="nuevoPaciente.jsp" class="btn btn-secondary">Agregar Nuevo Paciente</a>
        </div>
        <!-- Incluir jQuery y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
