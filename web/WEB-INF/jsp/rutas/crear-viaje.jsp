<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FollowCargo - Crear Ruta</title>
    <link rel="shortcut icon" type="image/png" href="assets/images/logos/Logo-cuadrado-iconoFondo.png" />
    <link rel="stylesheet" href="assets/css/styles.min.css" />
    <link rel="stylesheet" href="assets/css/rutas/styles.css" />
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
                <form id="tripForm" class="needs-validation" novalidate>
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h5 class="card-title fw-semibold">Crear Nueva Ruta</h5>
                        <div>
                            <a href="/FollowCargo/viajes" class="btn btn-secondary me-2">
                                <i class="ti ti-arrow-left"></i> Regresar
                            </a>
                            <button type="submit" class="btn btn-primary" id="submitBtn">
                                <i class="ti ti-device-floppy"></i> 
                                <span id="submitBtnText">Crear Ruta</span>
                            </button>
                        </div>
                    </div>

                    <input type="hidden" id="tripId" value="">
                    
                    <!-- Fecha de Viaje -->
                    <div class="mb-3">
                        <label for="fechaViaje" class="form-label">Fecha de Ruta</label>
                        <input type="date" class="form-control" id="fechaViaje" required>
                        <div class="invalid-feedback">
                            Por favor seleccione la fecha de la Ruta
                        </div>
                    </div>

                    <!-- Selección de Vehículo -->
                    <div class="mb-3">
                        <label for="vehiculo" class="form-label">Vehículo</label>
                        <select class="form-select" id="vehiculo" required>
                            <option value="">Seleccione un vehículo</option>
                            <option value="1" data-recargo="10">Placa: ABC-123 - Toyota Hilux 2023 - Tipo: Liviano</option>
                            <option value="2" data-recargo="15">Placa: DEF-456 - Nissan NP300 2022 - Tipo: Particular</option>
                            <option value="3" data-recargo="20">Placa: GHI-789 - Volvo FH16 2023 - Tipo: Pesado</option>
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
                            <option value="1">Producto: Cajas de cartón - Peso: 500kg</option>
                            <option value="2">Producto: Contenedor refrigerado - Peso: 1000kg</option>
                            <option value="3">Producto: Materiales de construcción - Peso: 2000kg</option>
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

                    <!-- Resumen de Costo -->
                    <div class="card bg-light mb-3">
                        <div class="card-body">
                            <h6 class="card-title">Resumen de Costo</h6>
                            <div class="row">
                                <div class="col-md-4">
                                    <p class="mb-1">Costo por distancia:</p>
                                    <h5 id="costoDistancia">$0.00</h5>
                                </div>
                                <div class="col-md-4">
                                    <p class="mb-1">Recargo por tipo:</p>
                                    <h5 id="costoRecargo">$0.00</h5>
                                </div>
                                <div class="col-md-4">
                                    <p class="mb-1">Costo total:</p>
                                    <h5 id="costoTotal">$0.00</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
   </div>
</div>

    <!-- Scripts -->
    <script src="assets/js/rutas/viaje-form.js"></script>
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