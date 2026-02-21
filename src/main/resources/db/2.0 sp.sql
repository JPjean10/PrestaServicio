DROP PROCEDURE IF EXISTS SP_LogService;

DELIMITER $$

CREATE PROCEDURE SP_LogService(
    IN p_id_usuario       BIGINT,
    IN p_request_code    VARCHAR(25),
    IN p_http_status_code INT,
    IN p_ip               VARCHAR(20),
    IN p_method           VARCHAR(25),
    IN p_end_point        VARCHAR(20),
    IN p_request_header   TEXT,
    IN p_request_body     TEXT,
    IN p_response_body    TEXT,
    IN p_error_           TEXT,
    IN p_begin_date_time   DATETIME,
    IN p_end_date_time     DATETIME
)
BEGIN
    INSERT INTO log_service(
        id_usuario,
        request_code,
        http_status_code,
        ip,
        method,
        end_point,
        request_header,
        request_body,
        response_body,
        error_,
        begin_date,
        begin_time,
        transcurred_time,
        end_date,
        end_time
    ) VALUES (
        p_id_usuario,
        p_request_code,
        p_http_status_code,
        p_ip,
        p_method,
        p_end_point,
        p_request_header,
        p_request_body,
        p_response_body,
        p_error_,
        DATE(p_begin_date_time),
        TIME(p_begin_date_time),
        CAST(TIMESTAMPDIFF(MICROSECOND, p_begin_date_time, p_end_date_time) / 1000 AS UNSIGNED),
        DATE(p_end_date_time),
        TIME(p_end_date_time)
    );
END $$

grant execute on procedure presta_bd.SP_LogService   TO 'presta_app'@'localhost';

 DROP PROCEDURE IF EXISTS SP_InsertarUsuario;

 DELIMITER $$

CREATE PROCEDURE SP_InsertarUsuario(
  IN p_usuario  varchar(20),
  IN p_contrasena  varchar(20),
  IN p_nombre      varchar(50)
)
BEGIN

  -- Si también quieres evitar repetición de usuario + contraseña
  IF EXISTS (SELECT 1 FROM usuario 
             WHERE usuario = p_usuario AND contrasena = p_contrasena) THEN
      SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'El usuario y contraseña ya existen';
  END IF;

  INSERT INTO usuario (usuario, contrasena,nombre)
  VALUES (p_usuario, p_contrasena,p_nombre);
END $$

DELIMITER ;

grant execute on procedure presta_bd.SP_InsertarUsuario   TO 'presta_app'@'localhost';