<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="left-sidebar">
    <!-- Sidebar scroll-->
    <div>
        <div class="brand-logo d-flex align-items-center justify-content-between">
            <a href="#" class="text-nowrap logo-img">
                <img src="assets/images/logos/logo-vertical.svg" alt="" />
            </a>
            <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                <i class="ti ti-x fs-8"></i>
            </div>
        </div>
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
            <ul id="sidebarnav">
                <li class="nav-small-cap">
                    <i class="ti ti-dots nav-small-cap-icon fs-6"></i>
                    <span class="hide-menu">HOME</span>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="dashboard" aria-expanded="false">
                        <span>
                            <iconify-icon icon="solar:home-smile-bold-duotone" class="fs-8"></iconify-icon>
                        </span>
                        <span class="hide-menu">Dashboard</span>
                    </a>
                </li>
               

                <li class="nav-small-cap">
                    <i class="ti ti-dots nav-small-cap-icon fs-8"></i>
                    <span class="hide-menu">RUTAS</span>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="rutas" aria-expanded="false">
                        <span>
                            <iconify-icon icon="emojione-monotone:delivery-truck" class="fs-8"></iconify-icon>
                            </span>
                        <span class="hide-menu">Listado de rutas</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="crear-ruta" aria-expanded="false">
                        <span>
                            <iconify-icon icon="gis:route" class="fs-8"></iconify-icon>
                        </span>
                        <span class="hide-menu">Crear ruta</span>
                    </a>
                </li>
                
                
                <li class="nav-small-cap">
                    <i class="ti ti-dots nav-small-cap-icon fs-6"></i>
                    <span class="hide-menu">REPORTES</span>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="vehiculos" aria-expanded="false">
                        <span>
                            <iconify-icon icon="fontisto:truck" class="fs-8"></iconify-icon>
                        </span>
                        <span class="hide-menu">Listado vehiculos</span>
                    </a>
                </li>
                
                <li class="sidebar-item">
                    <a class="sidebar-link" href="conductores" aria-expanded="false">
                        <span>
                            <iconify-icon icon="healthicons:truck-driver" class="fs-8"></iconify-icon>
                        </span>
                        <span class="hide-menu">Listado conductores</span>
                    </a>
                </li>

            </ul>
        </nav>
        <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
</aside>

<!--  Sidebar End -->
<!--  Main wrapper -->
<div class="body-wrapper">
    <!--  Header Start -->
    <header class="app-header">
        <nav class="navbar navbar-expand-lg navbar-light">
            <ul class="navbar-nav">
                <li class="nav-item d-block d-xl-none">
                    <a class="nav-link sidebartoggler nav-icon-hover" id="headerCollapse" href="javascript:void(0)">
                        <i class="ti ti-menu-2"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-icon-hover" href="javascript:void(0)">
                        <i class="ti ti-bell-ringing"></i>
                        <div class="notification bg-primary rounded-circle"></div>
                    </a>
                </li>
            </ul>
            <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
                <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
                    <span class="d-none d-md-block"><c:out value="${user_session.nombreCompleto}"/></span>
                    <li class="nav-item dropdown">
                        <a class="nav-link nav-icon-hover" href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="assets/images/profile/profile.png" alt="" width="35" height="35" class="rounded-circle">
                        </a>
                        <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                            <div class="message-body">
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-user fs-6"></i>
                                    <p class="mb-0 fs-3">Mi Perfil</p>
                                </a>
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-mail fs-6"></i>
                                    <p class="mb-0 fs-3">Mi Cuenta</p>
                                </a>
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-list-check fs-6"></i>
                                    <p class="mb-0 fs-3">Mis Tareas</p>
                                </a>
                                <a class="btn btn-outline-primary mx-3 mt-2 d-block" data-bs-toggle="modal" data-bs-target="#logoutModal">
                                    Cerrar sesión</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
</div>