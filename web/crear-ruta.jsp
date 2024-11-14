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
                            <form id="tripForm" class="needs-validation" novalidate>
                                <div class="d-flex justify-content-between align-items-center mb-4">
                                    <h5 class="card-title fw-semibold">Crear Nueva Ruta</h5>
                                    <div>
                                        <a href="rutas" class="btn btn-secondary me-2">
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
                                    <input type="date" class="form-control" id="fechaViaje" name="fechaViaje" value="${dateNow}" min="${dateNow}" required>
                                    <div class="invalid-feedback">
                                        Por favor seleccione la fecha de la Ruta
                                    </div>
                                </div>

                                <!-- Selección de Vehículo -->
                                <div class="mb-3">
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
                                <div class="mb-3">
                                    <label for="vehiculo" class="form-label">Conductor</label>
                                    <select class="form-select" id="conductor" name="conductor" required>
                                        <option value="">Seleccione un conductor</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        Por favor seleccione un vehículo
                                    </div>
                                </div> 


                                <!-- Selección de Producto -->
                                <div class="mb-3">
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

                                    <div class="mb-3"><div id="geomap"></div></div>

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

        <script>
            var geocoder;
            var map;
            var marker;

            /*
             * Google Map with marker
             */
            function initialize() {
                var initialLat = $('.search_latitude').val();
                var initialLong = $('.search_longitude').val();
                var ratio = $("#ratio").val() * 1609.344;
                initialLat = initialLat ? initialLat : 13.7071242;
                initialLong = initialLong ? initialLong : -89.2041834;

                var latlng = new google.maps.LatLng(initialLat, initialLong);
                var options = {
                    zoom: 13,
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                map = new google.maps.Map(document.getElementById("geomap"), options);

                geocoder = new google.maps.Geocoder();

                marker = new google.maps.Marker({
                    map: map,
                    draggable: true,
                    position: latlng
                });

                //Circle
                var cityCircle = new google.maps.Circle({
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: '#FF0000',
                    fillOpacity: 0.35,
                    map: map,
                    center: latlng,
                    radius: ratio,
                    draggable: false,
                    editable: true
                });

                google.maps.event.addListener(marker, "dragend", function () {
                    var point = marker.getPosition();
                    map.panTo(point);
                    geocoder.geocode({'latLng': marker.getPosition()}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            map.setCenter(results[0].geometry.location);
                            marker.setPosition(results[0].geometry.location);
                            console.log("initialize");
                            //$('.search_addr').text(results[0].formatted_address);
                            $('.search_location').val(results[0].formatted_address);
                            $('.search_latitude').val(marker.getPosition().lat());
                            $('.search_longitude').val(marker.getPosition().lng());
                        }
                    });

                    cityCircle.setMap(null);

                    ratio = $("#ratio").val() * 1609.344;

                    lat_lng = {lat: marker.getPosition().lat(), lng: marker.getPosition().lng()};

                    cityCircle = new google.maps.Circle({
                        strokeColor: '#FF0000',
                        strokeOpacity: 0.8,
                        strokeWeight: 2,
                        fillColor: '#FF0000',
                        fillOpacity: 0.35,
                        map: map,
                        center: lat_lng,
                        radius: ratio,
                        draggable: false,
                        editable: true
                    });

                    google.maps.event.addListener(cityCircle, 'radius_changed', function () {
                        console.log(cityCircle.getRadius());
                        var ratio = Number((cityCircle.getRadius() / 1609.344).toFixed(2));
                        $("#ratio").val(ratio);
                    });
                    google.maps.event.addListener(cityCircle, 'center_changed', function () {
                        //console.log(cityCircle.getCenter());
                        var latLng = cityCircle.getCenter().toLocaleString();
                        latlng = new google.maps.LatLng(latLng);
                        marker.setPosition(latlng);
                        console.log(latLng);
                    });


                    //cityCircle.setMap(map);
                });

                google.maps.event.addListener(cityCircle, 'radius_changed', function () {
                    console.log(cityCircle.getRadius());
                    var ratio = Number((cityCircle.getRadius() / 1609.344).toFixed(2));
                    $("#ratio").val(ratio);
                });
                google.maps.event.addListener(cityCircle, 'center_changed', function () {
                    //console.log(cityCircle.getCenter());
                    var latLng = cityCircle.getCenter().toLocaleString();
                    latlng = new google.maps.LatLng(latLng);
                    marker.setPosition(latlng);
                    console.log(latLng);
                });


            }


            /*  autocomplete location search */
            $(document).ready(function () {
                initialize();//load google map
                var PostCodeid = '#search_location';
                $(function () {
                    $(PostCodeid).autocomplete({
                        source: function (request, response) {
                            geocoder.geocode({
                                'address': request.term
                            }, function (results, status) {
                                response($.map(results, function (item) {
                                    return {
                                        label: item.formatted_address,
                                        value: item.formatted_address,
                                        lat: item.geometry.location.lat(),
                                        lon: item.geometry.location.lng()
                                    };
                                }));
                            });
                        },
                        select: function (event, ui) {
                            console.log("search_location");
                            console.log(ui.item.value);
                            $('.search_addr').val(ui.item.value);
                            $('.search_location').val(ui.item.value);
                            $('.search_latitude').val(ui.item.lat);
                            $('.search_longitude').val(ui.item.lon);
                            var latlng = new google.maps.LatLng(ui.item.lat, ui.item.lon);
                            marker.setPosition(latlng);
                            initialize();

                        }
                    });
                });

                /* Point location on google map */
                $('.get_map').click(function (e) {
                    var address = $(PostCodeid).val();
                    geocoder.geocode({'address': address}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            map.setCenter(results[0].geometry.location);
                            marker.setPosition(results[0].geometry.location);
                            console.log("get_map");
                            $('.search_addr').val(results[0].formatted_address);
                            $('.search_location').val(results[0].formatted_address);
                            $('.search_latitude').val(marker.getPosition().lat());
                            $('.search_longitude').val(marker.getPosition().lng());
                            $('#ratio').focus();
                        } else {
                            alert("Geocode was not successful for the following reason: " + status);
                        }
                    });
                    e.preventDefault();
                });

                //Add listener to marker for reverse geocoding
                google.maps.event.addListener(marker, 'drag', function () {
                    geocoder.geocode({'latLng': marker.getPosition()}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            if (results[0]) {
                                console.log("drag");
                                $('.search_addr').val(results[0].formatted_address);
                                $('.search_location').val(results[0].formatted_address);
                                $('.search_latitude').val(marker.getPosition().lat());
                                $('.search_longitude').val(marker.getPosition().lng());
                            }
                        }
                    });
                });

            });



            /*  autocomplete location search */
            $(document).ready(function () {
                initialize();//load google map
                var PostCodeid = '#search_location_destino';
                $(function () {
                    $(PostCodeid).autocomplete({
                        source: function (request, response) {
                            geocoder.geocode({
                                'address': request.term
                            }, function (results, status) {
                                response($.map(results, function (item) {
                                    return {
                                        label: item.formatted_address,
                                        value: item.formatted_address,
                                        lat: item.geometry.location.lat(),
                                        lon: item.geometry.location.lng()
                                    };
                                }));
                            });
                        },
                        select: function (event, ui) {
                            console.log("search_location_destino");
                            console.log(ui.item.value);
                            $('.search_addr_destino').val(ui.item.value);
                            $('.search_location_destino').val(ui.item.value);
                            $('.search_latitude_destino').val(ui.item.lat);
                            $('.search_longitude_destino').val(ui.item.lon);
                            var latlng = new google.maps.LatLng(ui.item.lat, ui.item.lon);
                            marker.setPosition(latlng);
                            initialize();

                        }
                    });
                });

                /* Point location on google map */
                $('.get_map').click(function (e) {
                    var address = $(PostCodeid).val();
                    geocoder.geocode({'address': address}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            map.setCenter(results[0].geometry.location);
                            marker.setPosition(results[0].geometry.location);
                            console.log("get_map");
                            $('.search_addr_destino').val(results[0].formatted_address);
                            $('.search_location_destino').val(results[0].formatted_address);
                            $('.search_latitude_destino').val(marker.getPosition().lat());
                            $('.search_longitude_destino').val(marker.getPosition().lng());
                            $('#ratio').focus();
                        } else {
                            alert("Geocode was not successful for the following reason: " + status);
                        }
                    });
                    e.preventDefault();
                });

                //Add listener to marker for reverse geocoding
                google.maps.event.addListener(marker, 'drag', function () {
                    geocoder.geocode({'latLng': marker.getPosition()}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            if (results[0]) {
                                console.log("drag");
                                $('.search_addr_destino').val(results[0].formatted_address);
                                $('.search_location_destino').val(results[0].formatted_address);
                                $('.search_latitude_destino').val(marker.getPosition().lat());
                                $('.search_longitude_destino').val(marker.getPosition().lng());
                            }
                        }
                    });
                });

            });

        </script>

        <script>
            // Define las coordenadas de origen y destino
            const origin = "9.9281,-84.0907";      // Coordenadas de San José, Costa Rica
            const destination = "10.0049,-84.1193"; // Coordenadas de Alajuela, Costa Rica
            const apiKey = "AIzaSyDhAeDIVSpivfE9V8DKA1wLk5Jl3i6FaSk";            // Reemplaza con tu API Key

            // Construye la URL de la solicitud
            const url = "https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=" + apiKey;

            // Realiza la solicitud AJAX usando jQuery
            $.ajax({
                url: url,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    if (data.status === "OK") {
                        const element = data.rows[0].elements[0];
                        if (element.status === "OK") {
                            const distance = element.distance.text; // Distancia en formato legible
                            const duration = element.duration.text; // Duración del viaje
                            console.log(`Distancia: ${distance}`);
                            console.log(`Duración: ${duration}`);
                        } else {
                            console.log("No se pudo obtener la distancia.");
                        }
                    } else {
                        console.log("Error en la respuesta de la API:", data.status);
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Error en la solicitud AJAX:", error);
                }
            });
        </script>

        <script>
            $(document).ready(function () {
                $.ajax({
                    url: 'https://infoavance.com/FollowCargo/producto?action=list', // Cambia esto por la ruta a tu backend
                    type: 'POST', // O 'POST' si el servidor lo requiere
                    dataType: 'json', // El tipo de dato que esperas del servidor
                    crossDomain: true, // Habilitar CORS
                    success: function (response) {
                        console.log(response);
                        // Limpiamos el select antes de llenarlo
                        $('#producto').empty();
                        $('#producto').append('<option value="">Seleccione una opción</option>');

                        // Iteramos sobre los datos y agregamos opciones al select
                        $.each(response, function (index, item) {
                            console.log(item);
                            console.log(item.id);
                            const producto = item.nombre + " - " + item.descripcion + " | " + item.peso + " " + item.unidadMedida
                            $('#producto').append("<option value=" + item.id + ">" + producto + "</option>");
                        });
                    },
                    error: function (xhr, status, error) {
                        console.error("Error al cargar los datos: " + error);
                    }
                });

                $.ajax({
                    url: 'https://infoavance.com/FollowCargo/vehiculo?action=list', // Cambia esto por la ruta a tu backend
                    type: 'POST', // O 'POST' si el servidor lo requiere
                    dataType: 'json', // El tipo de dato que esperas del servidor
                    crossDomain: true, // Habilitar CORS
                    success: function (response) {
                        console.log(response);
                        // Limpiamos el select antes de llenarlo
                        $('#vehiculo').empty();
                        $('#vehiculo').append('<option value="">Seleccione una opción</option>');

                        // Iteramos sobre los datos y agregamos opciones al select
                        $.each(response, function (index, item) {
                            const vehiculo = item.tipo + " - " + item.marca + " " + item.modelo + " " + item.placa
                            $('#vehiculo').append("<option value=" + item.id + " data-recargo=" + item.costoFijoViaje+">" + vehiculo + "</option>");
                        });
                    },
                    error: function (xhr, status, error) {
                        console.error("Error al cargar los datos: " + error);
                    }
                });
                
                $.ajax({
                    url: 'https://infoavance.com/FollowCargo/conductores?action=list', // Cambia esto por la ruta a tu backend
                    type: 'POST', // O 'POST' si el servidor lo requiere
                    dataType: 'json', // El tipo de dato que esperas del servidor
                    crossDomain: true, // Habilitar CORS
                    success: function (response) {
                        console.log(response);
                        // Limpiamos el select antes de llenarlo
                        $('#conductor').empty();
                        $('#conductor').append('<option value="">Seleccione una opción</option>');

                        // Iteramos sobre los datos y agregamos opciones al select
                        $.each(response, function (index, item) {
                            const conductor = item.nombres + " " + item.apellidos + " | " + item.email
                            $('#conductor').append("<option value=" + item.id + ">" + conductor + "</option>");
                        });
                    },
                    error: function (xhr, status, error) {
                        console.error("Error al cargar los datos: " + error);
                    }
                });
            });
        </script>
    </body>
</html>