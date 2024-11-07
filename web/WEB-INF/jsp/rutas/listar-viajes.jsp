<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FollowCargo - Lista de Rutas</title>
    <link rel="shortcut icon" type="image/png" href="assets/images/logos/seodashlogo.png" />
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
    <div class="container-fluid">
        <div class="card">
            <div class="card-body">
                <!-- Header Section con más espacio -->
                <div class="header-section">
                    <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title fw-semibold">Lista de Rutas</h5>
                        <div>
                            <a href="/FollowCargo/dashboard" class="btn btn-secondary me-2">
                                <i class="ti ti-arrow-left"></i> Dashboard
                            </a>
                            <a href="/FollowCargo/crear-viaje" class="btn btn-primary">
                                <i class="ti ti-plus"></i> Nueva Ruta
                            </a>
                        </div>
                    </div>
                </div>
                
                <!-- Contenedor para la tabla y mensaje de no registros -->
                <div class="table-section">
                    <!-- Mensaje de no registros (inicialmente oculto) -->
                    <div id="noRecordsMessage" class="no-records-message" style="display: none;">
                        <i class="ti ti-clipboard-x"></i>
                        <h4>No hay Rutas registradas</h4>
                        <p>Comienza creando una nueva ruta usando el botón "Nueva Ruta"</p>
                    </div>

                    <!-- Tabla (se mostrará/ocultará según haya registros) -->
                    <div id="tripsTableContainer" class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Vehículo</th>
                                    <th>Origen</th>
                                    <th>Destino</th>
                                    <th>Costo</th>
                                    <th>Estado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody id="tripsTableBody">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de Estado -->
    <div class="modal fade" id="estadoModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Cambiar Estado</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <select class="form-select" id="nuevoEstado">
                        <option value="Pendiente">Pendiente</option>
                        <option value="En Ruta">En Ruta</option>
                        <option value="Interrumpido">Interrumpido</option>
                        <option value="Finalizado">Finalizado</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary" id="guardarEstado">Guardar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de Detalles -->
    <div class="modal fade" id="detalleModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Detalles de la Ruta</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <!-- El contenido se insertará dinámicamente -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
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