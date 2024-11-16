document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('tripForm');
    const distanciaInput = document.getElementById('distancia');
    const vehiculoSelect = document.getElementById('vehiculo');
    const productoSelect = document.getElementById('producto');
    const submitBtnText = document.getElementById('submitBtnText');
    const btnSubmit = document.getElementById('btnSubmit');
    let editMode = false;

    // Verificar si estamos en modo edición
    const urlParams = new URLSearchParams(window.location.search);
    const tripId = urlParams.get('id');

    if (tripId) {
        editMode = true;
        cargarDatosViaje(tripId);
        if (submitBtnText) {
            submitBtnText.textContent = 'Actualizar Ruta';
        }
    }

    // Calcular costo en tiempo real
    if (distanciaInput && vehiculoSelect && productoSelect) {
        distanciaInput.addEventListener('input', actualizarResumenCosto);
        vehiculoSelect.addEventListener('change', actualizarResumenCosto);
        productoSelect.addEventListener('change', actualizarResumenCosto);

        productoSelect.addEventListener('change', function () {
            document.getElementById("btnAgregarProducto").disabled = false;
        });

    }



    if (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault();

            if (!form.checkValidity()) {
                event.stopPropagation();
                form.classList.add('was-validated');
                return;
            }

            const tripData = {
                fechaViaje: document.getElementById('fechaViaje').value,
                vehiculoId: vehiculoSelect.value,
                productoId: document.getElementById('producto').value,
                origen: document.getElementById('origen').value,
                destino: document.getElementById('destino').value,
                distancia: parseFloat(distanciaInput.value),
                ...actualizarResumenCosto()
            };

            const trips = JSON.parse(localStorage.getItem('trips') || '[]');

            if (editMode) {
                const index = trips.findIndex(t => t.id === parseInt(tripId));
                if (index !== -1) {
                    trips[index] = {
                        ...trips[index],
                        ...tripData
                    };
                }
            } else {
                trips.push({
                    id: trips.length + 1,
                    ...tripData,
                    estado: 'Pendiente'
                });
            }

            localStorage.setItem('trips', JSON.stringify(trips));
            window.location.href = '/FollowCargo/viajes';
        });
    }
});

function actualizarResumenCosto() {

    const btnSubmit = document.getElementById('btnSubmit');

    const distancia = parseFloat(document.getElementById('distancia').value) || 0;
    const vehiculoOption = document.getElementById('vehiculo').selectedOptions[0];
    const productoOption = document.getElementById('producto').selectedOptions[0];
    const recargo = vehiculoOption ? parseFloat(vehiculoOption.dataset.recargo) || 0 : 0;
    const tarifaPeso = vehiculoOption ? parseFloat(vehiculoOption.dataset.tarifapeso) || 0.15 : 0.15;
    const tarifaKm = vehiculoOption ? parseFloat(vehiculoOption.dataset.tarifakm) || 1 : 1;

    const selectConductor = document.getElementById("conductor");
    const selectProducto = document.getElementById("producto");

    const costoProducto = parseFloat(document.getElementById("costoProducto").value) || 0;
    const cantidadProducto = parseFloat(document.getElementById("cantidad").value) || 0;
    const opcionExiste = Array.from(selectConductor.options).some(option => option.value === vehiculoOption.dataset.conductor);

    if (opcionExiste) {
        selectConductor.value = vehiculoOption.dataset.conductor;
    } else {
        selectConductor.value = "0";
        selectConductor.disabled;
    }

    document.getElementById("idProducto").value = productoOption.value;
    document.getElementById("nombreProducto").value = productoOption.textContent;
    document.getElementById("precio").value = cantidadProducto * parseFloat(productoOption.dataset.precio) || 0;
    document.getElementById("pesoProducto").value = parseFloat(productoOption.dataset.peso) || 0;

    //const pesoProducto = parseFloat(document.getElementById("pesoProducto").value) || 0;
    const pesoProducto = parseFloat(document.getElementById("pesoSumaProducto").value) || 0;

    //const pesoTotalProducto = parseFloat(document.getElementById("pesoTotalProduto").value) || 0;

    const costoPorPeso = pesoProducto * tarifaPeso;

    const costoPorKm = distancia * tarifaKm;
    const costoTotal = costoPorPeso + costoPorKm + recargo;

    const costoDistanciaEl = document.getElementById('costoDistancia');
    const costoRecargoEl = document.getElementById('costoRecargo');
    const costoTotalEl = document.getElementById('costoTotal');
    const costoPesoEl = document.getElementById('costoPeso');


        const costoTotalInput = document.getElementById('costoTotalInput');
        costoTotalInput.value = costoTotal.toFixed(2);


    if (costoDistanciaEl)
        costoDistanciaEl.textContent = `$${costoPorKm.toFixed(2)}`;

    if (costoRecargoEl)
        costoRecargoEl.textContent = `$${recargo.toFixed(2)}`;

    if (costoTotalEl)
        costoTotalEl.textContent = `$${costoTotal.toFixed(2)}`;

    if (costoPesoEl)
        costoPesoEl.textContent = `$${costoPorPeso.toFixed(2)}`;


    if (costoDistanciaEl && costoRecargoEl && costoTotalEl && costoPesoEl) {
        const isValid = [costoDistanciaEl.textContent, costoRecargoEl.textContent, costoTotalEl.textContent, costoPesoEl.textContent].every(value => value.trim() !== "" && value !== "$0.00");

        if (isValid) {
            console.log("Todos los campos tienen contenido.");

            btnSubmit.disabled = false;
        } else {
            console.log("Uno o más campos están vacíos.");
            btnSubmit.disabled = true;
        }
    } else {
        console.error("Uno o más elementos no se encontraron en el DOM.");
    }

    return {
        costoPorKm,
        recargo,
        costoTotal
    };
}

function cargarDatosViaje(id) {
    const trips = JSON.parse(localStorage.getItem('trips') || '[]');
    const trip = trips.find(t => t.id === parseInt(id));
    console.log(trips);

    if (trip) {
        document.getElementById('tripId').value = trip.id;
        document.getElementById('fechaViaje').value = trip.fechaViaje;
        document.getElementById('vehiculo').value = trip.vehiculoId;
        document.getElementById('producto').value = trip.productoId;
        document.getElementById('origen').value = trip.origen;
        document.getElementById('destino').value = trip.distancia;
        document.getElementById('distancia').value = trip.distancia;
        actualizarResumenCosto();
    }
}

document.getElementById('cantidad').addEventListener('input', function () {
    const productoOption = document.getElementById('producto').selectedOptions[0];
    const precioUnitario = parseFloat(productoOption.dataset.precio);
    const pesoProducto = parseFloat(productoOption.dataset.peso);

    const cantidad = parseInt(document.getElementById('cantidad').value);
    const precioTotal = cantidad * precioUnitario;
    const pesoTotal = cantidad * pesoProducto;

    document.getElementById('precio').value = `${precioTotal.toFixed(2)}`;

    actualizarResumenCosto();
});


function addItem() {
    var idProducto = $("#idProducto").val();
    var cantidad = $("#cantidad").val();
    var precio = $("#precio").val();
    var peso = $("#pesoProducto").val();
    var nombreProducto = $("#nombreProducto").val()
    $.ajax({
        url: "rutas",
        type: "POST",
        data: {
            action: "addItem",
            idProducto: idProducto,
            cantidad: cantidad,
            precio: precio,
            peso: peso,
            nombre: nombreProducto
        },
        success: function (result) {
            $("#detalleProductos").html(result);
            const pesoTotalProducto = parseFloat(document.getElementById("pesoTotalProducto").value) || 0;
            document.getElementById("pesoSumaProducto").value = pesoTotalProducto;
            console.log(pesoTotalProducto);
            actualizarResumenCosto();
        }
    });

}

function deleteItem(idItem) {
    //var item = $("#item").val();
    $.ajax({
        type: "POST",
        data: {
            item: idItem,
            action: "deleteItem"
        },
        url: "rutas",
        success: function (result) {
            $("#detalleProductos").html(result);
            const pesoTotalProducto = parseFloat(document.getElementById("pesoTotalProducto").value) || 0;
            document.getElementById("pesoSumaProducto").value = pesoTotalProducto;
            console.log(pesoTotalProducto);
            actualizarResumenCosto();
        }
    });

}

function cleanItems() {
    document.getElementById("cantidad").value = "1";

}