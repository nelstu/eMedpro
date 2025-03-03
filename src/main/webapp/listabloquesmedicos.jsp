<%-- 
    Document   : bloquesmedicos
    Created on : 2 mar. 2025, 11:40:14
    Author     : devmarin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bloques Medicos</title>
         <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        
        <div class="container mt-5">
            <jsp:include page="menu.jsp" />
            <h2>Bloques Medicos</h2>

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
                    <th>Medico</th>
                    <th>Desde</th>
                    <th>Hasta</th>
                    <th>Hora Inicial</th>
                    <th>Hora Final</th>
                    <th>Tiempo</th>
                    
                </tr>

            </table>
            </div>
            <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
            <a href="nuevoBloqueMedico.jsp" class="btn btn-secondary">Agregar Bloque Medico</a>
        </div>
        <!-- Incluir jQuery y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
