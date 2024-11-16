<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FollowCargo - Vehiculo</title>
    <link rel="shortcut icon" type="image/png" href="assets/images/logos/Logo-cuadrado-iconoFondo.png" />
    <link rel="stylesheet" href="assets/css/styles.min.css" />
    <link rel="stylesheet" href="assets/css/conductores/styles.css" />
    
</head>
<body>
    <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed">
        <!-- Menu-->
        <jsp:include page="include/menu.jsp"/>
        
        <div class="body-wrapper">
            <div class="container-fluid">
                <div class="card">
                    <div class="card-body">
                        <form id="vehiculoForm" class="needs-validation" novalidate>
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h5 class="card-title fw-semibold" id="formTitle">Vehiculo</h5>
                                <div>
                                    <a href="/FollowCargo/vehiculos" class="btn btn-secondary me-2">
                                        <i class="ti ti-arrow-left"></i> Regresar
                                    </a>
                                    <button type="submit" class="btn btn-primary" id="submitBtn">
                                        <i class="ti ti-device-floppy"></i> 
                                        <span id="submitBtnText">Guardar</span>
                                    </button>
                                </div>
                            </div>

                            <input type="hidden" id="vehiculoId">
                            
                            <!-- Información Principal del vehiculo -->
                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <label for="marca" class="form-label">Marca</label>
                                    <input type="text" class="form-control" id="marca" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese la marca del vehiculo
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <label for="modelo" class="form-label">Modelo</label>
                                    <input type="text" class="form-control" id="modelo" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese el modelo del vehiculo
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <label for="tipo" class="form-label">Tipo de vehiculo</label>
                                    <select class="form-select" id="tipo" required>
                                    <option value="">Seleccione el tipo de vehiculo</option>
                                    <option value="1">Refrigerado</option>
                                    <option value="0">Regular</option>
                                </select>
                                    <div class="invalid-feedback">
                                        Por favor ingrese el tipo del vehiculo
                                    </div>
                                </div>
                            </div> 
                            

                            <!-- Otros datos importantes -->
                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <label for="placa" class="form-label">Placa</label>
                                    <input type="text" class="form-control" id="placa" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese el numero de placa
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <label for="costo" class="form-label">Costo fijo</label>
                                    <input type="double" class="form-control" id="costo" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese costo fijo del vehiculo
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <label for="conductor" class="form-label">Nombre del conductor</label>
                                        <select class="form-select" id="conductor" required>
                                        <option value="">Seleccione un conductor</option>
                                        </select>
                                    <div class="invalid-feedback">
                                        Por favor seleccione un conductor
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
    <script src="assets/js/vehiculos/Vehiculos-form.js"></script>
    <jsp:include page="include/modal.jsp"/>
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="assets/libs/simplebar/dist/simplebar.js"></script>
    <script src="assets/js/sidebarmenu.js"></script>
    <script src="assets/js/app.min.js"></script>
    <script src="assets/js/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>
</body>
</html>