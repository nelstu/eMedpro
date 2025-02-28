<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="cl.disma.cl.emedpro.model.Usuario" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Usuarios</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-5">
            <h2>Lista de Usuarios</h2>

            <!-- Formulario de búsqueda por email -->
            <form action="UsuarioServlet" method="get">
                <label for="emailBusqueda">Buscar por Email:</label>
                <input type="text" id="emailBusqueda" name="emailBusqueda" placeholder="Ingresa parte del email">
                <button type="submit">Buscar</button>
            </form>

            <table  class="table table-bordered table-striped">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Acciones</th>
                </tr>
                <%
                    List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
                    if (listaUsuarios != null) {
                        for (Usuario usuario : listaUsuarios) {
                %>
                <tr>
                    <td><%= usuario.getId()%></td>
                    <td><%= usuario.getEmail()%></td>
                    <td><%= usuario.getPass()%></td>
                    <td>
                        <a href="UsuarioServlet?action=editar&id=<%= usuario.getId()%>">Editar</a>
                        <a href="UsuarioServlet?action=eliminar&id=<%= usuario.getId()%>">Eliminar</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="4">No hay usuarios disponibles</td></tr>
                <% }%>
            </table>

            <a href="index.jsp">Volver al Inicio</a>
            <a href="nuevoUsuario.jsp">Agregar Nuevo Usuario</a>
        </div>
        <!-- Incluir jQuery y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
