let conductoresTable;

document.addEventListener('DOMContentLoaded', function() {
    // Configuración del idioma español
    const spanishLanguage = {
        "decimal": "",
        "emptyTable": "No hay datos disponibles",
        "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
        "infoEmpty": "Mostrando 0 a 0 de 0 registros",
        "infoFiltered": "(filtrado de _MAX_ registros totales)",
        "infoPostFix": "",
        "thousands": ",",
        "lengthMenu": "Mostrar _MENU_ registros",
        "loadingRecords": "Cargando...",
        "processing": "Procesando...",
        "search": "Buscar:",
        "zeroRecords": "No se encontraron registros coincidentes",
        "paginate": {
            "first": "Primero",
            "last": "Último",
            "next": "Siguiente",
            "previous": "Anterior"
        },
        "aria": {
            "sortAscending": ": activar para ordenar columna ascendente",
            "sortDescending": ": activar para ordenar columna descendente"
        },
        "buttons": {
            "copy": "Copiar",
            "csv": "CSV",
            "excel": "Excel",
            "pdf": "PDF",
            "print": "Imprimir",
            "colvis": "Visibilidad de columnas"
        }
    };

    // Inicializar DataTable
    conductoresTable = new DataTable('#conductoresTable', {
        responsive: true,
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ],
        language: spanishLanguage
    });
    
    // Cargar los conductores
    listConductores();
});

const listConductores = async () => {
    try {
        const response = await fetch('https://infoavance.com/FollowCargo/conductores?action=list');
        const conductores = await response.json();
        
        // Limpiar la tabla existente
        conductoresTable.clear();
        
        if (conductores.length === 0) {
            document.getElementById('noRecordsMessage').style.display = 'block';
            document.getElementById('conductoresTable').style.display = 'none';
        } else {
            document.getElementById('noRecordsMessage').style.display = 'none';
            document.getElementById('conductoresTable').style.display = 'table';
            
            // Agregar los datos a la tabla
            conductores.forEach((conductor, index) => {
                conductoresTable.row.add([
                    '<i class="fa-solid fa-user-tie"></i>',
                    index + 1,
                    conductor.nombres,
                    conductor.apellidos,
                    conductor.telefono,
                    conductor.email,
                    getEstadoBadge(conductor.estado),
                    conductor.ubicacionActual || 'No disponible',
                    `<button class="btn btn-sm btn-primary" onclick="redirectToDetail(${conductor.id})">
                        <i class="fa-solid fa-eye"></i>
                    </button>`
                ]);
            });
            
            // Redibujar la tabla
            conductoresTable.draw();
        }
    } catch (error) {
        console.error('Error al cargar conductores:', error);
        alert('Error al cargar la lista de conductores');
    }
};

const getEstadoBadge = (estado) => {
    let badge;
    switch (estado) {
        case '1':
            badge = '<span class="badge bg-success">Activo</span>';
            break;
        case '0':
            badge = '<span class="badge bg-danger">Inactivo</span>';
            break;
        default:
            badge = '<span class="badge bg-secondary">Desconocido</span>';
    }
    return badge;
};

const redirectToDetail = (id) => {
    window.location.href = `/FollowCargo/conductores-form?id=${id}`;
};