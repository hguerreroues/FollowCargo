//PRODUCTOS
INSERT INTO `producto`(`nombre`, `descripcion`, `peso`, `dimension_alto`, `dimension_ancho`, `dimension_largo`, `fecha_creacion`) VALUES ('Repuestos','Repuesto de vehiculo','1000','2','2','5','2024-11-06 15:34:12');
INSERT INTO `producto`(`nombre`, `descripcion`, `peso`, `dimension_alto`, `dimension_ancho`, `dimension_largo`, `fecha_creacion`) VALUES ('Granos','Granos de la canasta basica','1000','2','2','5','2024-11-06 15:34:12');
INSERT INTO `producto`(`nombre`, `descripcion`, `peso`, `dimension_alto`, `dimension_ancho`, `dimension_largo`, `fecha_creacion`) VALUES ('Agua','Agua envasada','1000','2','2','5','2024-11-06 15:34:12');
INSERT INTO `producto`(`nombre`, `descripcion`, `peso`, `dimension_alto`, `dimension_ancho`, `dimension_largo`, `fecha_creacion`) VALUES ('Ropa','Ropa importada','1000','2','2','5','2024-11-06 15:34:12');

//RUTAS
INSERT INTO `rutas`(`id_viaje`, `origen`, `destino`, `distancia`, `fecha_creacion`) VALUES ('1','San Salvador','San Miguel','100','2024-11-06 15:34:12');
INSERT INTO `rutas`(`id_viaje`, `origen`, `destino`, `distancia`, `fecha_creacion`) VALUES ('2','San Salvador','La union','150','2024-11-06 15:34:12');
INSERT INTO `rutas`(`id_viaje`, `origen`, `destino`, `distancia`, `fecha_creacion`) VALUES ('1','San Salvador','Guatemala','200','2024-11-06 15:34:12');
INSERT INTO `rutas`(`id_viaje`, `origen`, `destino`, `distancia`, `fecha_creacion`) VALUES ('1','Santa Ana','San Miguel','150','2024-11-06 15:34:12');

//RUTAS GEOLOCALIZACION
INSERT INTO `rutas_geolocalizacion`(`id_ruta`, `latitud`, `longitud`, `descripcion`, `fecha_creacion`) VALUES ('1','12.345','98.765','Ubicacion relativa','2024-11-06 15:34:12');
INSERT INTO `rutas_geolocalizacion`(`id_ruta`, `latitud`, `longitud`, `descripcion`, `fecha_creacion`) VALUES ('2','12.345','98.765','Ubicacion relativa','2024-11-06 15:34:12');
INSERT INTO `rutas_geolocalizacion`(`id_ruta`, `latitud`, `longitud`, `descripcion`, `fecha_creacion`) VALUES ('3','12.345','98.765','Ubicacion relativa','2024-11-06 15:34:12');
INSERT INTO `rutas_geolocalizacion`(`id_ruta`, `latitud`, `longitud`, `descripcion`, `fecha_creacion`) VALUES ('4','12.345','98.765','Ubicacion relativa','2024-11-06 15:34:12');

//VEHICULOS
INSERT INTO `vehiculo`(`tipo`, `marca`, `modelo`, `placa`, `fecha_creacion`) VALUES ('Furgon','Bluebird','GTX','T-123456','2024-11-06 15:34:12');
INSERT INTO `vehiculo`(`tipo`, `marca`, `modelo`, `placa`, `fecha_creacion`) VALUES ('Furgon','Freightliner','Optimus','T-234567','2024-11-06 15:34:12');
INSERT INTO `vehiculo`(`tipo`, `marca`, `modelo`, `placa`, `fecha_creacion`) VALUES ('Trailer','Bluebird','GTX','T-123456','2024-11-06 15:34:12');
INSERT INTO `vehiculo`(`tipo`, `marca`, `modelo`, `placa`, `fecha_creacion`) VALUES ('Trailer','Freightliner','Optimus','T-234567','2024-11-06 15:34:12');

//VIAJES
INSERT INTO `viajes`(`id_vehiculo`, `id_ruta`, `costo`, `estado`, `fecha`, `fecha_creacion`) VALUES ('1','1','300','En Ruta','2024-11-06','2024-11-06 15:34:12');
INSERT INTO `viajes`(`id_vehiculo`, `id_ruta`, `costo`, `estado`, `fecha`, `fecha_creacion`) VALUES ('2','2','500','Pendiente','2024-11-06','2024-11-06 15:34:12');
INSERT INTO `viajes`(`id_vehiculo`, `id_ruta`, `costo`, `estado`, `fecha`, `fecha_creacion`) VALUES ('3','3','600','Interrumpido','2024-11-06','2024-11-06 15:34:12');
INSERT INTO `viajes`(`id_vehiculo`, `id_ruta`, `costo`, `estado`, `fecha`, `fecha_creacion`) VALUES ('4','4','700','Finalizado','2024-11-06','2024-11-06 15:34:12');

//VIAJES DETALLE PRODUCTO
INSERT INTO `viajes_detalle_producto`(`id_viaje`, `id_producto`, `fecha_creacion`) VALUES ('1','1','2024-11-06 15:34:12');
INSERT INTO `viajes_detalle_producto`(`id_viaje`, `id_producto`, `fecha_creacion`) VALUES ('2','2','2024-11-06 15:34:12');
INSERT INTO `viajes_detalle_producto`(`id_viaje`, `id_producto`, `fecha_creacion`) VALUES ('3','3','2024-11-06 15:34:12');
INSERT INTO `viajes_detalle_producto`(`id_viaje`, `id_producto`, `fecha_creacion`) VALUES ('4','4','2024-11-06 15:34:12');