<%@page import="cl.disma.cl.emedpro.model.Medico"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="cl.disma.cl.emedpro.model.Medico" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Usuarios</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-5">
            <jsp:include page="menu.jsp" />
            <h2>Lista de Medicos</h2>

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
                    List<Medico> listaMedicos = (List<Medico>) request.getAttribute("listaMedicos");
                    if (listaMedicos != null) {
                        for (Medico medico : listaMedicos) {
                %>
                <tr>
                    <td><%= medico.getId()%></td>
                    <td><%= medico.getRut()%></td>
                    <td><%= medico.getNombres()%></td>
                    <td>
                        <a href="MedicoServlet?action=editar&id=<%= medico.getId()%>">Editar</a>
                        <a href="MedicoServlet?action=eliminar&id=<%= medico.getId()%>">Eliminar</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="4">No hay medicos disponibles</td></tr>
                <% }%>
            </table>
            </div>
            <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
            <a href="nuevoMedico.jsp" class="btn btn-secondary">Agregar Nuevo Medico</a>
        </div>
        <!-- Incluir jQuery y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
