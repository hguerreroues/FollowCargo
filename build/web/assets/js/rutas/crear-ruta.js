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
    initialize(); //load google map
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
    initialize(); //load google map
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


// Define las coordenadas de origen y destino
const origin = "9.9281,-84.0907";      // Coordenadas de San José, Costa Rica
const destination = "10.0049,-84.1193"; // Coordenadas de Alajuela, Costa Rica
const apiKey = "AIzaSyDhAeDIVSpivfE9V8DKA1wLk5Jl3i6FaSk";            // Reemplaza con tu API Key

// Construye la URL de la solicitud
const url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=" + apiKey;

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

$(document).ready(function () {
    $.ajax({
        url: 'https://infoavance.com/FollowCargo/producto?action=list', // Cambia esto por la ruta a tu backend
        type: 'POST', // O 'POST' si el servidor lo requiere
        dataType: 'json', // El tipo de dato que esperas del servidor
        crossDomain: true, // Habilitar CORS
        success: function (response) {
            //console.log(response);
            // Limpiamos el select antes de llenarlo
            $('#producto').empty();
            $('#producto').append('<option value="">Seleccione una opción</option>');
            // Iteramos sobre los datos y agregamos opciones al select
            $.each(response, function (index, item) {
                //console.log(item);
                //console.log(item.id);
                const producto = item.nombre + " - " + item.descripcion + " | " + item.peso + " " + item.unidadMedida
                $('#producto').append("<option value=" + item.id + " data-precio=" + item.precio + " data-peso=" + item.peso + ">" + producto + "</option>");
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
            //console.log(response);
            // Limpiamos el select antes de llenarlo
            $('#vehiculo').empty();
            $('#vehiculo').append('<option value="">Seleccione una opción</option>');
            // Iteramos sobre los datos y agregamos opciones al select
            $.each(response, function (index, item) {
                const vehiculo = item.tipo + " - " + item.marca + " " + item.modelo + " " + item.placa
                $('#vehiculo').append("<option value=" + item.id + " data-recargo=" + item.costoFijoViaje + " data-conductor=" + item.idConductor + " data-tarifapeso=" + item.tarifaPeso + " data-tarifakm=" + item.tarifaKm + ">" + vehiculo + "</option>");
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
            //console.log(response);
            // Limpiamos el select antes de llenarlo
            $('#conductor').empty();
            $('#conductor').append('<option value="0">Seleccione una opción</option>');
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