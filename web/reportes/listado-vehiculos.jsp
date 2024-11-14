<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                 <!--  Data tables -->
        <link href="https://cdn.datatables.net/v/bs5/jszip-3.10.1/dt-2.1.8/b-3.2.0/b-colvis-3.2.0/b-html5-3.2.0/b-print-3.2.0/datatables.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.7.1/css/buttons.dataTables.min.css">
        <!--  Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!--  Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
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
                                            <i class="ti ti-plus"></i> Nuevo Vehiculo
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
                                <table id="vehiculosTableContainer" class="table table-striped" style="width: 100%">
      
                                        <thead>
                                            <tr>
                                            <th></th>
                                            <th>#</th>
                                            <th>Marca</th>
                                            <th>Modelo</th>
                                            <th>Tipo</th>
                                            <th>Placa</th>
                                            <th>Costo fijo</th>
                                            <th>Conductor</th>
                                            <th>Detalle</th> 
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
            
            <!-- JQuery-->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

            <!-- DataTables (asegúrate de que solo se incluyan una vez) -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
            <script src="https://cdn.datatables.net/v/bs5/jszip-3.10.1/dt-2.1.8/b-3.2.0/b-colvis-3.2.0/b-html5-3.2.0/b-print-3.2.0/datatables.min.js"></script>

            <!-- Bootstrap y otros scripts -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

            <script src="assets/libs/simplebar/dist/simplebar.js"></script>
            <script src="assets/js/sidebarmenu.js"></script>
            <script src="assets/js/app.min.js"></script>
            <script src="assets/js/dashboard.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>
            <script src="https://code.iconify.design/2/2.0.4/iconify.min.js"></script>

            <!-- ApexCharts -->
            <script src="assets/libs/apexcharts/dist/apexcharts.min.js"></script>

            <!-- Archivos de configuración personalizados -->
            <script src="assets/js/vehiculos/tablaVehiculos.js"></script>
    </body>
</html>