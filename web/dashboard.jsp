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
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title d-flex align-items-center gap-2 mb-4">
                                    Rutas
                                    <span>
                                        <iconify-icon icon="solar:question-circle-bold" class="fs-7 d-flex text-muted" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-custom-class="tooltip-success" data-bs-title="Traffic Overview"></iconify-icon>
                                    </span>
                                </h5>
                                <div id="traffic-overview" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body text-center">
                                <img src="assets/images/logos/Logo-cuadrado.png" alt="image" class="img-fluid" width="260">
                                <h4 class="mt-7">Productivity Tips!</h4>
                                <p class="card-subtitle mt-2 mb-3">Duis at orci justo nulla in libero id leo
                                    molestie sodales phasellus justo.</p>
                                <button class="btn btn-primary mb-3">View All Tips</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">View by page title and screen class</h5>
                                <div class="table-responsive">
                                    <table class="table text-nowrap align-middle mb-0">
                                        <thead>
                                            <tr class="border-2 border-bottom border-primary border-0"> 
                                                <th scope="col" class="ps-0">Page Title</th>
                                                <th scope="col" >Link</th>
                                                <th scope="col" class="text-center">Pageviews</th>
                                                <th scope="col" class="text-center">Page Value</th>
                                            </tr>
                                        </thead>
                                        <tbody class="table-group-divider">
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">Welcome to our
                                                        website</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">/index.jsp</a>
                                                </td>
                                                <td class="text-center fw-medium">18,456</td>
                                                <td class="text-center fw-medium">$2.40</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">Modern Admin
                                                        Dashboard Template</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">/dashboard</a>
                                                </td>
                                                <td class="text-center fw-medium">17,452</td>
                                                <td class="text-center fw-medium">$0.97</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">Explore our
                                                        product catalog</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">/product-checkout</a>
                                                </td>
                                                <td class="text-center fw-medium">12,180</td>
                                                <td class="text-center fw-medium">$7,50</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium">
                                                    <span class="table-link1 text-truncate d-block">Comprehensive
                                                        User Guide</span>
                                                </th>
                                                <td>
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">/docs</a>
                                                </td>
                                                <td class="text-center fw-medium">800</td>
                                                <td class="text-center fw-medium">$5,50</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="ps-0 fw-medium border-0">
                                                    <span class="table-link1 text-truncate d-block">Check out our
                                                        services</span>
                                                </th>
                                                <td class="border-0">
                                                    <a href="javascript:void(0)" class="link-primary text-dark fw-medium d-block">/services</a>
                                                </td>
                                                <td class="text-center fw-medium border-0">1300</td>
                                                <td class="text-center fw-medium border-0">$2,15</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title d-flex align-items-center gap-2 mb-5 pb-3">Sessions by
                                    device<span><iconify-icon icon="solar:question-circle-bold" class="fs-7 d-flex text-muted" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-custom-class="tooltip-success" data-bs-title="Locations"></iconify-icon></span>
                                </h5>
                                <div class="row">
                                    <div class="col-4">
                                        <iconify-icon icon="solar:laptop-minimalistic-line-duotone" class="fs-7 d-flex text-primary"></iconify-icon>
                                        <span class="fs-11 mt-2 d-block text-nowrap">Computers</span>
                                        <h4 class="mb-0 mt-1">87%</h4>
                                    </div>
                                    <div class="col-4">
                                        <iconify-icon icon="solar:smartphone-line-duotone" class="fs-7 d-flex text-secondary"></iconify-icon>
                                        <span class="fs-11 mt-2 d-block text-nowrap">Smartphone</span>
                                        <h4 class="mb-0 mt-1">9.2%</h4>
                                    </div>
                                    <div class="col-4">
                                        <iconify-icon icon="solar:tablet-line-duotone" class="fs-7 d-flex text-success"></iconify-icon>
                                        <span class="fs-11 mt-2 d-block text-nowrap">Tablets</span>
                                        <h4 class="mb-0 mt-1">3.1%</h4>
                                    </div>
                                </div>

                                <div class="vstack gap-4 mt-7 pt-2">
                                    <div>
                                        <div class="hstack justify-content-between">
                                            <span class="fs-3 fw-medium">Computers</span>
                                            <h6 class="fs-3 fw-medium text-dark lh-base mb-0">87%</h6>
                                        </div>
                                        <div class="progress mt-6" role="progressbar" aria-label="Warning example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                                            <div class="progress-bar bg-primary" style="width: 100%"></div>
                                        </div>
                                    </div>

                                    <div>
                                        <div class="hstack justify-content-between">
                                            <span class="fs-3 fw-medium">Smartphones</span>
                                            <h6 class="fs-3 fw-medium text-dark lh-base mb-0">9.2%</h6>
                                        </div>
                                        <div class="progress mt-6" role="progressbar" aria-label="Warning example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                                            <div class="progress-bar bg-secondary" style="width: 50%"></div>
                                        </div>
                                    </div>

                                    <div>
                                        <div class="hstack justify-content-between">
                                            <span class="fs-3 fw-medium">Tablets</span>
                                            <h6 class="fs-3 fw-medium text-dark lh-base mb-0">3.1%</h6>
                                        </div>
                                        <div class="progress mt-6" role="progressbar" aria-label="Warning example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                                            <div class="progress-bar bg-success" style="width: 35%"></div>
                                        </div>
                                    </div>

                                </div>
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