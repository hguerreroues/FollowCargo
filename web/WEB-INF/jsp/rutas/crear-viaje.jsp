<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FollowCargo - Crear Viaje</title>
    <link rel="shortcut icon" type="image/png" href="assets/images/logos/seodashlogo.png" />
    <link rel="stylesheet" href="assets/css/styles.min.css" />
    <link rel="stylesheet" href="assets/css/rutas/styles.css" />
</head>
<body>
    <!-- Header -->
    
    <div class="container-fluid">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title fw-semibold mb-4">Crear Nuevo Viaje</h5>
                <form id="tripForm" class="needs-validation" novalidate>
                    <!-- Selección de Vehículo -->
                    <div class="mb-3">
                        <label for="vehiculo" class="form-label">Vehículo</label>
                        <select class="form-select" id="vehiculo" required>
                            <option value="">Seleccione un vehículo</option>
                            <option value="1">Camión ABC123 - Tipo Furgón</option>
                            <option value="2">Camión DEF456 - Tipo Plataforma</option>
                            <option value="3">Camión GHI789 - Tipo Cisterna</option>
                        </select>
                        <div class="invalid-feedback">
                            Por favor seleccione un vehículo
                        </div>
                    </div>

                    <!-- Selección de Producto -->
                    <div class="mb-3">
                        <label for="producto" class="form-label">Producto</label>
                        <select class="form-select" id="producto" required>
                            <option value="">Seleccione un producto</option>
                            <option value="1">Cajas de cartón - 500kg</option>
                            <option value="2">Contenedor refrigerado - 1000kg</option>
                            <option value="3">Materiales de construcción - 2000kg</option>
                        </select>
                        <div class="invalid-feedback">
                            Por favor seleccione un producto
                        </div>
                    </div>

                    <!-- Información de Ruta -->
                    <div class="mb-3">
                        <label for="origen" class="form-label">Origen</label>
                        <input type="text" class="form-control" id="origen" required>
                        <div class="invalid-feedback">
                            Por favor ingrese el origen
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="destino" class="form-label">Destino</label>
                        <input type="text" class="form-control" id="destino" required>
                        <div class="invalid-feedback">
                            Por favor ingrese el destino
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="distancia" class="form-label">Distancia (km)</label>
                        <input type="number" class="form-control" id="distancia" required>
                        <div class="invalid-feedback">
                            Por favor ingrese la distancia
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Crear Viaje</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/rutas/viaje-form.js"></script>
</body>
</html>