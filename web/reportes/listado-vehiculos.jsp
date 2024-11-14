<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FollowCargo - Lista de Rutas</title>
        <link rel="shortcut icon" type="image/png" href="assets/images/logos/Logo-cuadrado-iconoFondo.png" />
        <link rel="stylesheet" href="assets/css/styles.min.css" />
        <link rel="stylesheet" href="assets/css/rutas/styles.css" />
        <style>
            .header-section {
                margin-bottom: 2rem;
                padding-bottom: 1rem;
                border-bottom: 1px solid #eee;
            }

            .table-section {
                margin-top: 2rem;
            }

            .no-records-message {
                text-align: center;
                padding: 3rem 0;
                background-color: #f8f9fa;
                border-radius: 0.5rem;
                margin: 2rem 0;
            }

            .no-records-message i {
                font-size: 3rem;
                color: #6c757d;
                margin-bottom: 1rem;
            }

            .no-records-message h4 {
                color: #495057;
                margin-bottom: 0.5rem;
            }

            .no-records-message p {
                color: #6c757d;
            }
        </style>
    </head>
    <body>
        <!--  Body Wrapper -->
        <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed">
            <!-- Sidebar Start -->

            <!-- Menu-->
            <jsp:include page="include/menu.jsp"/>
            <!--  Header End -->
            <div class="body-wrapper">
                <div class="container-fluid">
                    <div class="card">
                        <div class="card-body">
                            <!-- Header Section con más espacio -->
                            <div class="header-section">
                                <div class="d-flex justify-content-between align-items-center">
                                    <h5 class="card-title fw-semibold">Listado de Vehiculos</h5>
                                    <div>
                                        <a href="dashboard" class="btn btn-secondary me-2">
                                            <i class="ti ti-arrow-left"></i> Dashboard
                                        </a>
                                        <a href="#" class="btn btn-primary">
                                            <i class="ti ti-plus"></i> NuevO Vehiculo
                                        </a>
                                    </div>
                                </div>
                            </div>

                            <!-- Contenedor para la tabla y mensaje de no registros -->
                            <div class="table-section">
                                <!-- Mensaje de no registros (inicialmente oculto) -->
                                <div id="noRecordsMessage" class="no-records-message" style="display: none;">
                                    <i class="ti ti-clipboard-x"></i>
                                    <h4>No hay vehiculos registrados</h4>
                                    <p>Agrega un nuevo vehiculo usando el botón "Nueva vehiculo"</p>
                                </div>

                                <!-- Tabla (se mostrará/ocultará según haya registros) -->
                                <div id="tripsTableContainer" class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Tipo</th>
                                                <th>Marca</th>
                                                <th>Modelo</th>
                                                <th>Placa</th>
                                                <th>Estado</th>
                                            </tr>
                                        </thead>
                                        <tbody id="vehicleTableBody">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div> 

            <!-- Scripts -->
            <script src="assets/js/rutas/viaje-list.js"></script>
            <!-- Menu-->
            <jsp:include page="include/modal.jsp"/>
            <script src="assets/libs/jquery/dist/jquery.min.js"></script>
            <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
            <script src="assets/libs/apexcharts/dist/apexcharts.min.js"></script>
            <script src="assets/libs/simplebar/dist/simplebar.js"></script>
            <script src="assets/js/sidebarmenu.js"></script>
            <script src="assets/js/app.min.js"></script>
            <script src="assets/js/dashboard.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>
    </body>
</html>