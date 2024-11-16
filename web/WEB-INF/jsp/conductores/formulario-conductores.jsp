<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FollowCargo - Conductor</title>
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
                        <form id="conductorForm" class="needs-validation" novalidate>
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h5 class="card-title fw-semibold" id="formTitle">Conductor</h5>
                                <div>
                                    <a href="/FollowCargo/lista-conductores" class="btn btn-secondary me-2">
                                        <i class="ti ti-arrow-left"></i> Regresar
                                    </a>
                                    <button type="submit" class="btn btn-primary" id="submitBtn">
                                        <i class="ti ti-device-floppy"></i> 
                                        <span id="submitBtnText">Guardar</span>
                                    </button>
                                </div>
                            </div>

                            <input type="hidden" id="conductorId">
                            
                            <!-- Información Personal -->
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="nombres" class="form-label">Nombres</label>
                                    <input type="text" class="form-control" id="nombres" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese los nombres
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="apellidos" class="form-label">Apellidos</label>
                                    <input type="text" class="form-control" id="apellidos" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese los apellidos
                                    </div>
                                </div>
                            </div>

                            <!-- Información de Contacto -->
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="telefono" class="form-label">Teléfono</label>
                                    <input type="tel" class="form-control" id="telefono" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese un teléfono válido
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese un email válido
                                    </div>
                                </div>
                            </div>

                            <!-- Información de Licencia -->
                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <label for="licencia" class="form-label">Número de Licencia</label>
                                    <input type="text" class="form-control" id="licencia" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese el número de licencia
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <label for="fechaLicencia" class="form-label">Fecha de Emisión</label>
                                    <input type="date" class="form-control" id="fechaLicencia" required>
                                    <div class="invalid-feedback">
                                        Por favor seleccione la fecha de emisión
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <label for="vencimientoLicencia" class="form-label">Fecha de Vencimiento</label>
                                    <input type="date" class="form-control" id="vencimientoLicencia" required>
                                    <div class="invalid-feedback">
                                        Por favor seleccione la fecha de vencimiento
                                    </div>
                                </div>
                            </div>

                            <!-- Estado -->
                            <div class="mb-3">
                                <label for="estado" class="form-label">Estado</label>
                                <select class="form-select" id="estado" required>
                                    <option value="">Seleccione un estado</option>
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                                <div class="invalid-feedback">
                                    Por favor seleccione un estado
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="assets/js/conductores/conductores-form.js"></script>
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