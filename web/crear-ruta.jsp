<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="dateNow" value="${now}" pattern="yyyy-MM-dd" />

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
                            <form action="rutas" method="POST">
                                <input type="hidden" name="action" value="addLocal">
                                <input type="hidden" name="usuario" value="${user_session.nombreUsuario}">
                                <div class="d-flex justify-content-between align-items-center mb-4">
                                    <h5 class="card-title fw-semibold">Crear Nueva Ruta</h5>
                                    <div>
                                        <a href="/FollowCargo/dashboard" class="btn btn-secondary me-2">
                                            <i class="ti ti-arrow-left"></i> Regresar
                                        </a>
                                        <button type="button" class="btn btn-primary" disabled="">
                                            <i class="ti ti-device-floppy"></i> 
                                            <span id="submitBtnText">Crear Ruta</span>
                                        </button>
                                    </div>
                                </div>

                                <input type="hidden" id="tripId" value="">

                                <!-- Fecha de Viaje -->
                                <div class="mb-3">
                                    <label for="fechaViaje" class="form-label">Fecha de Ruta</label>
                                    <input type="date" class="form-control" id="fechaViaje" name="fechaViaje" value="${dateNow}" min="${dateNow}" required>
                                    <div class="invalid-feedback">
                                        Por favor seleccione la fecha de la Ruta
                                    </div>
                                </div>

                                <!-- Selección de Vehículo -->
                                <div class="mb-3">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <label for="vehiculo" class="form-label">Vehículo</label>
                                            <select class="form-select" id="vehiculo" name="vehiculo" required>
                                                <option value="">Seleccione un vehículo</option>
                                                <option value="1" data-recargo="10">Placa: ABC-123 - Toyota Hilux 2023 - Tipo: Liviano</option>
                                                <option value="2" data-recargo="15">Placa: DEF-456 - Nissan NP300 2022 - Tipo: Particular</option>
                                                <option value="3" data-recargo="20">Placa: GHI-789 - Volvo FH16 2023 - Tipo: Pesado</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Por favor seleccione un vehículo
                                            </div>
                                        </div>

                                        <!-- Selección de Vehículo -->
                                        <div class="col-lg-6">
                                            <label for="conductor" class="form-label">Conductor</label>
                                            <select class="form-select" id="conductor" name="conductor" required>
                                                <option value="">Seleccione un conductor</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Por favor seleccione un vehículo
                                            </div>
                                        </div> 
                                    </div> 
                                </div> 

                                <!-- Información de Ruta -->
                                <div class="mb-3">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="mb-3">
                                                <label for="origen" class="form-label">Origen</label>
                                                <input type="text" class="form-control search_location" name="origen" id="search_location" required>
                                                <small id="searchLocation" class="form-text text-muted"><span class="search_addr"></span></small>
                                                <div class="invalid-feedback">
                                                    Por favor ingrese el origen
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label-text" for="latitudOrigen">Latitud:</label>
                                                            <input type="text" class="form-control search_latitude" name="latitudOrigen">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="longitudOrigen">Longitud:</label>
                                                            <input type="text" class="form-control search_longitude" name="longitudOrigen">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="mb-3">
                                                <label for="destino" class="form-label">Destino</label>
                                                <input type="text" class="form-control search_location_destino" id="search_location_destino" name="destino" required>
                                                <small id="searchLocation" class="form-text text-muted"><span class="search_addr_destino"></span></small>
                                                <div class="invalid-feedback">
                                                    Por favor ingrese el destino
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="latitudDestino">Latitud:</label>
                                                            <input type="text" class="form-control search_latitude_destino" name="latitudDestino">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="longitudDestino">Longitud:</label>
                                                            <input type="text" class="form-control search_longitude_destino" name="longitudDestino">
                                                        </div>
                                                    </div>

                                                    <input type="hidden" name="ratio" id="ratio" value="1">
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label for="distancia" class="form-label">Distancia (km)</label>
                                    <input type="number" class="form-control" id="distancia" name="distancia" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese la distancia
                                    </div>
                                </div>

                                <hr>
                                <!-- Selección de Producto -->
                                <div class="mb-3">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <label for="producto" class="form-label">Producto</label>
                                            <select class="form-select" id="producto" name="producto" required>
                                                <option value="">Seleccione un producto</option>
                                                <option value="1">Producto: Cajas de cartón - Peso: 500kg</option>
                                                <option value="2">Producto: Contenedor refrigerado - Peso: 1000kg</option>
                                                <option value="3">Producto: Materiales de construcción - Peso: 2000kg</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Por favor seleccione un producto
                                            </div>
                                            <input type="hidden" id="idProducto">
                                            <input type="hidden" id="nombreProducto">
                                            <input type="hidden" id="pesoProducto" value="0">
                                            <input type="hidden" id="pesoSumaProducto" value="0">
                                        </div>

                                        <input type="hidden" id="costoProducto" name="costoProducto">

                                        <div class="col-lg-3 mb-3">
                                            <label for="cantidad" class="form-label">Cantidad</label>
                                            <input type="number" class="form-control" id="cantidad" value="1">
                                            <div class="invalid-feedback">
                                                Por favor ingrese la cantidad
                                            </div>
                                        </div>

                                        <div class="col-lg-3 mb-3">
                                            <label for="precio" class="form-label">Precio ($)</label>
                                            <input type="text" class="form-control" id="precio">
                                            <div class="invalid-feedback">
                                                Por favor ingrese el precio
                                            </div>
                                        </div>

                                        <div class="col-lg-2 align-middle mb-3">
                                            <label for="precio" class="form-label">.&nbsp;</label>
                                            <button type="button" class="btn btn-danger me-2" onclick="addItem(); cleanItems();" disabled="" id="btnAgregarProducto"> 
                                                <i class="ti ti-plus"></i> Agregar
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <div id="detalleProductos"></div>
                                </div>


                                <!-- Resumen de Costo -->
                                <div class="card bg-light mb-3">
                                    <div class="card-body">
                                        <h6 class="card-title mb-3">Resumen de Costo</h6>
                                        <div class="row">
                                            <div class="col-md-3 text-center">
                                                <p class="mb-1">Costo por distancia:</p>
                                                <h5 id="costoDistancia">$0.00</h5>
                                            </div>
                                            <div class="col-md-3 text-center">
                                                <p class="mb-1">Recargo por tipo:</p>
                                                <h5 id="costoRecargo">$0.00</h5>
                                            </div>
                                            <div class="col-md-3 text-center">
                                                <p class="mb-1">Recargo por peso: (kg)</p>
                                                <h5 id="costoPeso">$0.00</h5>
                                            </div>
                                            <div class="col-md-3 text-center">
                                                <p class="mb-1">Costo total:</p>
                                                <h5 id="costoTotal">$0.00</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="mb-3"><div id="geomap"></div></div>
                                
                                <div class="mb-3">
                                    <div class="d-grid gap-2 col-6 mx-auto">
                                        <input type="hidden" id="costoTotalInput" name="costoTotalInput" value="0">
                                        <button type="submit" class="btn btn-primary btn-lg" id="btnSubmit" disabled="">
                                            <i class="ti ti-device-floppy"></i> 
                                            <span id="submitBtnText">Crear Ruta</span>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- Menu-->
        <jsp:include page="include/modal.jsp"/>
        <script src="assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <!--        <script src="assets/libs/apexcharts/dist/apexcharts.min.js"></script>-->
        <script src="assets/libs/simplebar/dist/simplebar.js"></script>
        <script src="assets/js/sidebarmenu.js"></script>
        <script src="assets/js/app.min.js"></script>
        <!--<script src="assets/js/dashboard.js"></script>-->
        <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>

        <!-- Google API's -->
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDhAeDIVSpivfE9V8DKA1wLk5Jl3i6FaSk"></script>


        <!-- Scripts -->
        <script src="assets/js/rutas/viaje-form.js"></script>
        <script src="assets/js/rutas/crear-ruta.js"></script>
    </body>
</html>