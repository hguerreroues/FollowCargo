<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<fmt:formatDate var="collectionDate" value="${now}" pattern="yyyy-MM-dd"/>
<fmt:setLocale value = "en_US"/>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
%>
<%-- <c:if test="${sessionScope['user_session']==null}">
    <%response.sendRedirect("/FollowCargo");%>
    <%String msg = (String) session.getAttribute("username");%>
</c:if> --%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
         <!--  Data tables -->
        <link href="https://cdn.datatables.net/v/bs5/jszip-3.10.1/dt-2.1.8/b-3.2.0/b-colvis-3.2.0/b-html5-3.2.0/b-print-3.2.0/datatables.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.7.1/css/buttons.dataTables.min.css">
        <!--  Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!--  Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
        <title>Follow Cargo - Dashboard</title>
        <link rel="shortcut icon" type="image/png" href="assets/images/logos/Logo-cuadrado-iconoFondo.png" />
        <link rel="stylesheet" href="assets/css/styles.min.css" />
        
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
                <div class="row">
                    <!--  Tabla de viajes -->
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title d-flex align-items-center gap-2 mb-4">
                                    
                                    <!-- Inicia tabla-->
                                    <table id="rutasDashboard" class="table table-striped" style="width: 100%">
                                        <caption>
                                          Tabla de rutas
                                        </caption>
                                        <thead>
                                          <tr>
                                            <th></th>
                                            <th>#</th>
                                            <th>Origen</th>
                                            <th>Destino</th>
                                            <th>Producto</th>
                                            <th>Costo</th>
                                            <th>Distancia</th>
                                            <th>Estado</th>
                                            <th>Detalle</th>                                            
                                          </tr>
                                        </thead>
                                        <tbody id="table_rutas"></tbody>
                                      </table>
                                    
                                    <!-- Fin tabla-->
                                 
                                </h5>
                            </div>
                        </div>
                    </div>
                                        
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Integrantes del grupo</h5>
                                <div class="table-responsive">
                                    <table class="table text-nowrap align-middle mb-0">
                                        <thead>
                                            <tr class="border-2 border-bottom border-primary border-0"> 
                                                <th scope="col" class="ps-0">Nombre</th>
                                                <th scope="col" >Apellido</th>
                                                <th scope="col" class="text-center">Carnet</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody class="table-group-divider">
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">
                                                        José Luis</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">Alberto Abrego</a>
                                                </td>
                                                <td class="text-center fw-medium">AA17025</td>                                                
                                            </tr>
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">
                                                        Bryan Andres</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">Candelaria Miranda</a>
                                                </td>
                                                <td class="text-center fw-medium">CM16127</td>                                                
                                            </tr>
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">
                                                        Hector Antonio</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">Guerrero Reyes</a>
                                                </td>
                                                <td class="text-center fw-medium">GR97043</td>                                                
                                            </tr>
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">
                                                        Daniel Antonio</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">Romero Duarte</a>
                                                </td>
                                                <td class="text-center fw-medium">RD17009</td>                                                
                                            </tr>
                                           
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>   
                    </div>
                    
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body text-center">
                                <img src="assets/images/logos/Logo-cuadrado.png" alt="image" class="img-fluid" width="138">
                                <h4 class="mt-7">Mira nuestro video de Youtube!</h4>
                                <p class="card-subtitle mt-2 mb-3">Donde explicamos el funcionamiento de nuestro sistema</p>
                                <button class="btn btn-primary mb-3" onclick="window.open('https://www.youtube.com/watch?v=hu4uJjQWacY', '_blank')">
                                    Ver en Youtube
                                </button>
                            </div>
                        </div>
                    </div>
                    
                            <div class="py-6 px-6 text-center">
                        <p class="mb-0 fs-4">Elaborado por IGF115 | Equipo 4 </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
            
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
    <script src="assets/js/dashboard.js"></script>
    <script src="assets/js/tablaRutas.js"></script>
    
</body>

</html>
