<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FollowCargo - Lista de Viajes</title>
    <link rel="shortcut icon" type="image/png" href="assets/images/logos/seodashlogo.png" />
    <link rel="stylesheet" href="assets/css/styles.min.css" />
    <link rel="stylesheet" href="assets/css/rutas/styles.css" />
</head>
<body>
    <!-- Header -->
    
    <div class="container-fluid">
        <div class="card">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h5 class="card-title fw-semibold">Lista de Viajes</h5>
                    <a href="/FollowCargo/crear-viaje" class="btn btn-primary">Nuevo Viaje</a>
                </div>
                
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Vehículo</th>
                                <th>Producto</th>
                                <th>Origen</th>
                                <th>Destino</th>
                                <th>Distancia</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody id="tripsTableBody">
                            <!-- Los datos se cargarán dinámicamente -->
                             Datos cargados dinamicamente
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/rutas/viaje-list.js"></script>
</body>
</html>