<%-- 
    Document   : listaReservas.jsp
    Created on : 28-04-2025, 4:23:18 p. m.
    Author     : nelsonstuardo
--%>
<%@page import="cl.disma.cl.emedpro.model.Medico"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@page import="cl.disma.cl.emedpro.model.Reserva" %>

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
            <h2>Reservas</h2>

            <!-- Contenedor del calendario -->
            <div id="calendar"></div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'timeGridDay',
                    slotDuration: '00:20:00',
                    slotLabelInterval: '00:20:00',
                    slotMinTime: '08:00:00',
                    slotMaxTime: '20:00:00',
                    allDaySlot: false,
                    slotLabelFormat: {
                    hour: '2-digit',
                            minute: '2-digit',
                            hour12: true
                    },
                    headerToolbar: {
                    left: 'prev,next today',
                            center: 'title',
                            right: ''
                    },
                    events: [
            <% 
                            List<Reserva> listaReservas = (List<Reserva>) request.getAttribute("listaReservas");
                            if (listaReservas != null) {
                                for (int i = 0; i < listaReservas.size(); i++) {
                                    Reserva r = listaReservas.get(i);
            %>
                    {
                    title: "<%= r.getTitle() %>",
                            start: "<%= r.getStart() %>",
                            end: "<%= r.getEnd() %>"
                    }<%= (i < listaReservas.size() - 1) ? "," : "" %>
            <% 
                                }
                            }
            %>
                    ]
            });
            calendar.render();
            });
        </script>

    </body>
</html>
