<%-- 
    Document   : editarUsuario
    Created on : 28-02-2025, 3:47:18 p. m.
    Author     : nelsonstuardo
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="cl.disma.cl.emedpro.model.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Usuario</title>
    <!-- Incluir Bootstrap desde CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Editar Usuario</h2>

        <%
            Usuario usuario = (Usuario) request.getAttribute("usuario");
        %>

        <form action="UsuarioServlet" method="post" class="border p-4 rounded shadow-sm">
            <input type="hidden" name="action" value="actualizar">
            <input type="hidden" name="id" value="<%= usuario.getId() %>">

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" name="email" id="email" value="<%= usuario.getEmail() %>" required>
            </div>

            <div class="form-group">
                <label for="pass">Nueva Contraseña:</label>
                <input type="password" class="form-control" name="pass" id="pass">
            </div>

            <button type="submit" class="btn btn-primary">Actualizar</button>
        </form>

        <br>
        <a href="UsuarioServlet" class="btn btn-link">Volver a la Lista de Usuarios</a>
    </div>

    <!-- Incluir jQuery y Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
