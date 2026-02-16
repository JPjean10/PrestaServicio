 CREATE DATABASE presta_bd;
 
 CREATE TABLE tipo_estado (
   tipo_estado_id Integer AUTO_INCREMENT PRIMARY KEY,
   nombre varchar(20)
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.tipo_estado                TO 'presta_app'@'localhost';

 CREATE TABLE estado (
    estado_id Integer AUTO_INCREMENT PRIMARY KEY,
    tipo_estado_id Integer,
    nombre varchar(20),
    estado int,
    CONSTRAINT fk_tipo_estado_estado FOREIGN KEY (tipo_estado_id) REFERENCES tipo_estado(tipo_estado_id)
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.estado                TO 'presta_app'@'localhost';

 CREATE TABLE usuario (
  usuario_id Integer AUTO_INCREMENT PRIMARY KEY,
  usuario varchar(20),
  contrasena varchar(20),
  nombre varchar(50),
  fecha_hora_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.usuario                TO 'presta_app'@'localhost';

 CREATE TABLE categoria (
  caregoria_id Integer AUTO_INCREMENT PRIMARY KEY,
  nombre_categoria varchar(50),
  fecha_hora_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.categoria                TO 'presta_app'@'localhost';

 CREATE TABLE Prestamo (
  prestamo_id Integer AUTO_INCREMENT PRIMARY KEY,
  usuario_id Integer,
  caregoria_id Integer,
  monto decimal(5,2),
  descripcion varchar(100),
  fecha_hora_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
  estado_id Integer,
  CONSTRAINT fk_Prestamo_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
  CONSTRAINT fk_Prestamo_categoria FOREIGN KEY (caregoria_id) REFERENCES categoria(caregoria_id),
  CONSTRAINT fk_Prestamo_estado FOREIGN KEY (estado_id) REFERENCES estado(estado_id)
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.Prestamo                TO 'presta_app'@'localhost';

 CREATE TABLE deuda (
  deuda_id Integer AUTO_INCREMENT PRIMARY KEY,
  usuario_id Integer,
  caregoria_id Integer,
  monto decimal(5,2),
  descripcion varchar(100),
  fecha_hora_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_deuda_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
  CONSTRAINT fk_deuda_categoria FOREIGN KEY (caregoria_id) REFERENCES categoria(caregoria_id)
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.deuda                TO 'presta_app'@'localhost';

 CREATE TABLE movimiento (
  movimiento_id Integer AUTO_INCREMENT PRIMARY KEY,
  prestamo_id Integer,
  deuda_id Integer,
  monto_abonado decimal(5,2),
  descripcion varchar(100),
  fecha_hora_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
  estado_id Integer,
  CONSTRAINT fk_movimiento_Prestamo FOREIGN KEY (prestamo_id) REFERENCES Prestamo(prestamo_id),
  CONSTRAINT fk_movimiento_deuda FOREIGN KEY (deuda_id) REFERENCES deuda(deuda_id),
  CONSTRAINT fk_movimiento_estado FOREIGN KEY (estado_id) REFERENCES estado(estado_id)
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.movimiento                TO 'presta_app'@'localhost';

CREATE TABLE log_service(
 log_service_id BIGINT AUTO_INCREMENT PRIMARY KEY,
 id_usuario     BIGINT,
 request_code   varchar(20),
 http_status_code int,
 ip             varchar(20),
 method         varchar(10),
 end_point      varchar(200),
 request_header text,
 request_body   text,
 response_body  text,
 error_         text,
 begin_date     date,
 begin_time     time,
 transcurred_time int,
 end_date       date,
 end_time       time
);
GRANT SELECT, INSERT, UPDATE ON presta_bd.log_service                TO 'presta_app'@'localhost';