<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="assets/images/logos/Logo-cuadrado-icono.png" />
        <link rel="stylesheet" href="assets/css/styles.min.css" />
    </head>

    <body>
        <!--  Body Wrapper -->
        <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed">
            <div class="position-relative overflow-hidden radial-gradient min-vh-100 d-flex align-items-center justify-content-center">
                <div class="d-flex align-items-center justify-content-center w-100">
                    <div class="row justify-content-center w-100">
                        <div class="col-md-8 col-lg-6 col-xxl-3">
                            <div class="card mb-0">
                                <div class="card-body">
                                    <div class="text-nowrap logo-img text-center d-block py-3 w-100">
                                        <img src="assets/images/logos/logo-vertical.svg" alt="">
                                    </div>
                                    <p class="text-center">Follow Cargo</p>

                                    <form action="Login" method="POST" onsubmit="return false">
                                        <input type="hidden" name="action" id="action" value="login">
                                        <input type="hidden" name="contra" id="contra">

                                        <div class="mb-3">
                                            <label for="nombreUsuario" class="form-label">Nombre de Usuario</label>
                                            <input type="text" class="form-control" name="nombreUsuario" id="nombreUsuario" aria-describedby="emailHelp">
                                        </div>
                                        <div class="mb-4">
                                            <label for="contra" class="form-label">Contraseña</label>
                                            <input type="password" class="form-control" name="psw" id="psw">
                                        </div>

                                        <c:if test="${param.error == 'invalidCredentials'}">
                                            <div class="alert alert-danger" role="alert">
                                                Las credenciales son incorrectas. Por favor, intente nuevamente.
                                            </div>
                                        </c:if>

                                        <div class="d-flex align-items-center justify-content-between mb-4">
                                            <a class="text-primary fw-bold" href="#">Olvidaste tu contaseña?</a>
                                        </div>
                                        <button type="submit" class="btn btn-primary w-100 py-8 fs-4 mb-4" onclick="getSha(); form.submit()">Iniciar Sesión</button>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <%
                                                String message = (String) session.getAttribute("login_message");
                                                if (message != null) {
                                                    out.println(message);
                                                    session.removeAttribute("login_mesage");
                                                }
                                            %>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>
        <script src="assets/js/sha.js" type="text/javascript"></script>        
        <script type="text/javascript">
                                            function getSha()
                                            {
                                                //event.preventDefault();
                                                pwd = $('#psw').val();
                                                hash = new jsSHA(unescape(pwd), 'ASCII');
                                                hash = hash.getHash('SHA-512', 'HEX');
                                                $('#contra').val(hash);
                                            }
        </script>
    </body>
</html>
