document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('tripForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        
        if (!form.checkValidity()) {
            event.stopPropagation();
            form.classList.add('was-validated');
            return;
        }

        // Recopilar datos del formulario
        const tripData = {
            vehiculoId: document.getElementById('vehiculo').value,
            productoId: document.getElementById('producto').value,
            origen: document.getElementById('origen').value,
            destino: document.getElementById('destino').value,
            distancia: document.getElementById('distancia').value
        };

        // Simulación de guardado (posteriormente se conectará con el backend)
        console.log('Datos del viaje:', tripData);
        
        // Guardar en localStorage para demostración
        const trips = JSON.parse(localStorage.getItem('trips') || '[]');
        trips.push({
            id: trips.length + 1,
            ...tripData,
            estado: 'Pendiente'
        });
        localStorage.setItem('trips', JSON.stringify(trips));

        // Redireccionar a la lista de viajes
        window.location.href = '/FollowCargo/viajes';
    });
});