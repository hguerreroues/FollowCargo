document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('vehiculoForm');
    const formTitle = document.getElementById('formTitle');
    const submitBtnText = document.getElementById('submitBtnText');
    const conductorSelect = document.getElementById('conductor');
    let editMode = false;

// Verificar el modo (edición o creación)
    const urlParams = new URLSearchParams(window.location.search);
    const vehiculoId = urlParams.get('id');
    
    if (vehiculoId && vehiculoId !== 'add') {
        editMode = false;
        formTitle.textContent = 'Ver detalle de Vehiculo';
        submitBtnText.textContent = 'Actualizar';
        // Ocultar el botón de guardar cuando se ve el detalle
        const submitBtn = document.getElementById('submitBtn'); // Asumiendo que tu botón de guardar tiene el id 'submitBtn'
        submitBtn.style.display = 'none'; // Esto ocultará el botón
        
        cargarDatosVehiculo(vehiculoId);
    } else {
        formTitle.textContent = 'Nuevo Vehiculo';
        submitBtnText.textContent = 'Crear';
    }

   // Manejar envío del formulario
    if (form) {
        form.addEventListener('submit', async function(event) {
            event.preventDefault();
            
            if (!form.checkValidity()) {
                event.stopPropagation();
                form.classList.add('was-validated');
                return;
            }

            // Deshabilitar el botón de submit mientras se procesa
            const submitBtn = document.getElementById('submitBtn');
            submitBtn.disabled = true;
            
            // Preparar los datos del vehiculo
            const tipoElement = document.getElementById('tipo');
            const vehiculoData = {
                marca: document.getElementById('marca').value.trim(),
                modelo: document.getElementById('modelo').value.trim(),
                tipo: tipoElement.options[tipoElement.selectedIndex].text, // Obtener el texto en lugar del valor numérico
                placa: document.getElementById('placa').value.trim(),
                usuario: 'admin', // Asegúrate de usar el valor correcto aquí
                idConductor: document.getElementById('conductor').value, // Asegúrate de que el idConductor sea el ID correcto
                costoFijoViaje: document.getElementById('costo').value
            };
            
            try {
                const response = await fetch('https://infoavance.com/FollowCargo/vehiculo', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        action: 'add',
                        //action: editMode ? 'update' : 'add',  // Cambia a 'update' si estás editando
                        tipo: vehiculoData.tipo,
                        marca: vehiculoData.marca,
                        modelo: vehiculoData.modelo,
                        placa: vehiculoData.placa,
                        usuario: vehiculoData.usuario,
                        idConductor: vehiculoData.idConductor,
                        costoFijoViaje: vehiculoData.costoFijoViaje
                    })
                });


                if (response.ok) {
                    mostrarAlerta('Vehículo guardado exitosamente', 'success');
                    setTimeout(() => {
                        window.location.href = '/FollowCargo/vehiculos';
                    }, 1500);
                } else {
                    throw new Error('Error al guardar los datos');
                }
            } catch (error) {
                console.error('Error:', error);
                mostrarAlerta('Error al guardar los datos del vehículo', 'danger');
                submitBtn.disabled = false;
            }
        });
    }
});

// Función para cargar la lista de conductores desde la API
async function cargarListaConductores() {
    try {
        const response = await fetch('https://infoavance.com/FollowCargo/conductores');
        if (!response.ok) {
            throw new Error('Error al cargar la lista de conductores');
        }
        
        const conductores = await response.json();

        // Poblar el select de conductores
        const conductorSelect = document.getElementById('conductor');
        conductores.forEach(conductor => {
            const option = document.createElement('option');
            option.value = conductor.id; // Usar el id para el envío
            option.textContent = conductor.nombres+' '+conductor.apellidos; // Mostrar el nombre
            conductorSelect.appendChild(option);
        });
        
    } catch (error) {
        console.error('Error:', error);
        mostrarAlerta('Error al cargar la lista de conductores', 'danger');
    }
}

        // Función para cargar los datos del vehículo en modo edición
        async function cargarDatosVehiculo(id) {
            try {
        const response = await fetch(`https://infoavance.com/FollowCargo/vehiculo?action=get&id=${id}`, {
            method: 'POST'
        });
        if (!response.ok) {
            throw new Error('Error al cargar los datos del conductor');
        }
        
        const vehiculo = await response.json();
        
        document.getElementById('marca').value = vehiculo.marca;
        document.getElementById('modelo').value = vehiculo.modelo;
        document.getElementById('tipo').value = vehiculo.tipo;
        document.getElementById('placa').value = vehiculo.placa;
        document.getElementById('costo').value = vehiculo.costoFijoViaje;
        document.getElementById('conductor').value = vehiculo.idConductor; // Seleccionar el conductor correspondiente
        
        // Llenar el campo "Tipo" con el valor correspondiente
        const tipoSelect = document.getElementById('tipo');
        tipoSelect.value = vehiculo.tipo === "Regular" ? "0" : "1"; // Asignar el valor correspondiente
        
    } catch (error) {
        console.error('Error:', error);
        mostrarAlerta('Error al cargar los datos del conductor', 'danger');
    }
}
cargarListaConductores()
    .then(() => {
        // Una vez que se carguen los conductores, cargar los datos del vehículo
        cargarDatosVehiculo(idVehiculo); // Reemplaza idVehiculo con el ID del vehículo
    })
    .catch(error => {
        console.error('Error:', error);
    });
    
function mostrarAlerta(mensaje, tipo) {
    const alertaDiv = document.createElement('div');
    alertaDiv.className = `alert alert-${tipo} alert-dismissible fade show`;
    alertaDiv.role = 'alert';
    alertaDiv.innerHTML = `
        ${mensaje}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;

    const form = document.getElementById('vehiculoForm');
    form.parentNode.insertBefore(alertaDiv, form);

    setTimeout(() => {
        alertaDiv.remove();
    }, 5000);
}
