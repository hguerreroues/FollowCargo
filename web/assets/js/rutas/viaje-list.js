// Variable global para mantener el ID del viaje seleccionado
let selectedTripId = null;
let estadoModal = null;

document.addEventListener('DOMContentLoaded', function() {
    // Inicializar el modal de estado
    estadoModal = new bootstrap.Modal(document.getElementById('estadoModal'));

    // Cargar viajes al iniciar
    loadTrips();

    // Event listener para el botón de guardar estado
    document.getElementById('guardarEstado').addEventListener('click', function() {
        if (selectedTripId !== null) {
            const nuevoEstado = document.getElementById('nuevoEstado').value;
            actualizarEstado(selectedTripId, nuevoEstado);
            estadoModal.hide();
        } else {
            console.error("No hay viaje seleccionado");
        }
    });

    // Agregar event listener para cuando el modal se cierre
    document.getElementById('estadoModal').addEventListener('hidden.bs.modal', function () {
        selectedTripId = null;
    });
});

function formatDate(dateString) {
    if (!dateString) return '';
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('es-ES', options);
}

function getVehiculoNombre(id) {
    const vehiculos = {
        1: 'Placa: ABC-123 - Toyota Hilux 2023 - Tipo: Liviano',
        2: 'Placa: DEF-456 - Nissan NP300 2022 - Tipo: Particular',
        3: 'Placa: GHI-789 - Volvo FH16 2023 - Tipo: Pesado'
    };
    return vehiculos[id] || 'Desconocido';
}

function getProductoNombre(id) {
    const productos = {
        1: 'Producto: Cajas de cartón - Peso: 500kg',
        2: 'Producto: Contenedor refrigerado - Peso: 1000kg',
        3: 'Producto: Materiales de construcción - Peso: 2000kg'
    };
    return productos[id] || 'Desconocido';
}

function getEstadoClass(estado) {
    const clases = {
        'Pendiente': 'bg-warning',
        'En Ruta': 'bg-primary',
        'Interrumpido': 'bg-danger',
        'Finalizado': 'bg-success'
    };
    return clases[estado] || 'bg-secondary';
}

function loadTrips() {
    const trips = JSON.parse(localStorage.getItem('trips') || '[]');
    const tableBody = document.getElementById('tripsTableBody');
    const noRecordsMessage = document.getElementById('noRecordsMessage');
    const tripsTableContainer = document.getElementById('tripsTableContainer');
    
    if (trips.length === 0) {
        // No hay registros, mostrar mensaje y ocultar tabla
        if (noRecordsMessage) noRecordsMessage.style.display = 'block';
        if (tripsTableContainer) tripsTableContainer.style.display = 'none';
        return;
    }
    
    // Hay registros, ocultar mensaje y mostrar tabla
    if (noRecordsMessage) noRecordsMessage.style.display = 'none';
    if (tripsTableContainer) tripsTableContainer.style.display = 'block';
    
    if (tableBody) {
        tableBody.innerHTML = trips.map(trip => `
            <tr>
                <td>${formatDate(trip.fechaViaje)}</td>
                <td>${getVehiculoNombre(trip.vehiculoId)}</td>
                <td>${trip.origen}</td>
                <td>${trip.destino}</td>
                <td>$${trip.costoTotal ? trip.costoTotal.toFixed(2) : '0.00'}</td>
                <td>
                    <span class="badge ${getEstadoClass(trip.estado)} estado-badge"
                          data-trip-id="${trip.id}"
                          data-estado="${trip.estado}"
                          style="cursor: pointer;">
                        ${trip.estado}
                    </span>
                </td>
                <td>
                    <div class="btn-group" role="group">
                        <a href="/FollowCargo/crear-viaje?id=${trip.id}" class="btn btn-sm btn-warning me-2">
                            Editar
                            <i class="ti ti-edit"></i>
                        </a>
                        <button class="btn btn-sm btn-info" onclick="verDetalles(${trip.id})">
                            Ver Detalles
                            <i class="ti ti-eye"></i>
                        </button>
                    </div>
                </td>
            </tr>
        `).join('');

        // Agregar event listeners a los badges de estado
        document.querySelectorAll('.estado-badge').forEach(badge => {
            badge.addEventListener('click', function() {
                const tripId = parseInt(this.dataset.tripId);
                const estadoActual = this.dataset.estado;
                mostrarModalEstado(tripId, estadoActual);
            });
        });
    }
}

function mostrarModalEstado(tripId, estadoActual) {
    // Asignar el ID del viaje seleccionado
    selectedTripId = tripId;
    
    // Configurar el select con el estado actual
    const selectEstado = document.getElementById('nuevoEstado');
    if (selectEstado) {
        selectEstado.value = estadoActual;
    }
    
    // Mostrar el modal
    if (estadoModal) {
        estadoModal.show();
    } else {
        // Si el modal no está inicializado, inicializarlo y mostrarlo
        estadoModal = new bootstrap.Modal(document.getElementById('estadoModal'));
        estadoModal.show();
    }
}

function actualizarEstado(tripId, nuevoEstado) {
    
    const trips = JSON.parse(localStorage.getItem('trips') || '[]');
    const index = trips.findIndex(t => t.id === tripId);
    
    if (index !== -1) {
        trips[index].estado = nuevoEstado;
        localStorage.setItem('trips', JSON.stringify(trips));
        loadTrips();
    } else {
        console.error('No se encontró la ruta con ID:', tripId);
    }
}

function verDetalles(tripId) {
    const trips = JSON.parse(localStorage.getItem('trips') || '[]');
    const trip = trips.find(t => t.id === tripId);
    
    if (trip) {
        const modalBody = document.querySelector('#detalleModal .modal-body');
        modalBody.innerHTML = `
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Fecha:</strong> ${formatDate(trip.fechaViaje)}</p>
                        <p><strong>Vehículo:</strong> ${getVehiculoNombre(trip.vehiculoId)}</p>
                        <p><strong>Producto:</strong> ${getProductoNombre(trip.productoId)}</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Origen:</strong> ${trip.origen}</p>
                        <p><strong>Destino:</strong> ${trip.destino}</p>
                        <p><strong>Distancia:</strong> ${trip.distancia} km</p>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-4">
                        <p><strong>Costo por distancia:</strong> $${trip.costoPorKm.toFixed(2)}</p>
                    </div>
                    <div class="col-md-4">
                        <p><strong>Recargo:</strong> $${trip.recargo.toFixed(2)}</p>
                    </div>
                    <div class="col-md-4">
                        <p><strong>Costo total:</strong> $${trip.costoTotal.toFixed(2)}</p>
                    </div>
                </div>
            </div>
        `;
        
        const detalleModal = new bootstrap.Modal(document.getElementById('detalleModal'));
        detalleModal.show();
    }
}