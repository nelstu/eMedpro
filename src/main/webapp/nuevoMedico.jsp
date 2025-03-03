<%-- 
    Document   : nuevoUsuario
    Created on : 28-02-2025, 3:34:38 p. m.
    Author     : nelsonstuardo
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Medico</title>
    <!-- Incluir Bootstrap desde CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <jsp:include page="menu.jsp" />
        <h2 class="text-center">Registrar Nuevo Medico</h2>

        <form action="MedicoServlet" method="post" class="border p-4 rounded shadow-sm">
            <input type="hidden" name="action" value="insertar">
            <div class="form-group">
                <label for="email">Rut:</label>
                <input type="text" class="form-control" name="rut" id="rut" required>
            </div>

            <div class="form-group">
                <label for="pass">Nombres:</label>
                <input type="text" class="form-control" name="nombres" id="nombres" required>
            </div>

            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>

        <br>
        <a href="MedicoServlet" class="btn btn-secondary">Volver a la Lista de Medicos</a>
    </div>

    <!-- Incluir jQuery y Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

