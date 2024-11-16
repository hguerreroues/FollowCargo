document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('tripForm');
    const distanciaInput = document.getElementById('distancia');
    const vehiculoSelect = document.getElementById('vehiculo');
    const submitBtnText = document.getElementById('submitBtnText');
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
    if (distanciaInput && vehiculoSelect) {
        distanciaInput.addEventListener('input', actualizarResumenCosto);
        vehiculoSelect.addEventListener('change', actualizarResumenCosto);
    }

    if (form) {
        form.addEventListener('submit', function(event) {
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
    const distancia = parseFloat(document.getElementById('distancia').value) || 0;
    const vehiculoOption = document.getElementById('vehiculo').selectedOptions[0];
    const recargo = vehiculoOption ? parseFloat(vehiculoOption.dataset.recargo) || 0 : 0;
    
    const costoPorKm = distancia * 0.44;
    const costoTotal = costoPorKm + recargo;

    const costoDistanciaEl = document.getElementById('costoDistancia');
    const costoRecargoEl = document.getElementById('costoRecargo');
    const costoTotalEl = document.getElementById('costoTotal');

    if (costoDistanciaEl) costoDistanciaEl.textContent = `$${costoPorKm.toFixed(2)}`;
    if (costoRecargoEl) costoRecargoEl.textContent = `$${recargo.toFixed(2)}`;
    if (costoTotalEl) costoTotalEl.textContent = `$${costoTotal.toFixed(2)}`;

    return {
        costoPorKm,
        recargo,
        costoTotal
    };
}

function cargarDatosViaje(id) {
    const trips = JSON.parse(localStorage.getItem('trips') || '[]');
    const trip = trips.find(t => t.id === parseInt(id));
    
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