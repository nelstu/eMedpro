<%-- 
    Document   : editarMedico
    Created on : 1 mar. 2025, 12:15:30
    Author     : devmarin
--%>



<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="cl.disma.cl.emedpro.model.Medico" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Medico</title>
    <!-- Incluir Bootstrap desde CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <jsp:include page="menu.jsp" />
        <h2 class="text-center">Editar Medico</h2>

        <%
            Medico medico = (Medico) request.getAttribute("medico");
        %>

        <form action="PacienteServlet" method="post" class="border p-4 rounded shadow-sm">
            <input type="hidden" name="action" value="actualizar">
            <input type="hidden" name="id" value="<%= medico.getId() %>">

            <div class="form-group">
                <label for="email">Rut:</label>
                <input type="text" class="form-control" name="rut" id="rut" value="<%= medico.getRut() %>" required>
            </div>

              <div class="form-group">
                <label for="email">Nombres:</label>
                <input type="text" class="form-control" name="nombres" id="nombres" value="<%= medico.getNombres() %>" required>
            </div>
            


            <button type="submit" class="btn btn-primary">Actualizar</button>
        </form>

        <br>
        <a href="UsuarioServlet" class="btn btn-secondary">Volver a la Lista de Medicos</a>
    </div>

    <!-- Incluir jQuery y Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
