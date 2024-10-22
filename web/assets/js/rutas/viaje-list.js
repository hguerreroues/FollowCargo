document.addEventListener('DOMContentLoaded', function() {
    loadTrips();
});

function loadTrips() {
    const trips = JSON.parse(localStorage.getItem('trips') || '[]');
    const tableBody = document.getElementById('tripsTableBody');
    
    tableBody.innerHTML = trips.map(trip => `
        <tr>
            <td>${trip.id}</td>
            <td>${getVehiculoNombre(trip.vehiculoId)}</td>
            <td>${getProductoNombre(trip.productoId)}</td>
            <td>${trip.origen}</td>
            <td>${trip.destino}</td>
            <td>${trip.distancia} km</td>
            <td><span class="badge bg-warning">${trip.estado}</span></td>
            <td>
                <button class="btn btn-sm btn-info" onclick="verDetalles(${trip.id})">
                    <i class="ti ti-eye"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

function getVehiculoNombre(id) {
    const vehiculos = {
        1: 'Camión ABC123',
        2: 'Camión DEF456',
        3: 'Camión GHI789'
    };
    return vehiculos[id] || 'Desconocido';
}

function getProductoNombre(id) {
    const productos = {
        1: 'Cajas de cartón',
        2: 'Contenedor refrigerado',
        3: 'Materiales de construcción'
    };
    return productos[id] || 'Desconocido';
}

function verDetalles(id) {
    // Implementar vista de detalles
    console.log('Ver detalles del viaje:', id);
}