<%-- 
    Document   : listaReservasPacientes.jsp
    Created on : 28-04-2025, 4:36:01 p. m.
    Author     : nelsonstuardo
--%>


<%@page import="cl.disma.cl.emedpro.model.Medico"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservas</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- FullCalendar CSS -->
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">

        <!-- FullCalendar JS -->
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>


    </head>
    <body>
        <div class="container mt-5">
            <jsp:include page="menu.jsp" />
            <h2>Reservas Pacientes</h2>

            <!-- Contenedor del calendario -->
            <div id="calendar"></div>
        </div>
  <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'timeGridDay',      // Vista de un solo día
                slotDuration: '00:20:00',         // Intervalos de 20 minutos
                slotLabelInterval: '00:20:00',    // Etiquetas cada 20 minutos
                slotMinTime: '08:00:00',           // Desde las 08:00 AM
                slotMaxTime: '20:00:00',           // Hasta las 08:00 PM
                allDaySlot: false,                 // No mostrar slot "todo el día"
                slotLabelFormat: {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: true                  // Para mostrar AM/PM
                },
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: ''
                },
                events: [
                    {
                        title: 'Reserva 1',
                        start: new Date().toISOString().slice(0,10) + 'T08:40:00',
                        end: new Date().toISOString().slice(0,10) + 'T09:00:00'
                    },
                    {
                        title: 'Reserva 2',
                        start: new Date().toISOString().slice(0,10) + 'T09:40:00',
                        end: new Date().toISOString().slice(0,10) + 'T10:00:00'
                    }
                ]
            });

            calendar.render();
        });
    </script>
    </body>
</html>
