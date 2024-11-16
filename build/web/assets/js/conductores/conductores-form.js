document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('conductorForm');
    const formTitle = document.getElementById('formTitle');
    const submitBtnText = document.getElementById('submitBtnText');
    let editMode = false;

    // Verificar el modo (edición o creación)
    const urlParams = new URLSearchParams(window.location.search);
    const conductorId = urlParams.get('id');
    
    if (conductorId && conductorId !== 'add') {
        editMode = true;
        formTitle.textContent = 'Editar Conductor';
        submitBtnText.textContent = 'Actualizar';
        cargarDatosConductor(conductorId);
        submitBtn.style.display = 'none';
    } else {
        formTitle.textContent = 'Nuevo Conductor';
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

            // Validaciones adicionales antes de enviar
            const telefono = document.getElementById('telefono').value;
            const licencia = document.getElementById('licencia').value;
            
            if (telefono.length !== 8) {
                mostrarAlerta('El teléfono debe tener exactamente 8 dígitos', 'warning');
                return;
            }

            if (!validarFormatoLicencia(licencia)) {
                mostrarAlerta('El formato de la licencia no es válido', 'warning');
                return;
            }

            // Deshabilitar el botón de submit mientras se procesa
            const submitBtn = document.getElementById('submitBtn');
            submitBtn.disabled = true;

            try {
                const response = await fetch('https://infoavance.com/FollowCargo/conductores?action=add&usuario=admin', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        nombres: document.getElementById('nombres').value.trim(),
                        apellidos: document.getElementById('apellidos').value.trim(),
                        telefono: telefono,
                        email: document.getElementById('email').value.trim(),
                        licencia: formatearLicencia(document.getElementById('licencia').value.replace(/[^\d]/g, '')),
                        fechaLicencia: document.getElementById('fechaLicencia').value,
                        vencimientoLicencia: document.getElementById('vencimientoLicencia').value,
                        estado: document.getElementById('estado').value,
                        ubicacionActual: "San Salvador",
                        idUltimaRuta: 2
                    })
                });

                if (response.ok) {
                    mostrarAlerta('Conductor guardado exitosamente', 'success');
                    setTimeout(() => {
                        window.location.href = '/FollowCargo/conductores';
                    }, 1500);
                } else {
                    throw new Error('Error al guardar los datos');
                }
            } catch (error) {
                console.error('Error:', error);
                mostrarAlerta('Error al guardar los datos del conductor', 'danger');
            } finally {
                submitBtn.disabled = false;
            }
        });
    }

    // Inicializar las máscaras de entrada
    inicializarMascaras();
});

// Función para validar el formato de la licencia
function validarFormatoLicencia(licencia) {
    // Eliminar todos los caracteres que no sean números
    const numerosSolos = licencia.replace(/[^\d]/g, '');
    return numerosSolos.length === 14;
}

// Función para formatear la licencia al formato requerido
function formatearLicencia(numeros) {
    if (numeros.length !== 14) return '';
    return `${numeros.slice(0,4)}-${numeros.slice(4,10)}-${numeros.slice(10,13)}-${numeros.slice(13)}`;
}

// Función para inicializar las máscaras de entrada
function inicializarMascaras() {
    // Máscara para teléfono (8 dígitos)
    const inputTelefono = document.getElementById('telefono');
    inputTelefono.addEventListener('input', function(e) {
        let valor = this.value.replace(/\D/g, ''); // Eliminar todo excepto números
        if (valor.length > 8) {
            valor = valor.slice(0, 8); // Limitar a 8 dígitos
        }
        this.value = valor;
    });

    // Máscara para licencia (formato XXXX-XXXXXX-XXX-X)
    const inputLicencia = document.getElementById('licencia');
    inputLicencia.addEventListener('input', function(e) {
        let valor = this.value.replace(/\D/g, ''); // Eliminar todo excepto números
        if (valor.length > 14) {
            valor = valor.slice(0, 14); // Limitar a 14 dígitos
        }
        
        // Aplicar formato mientras el usuario escribe
        if (valor.length >= 4) valor = valor.slice(0,4) + '-' + valor.slice(4);
        if (valor.length >= 11) valor = valor.slice(0,11) + '-' + valor.slice(11);
        if (valor.length >= 15) valor = valor.slice(0,15) + '-' + valor.slice(15);
        
        this.value = valor;
    });

    // Agregar placeholders con el formato esperado
    inputTelefono.placeholder = "12345678";
    inputLicencia.placeholder = "0000-000000-000-0";
}

// Función para mostrar alertas
function mostrarAlerta(mensaje, tipo) {
    const alertaDiv = document.createElement('div');
    alertaDiv.className = `alert alert-${tipo} alert-dismissible fade show`;
    alertaDiv.role = 'alert';
    alertaDiv.innerHTML = `
        ${mensaje}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;

    const form = document.getElementById('conductorForm');
    form.parentNode.insertBefore(alertaDiv, form);

    setTimeout(() => {
        alertaDiv.remove();
    }, 5000);
}

// Función para cargar datos del conductor
async function cargarDatosConductor(id) {
    try {
        const response = await fetch(`https://infoavance.com/FollowCargo/conductores?id=${id}&action=get`, {
            method: 'POST'
        });
        if (!response.ok) {
            throw new Error('Error al cargar los datos del conductor');
        }
        
        const conductor = await response.json();
        
        document.getElementById('nombres').value = conductor.nombres;
        document.getElementById('apellidos').value = conductor.apellidos;
        document.getElementById('telefono').value = conductor.telefono;
        document.getElementById('email').value = conductor.email;
        document.getElementById('licencia').value = conductor.licencia;
        document.getElementById('fechaLicencia').value = conductor.fechaLicencia;
        document.getElementById('vencimientoLicencia').value = conductor.vencimientoLicencia;
        document.getElementById('estado').value = conductor.estado;

    } catch (error) {
        console.error('Error:', error);
        mostrarAlerta('Error al cargar los datos del conductor', 'danger');
    }
}

// Validación de fechas
document.getElementById('fechaLicencia').addEventListener('change', function() {
    const fechaVencimiento = document.getElementById('vencimientoLicencia');
    fechaVencimiento.min = this.value;
});

document.getElementById('vencimientoLicencia').addEventListener('change', function() {
    const fechaEmision = document.getElementById('fechaLicencia');
    if (this.value < fechaEmision.value) {
        this.value = fechaEmision.value;
        mostrarAlerta('La fecha de vencimiento debe ser posterior a la fecha de emisión', 'warning');
    }
});