<%@page import="cl.disma.cl.emedpro.model.BloqueMedico"%>
<%@page import="cl.disma.cl.emedpro.model.Medico"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nuevo Bloque Médico</title>
        <!-- Incluir Bootstrap desde CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <jsp:include page="menu.jsp" />
            <h2 class="text-center">Registrar Nuevo Bloque Médico</h2>

            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) {%>
            <div style="color: red; font-weight: bold;">
                <%= error%>
            </div>
            <% } %>

            <% String mensaje = (String) request.getAttribute("mensaje"); %>
            <% if (mensaje != null) {%>
            <div style="color: <%= mensaje.contains("Error") ? "red" : mensaje.contains("existe") ? "orange" : "green"%>; font-weight: bold;">
                <%= mensaje%>
            </div>
            <% } %>



            <form action="BloquesServlet" method="post" class="border p-4 rounded shadow-sm">
                <input type="hidden" name="action" value="insertar">

                <div class="form-group">
                    <label for="medico">Médico:</label>
                    <select class="form-control" name="medico" id="medico" required>
                        <option value="">Seleccione un médico</option>
                        <%
                            List<Medico> medicos = (List<Medico>) request.getAttribute("medicos");
                            if (medicos != null) {
                                for (Medico medico : medicos) {
                        %>
                        <option value="<%= medico.getId()%>"><%= medico.getNombres()%> - <%= medico.getRut()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="desde">Desde:</label>
                    <input type="date" class="form-control" name="desde" id="desde" required>
                </div>

                <div class="form-group">
                    <label for="hasta">Hasta:</label>
                    <input type="date" class="form-control" name="hasta" id="hasta" required>
                </div>

                <div class="form-group">
                    <label for="hora_inicial">Hora Inicial:</label>
                    <input type="time" class="form-control" name="hora_inicial" id="hora_inicial" required>
                </div>

                <div class="form-group">
                    <label for="hora_final">Hora Final:</label>
                    <input type="time" class="form-control" name="hora_final" id="hora_final" required>
                </div>

                <div class="form-group">
                    <label for="intervalo">Intervalo (minutos):</label>
                    <input type="number" class="form-control" name="intervalo" id="intervalo" min="1" required>
                </div>

                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>

            <br>
            <a href="BloquesServlet" class="btn btn-secondary">Volver a la Lista de Bloques Médicos</a>



            <br>
            <h3 class="text-center">Bloques Médicos Registrados</h3>
            <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Médico</th>
                        <th>Desde</th>
                        <th>Hasta</th>
                        <th>Hora Inicial</th>
                        <th>Hora Final</th>
                        <th>Intervalo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<BloqueMedico> bloques = (List<BloqueMedico>) request.getAttribute("bloques");
                        if (bloques != null) {
                            for (BloqueMedico bloque : bloques) {
                    %>
                    <tr>
                        <td><%= bloque.getId()%></td>
                        <td><%= bloque.getIdmedico()  %></td>
                        <td><%= bloque.getDesde()%></td>
                        <td><%= bloque.getHasta()%></td>
                        <td><%= bloque.getHoraInicial()%></td>
                        <td><%= bloque.getHoraFinal()%></td>
                        <td><%= bloque.getIntervalo()%> min</td>
                        <td>
                            <form action="BloquesServlet" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="eliminar">
                                <input type="hidden" name="id" value="<%= bloque.getId()%>">
                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>

                </tbody>
            </table>
                    </div>
        </div>

        <!-- Incluir jQuery y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
