<%-- 
    Document   : menu
    Created on : 28-02-2025, 6:11:42?p. m.
    Author     : nelsonstuardo
--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">App</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ReservaServle">Reserva Hora</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="BloquesServlet">Bloques Medicos</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Tablas
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="UsuarioServlet">Usuarios</a>
                                <a class="dropdown-item" href="MedicoServlet">Medicos</a>
                                <a class="dropdown-item" href="PacienteServlet">Pacientes</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>