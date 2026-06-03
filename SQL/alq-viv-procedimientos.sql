-- ---------------------------------------------------------
-- Proyecto fin de curso ---------------------------------------
-- Script procedimientos Alquiler viviendas -------------------------------
-- alquiler_viviendas_procedimientos.sql--------------------------------------------
-- ----------------------------------------------------------------
-- PROPIETARIO
-- Procedimiento para insertar un propietario
DELIMITER //
DROP PROCEDURE IF EXISTS sp_crear_propietario//
CREATE PROCEDURE sp_crear_propietario(
	IN p_DNI CHAR(10),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(50),
    IN p_email VARCHAR(100)
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_DNI INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL INSERTAR EL PROPIETARIO" AS mensaje;
		END;
		
		-- si existe este dni no insertar nada
		SELECT COUNT(*) INTO v_DNI FROM propietario WHERE DNI=UPPER(p_DNI);
		IF v_DNI >0 THEN
			SELECT "Este DNI ya existe." AS mensaje;
			LEAVE sp;
		END IF;
		-- iniciamos transaccion
		START TRANSACTION;
		-- insertamos el nuevo propietario
		INSERT INTO propietario(DNI, nombre, telefono, email) VALUES(p_DNI, p_nombre, p_telefono,p_email);
		-- confirmamos cambios
		COMMIT;
		SELECT "Propietario insertado" AS mensaje;	
END//
DELIMITER ;
-- -------------------------------------------------------------------------------------------------------------------
-- Procedimiento para eliminar un propietario
DELIMITER //
DROP PROCEDURE IF EXISTS sp_eliminar_propietario//
CREATE PROCEDURE sp_eliminar_propietario(
	IN p_id INT
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_existe INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL ELIMINAR EL PROPIETARIO " AS mensaje;
		END;
		
		-- verificar si existe este id o no 
		SELECT COUNT(*) INTO v_existe FROM propietario WHERE id=p_id;
		IF v_existe =0 THEN
			SELECT "Este propietario no existe." AS mensaje;
			
		ELSE
			START TRANSACTION;
            -- eliminar los contratos de las viviendas de ese propietario
            DELETE FROM contrato WHERE id_vivienda IN (SELECT id FROM vivienda WHERE id_propietario= p_id); 
            -- eliminar la vivienda
            DELETE FROM vivienda WHERE id_propietario=p_id;
            -- eliminar el propietario
			DELETE FROM propietario WHERE id=p_id;	
            COMMIT;
            SELECT "PROPIETARIO ELIMINADO" AS mensaje; 
		END IF;
			
END//
DELIMITER ;
-- ---------------------------------------------------------------------------------
-- Consultar un propietario
DELIMITER //
DROP PROCEDURE IF EXISTS sp_consultar_propietario//
CREATE PROCEDURE sp_consultar_propietario(
    IN p_id INT
)
BEGIN

    SELECT *
    FROM propietario
    WHERE id = p_id;

END//
DELIMITER ;
-- --------------------------------------------------------------------------
-- Modificar datos del propietario
DELIMITER //
DROP PROCEDURE IF EXISTS sp_modificar_propietario//
CREATE PROCEDURE sp_modificar_propietario(
	IN p_id INT,
	IN p_DNI CHAR(10),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(50),
    IN p_email VARCHAR(100)
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_id INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL MODIFICAR" AS mensaje;
		END;
		
		-- si no existe este id no modificar nada
		SELECT COUNT(*) INTO v_id FROM propietario WHERE id=p_id;
		IF v_id =0 THEN
			SELECT "Este Propietario no existe." AS mensaje;
			LEAVE sp;
		END IF;
		-- iniciamos transaccion
		START TRANSACTION;
        -- modificamos los datos
		UPDATE propietario
		SET
			DNI = UPPER(p_dni),
            nombre = p_nombre,
            telefono=p_telefono,
            email = p_email
		WHERE id = p_id;
		
		-- confirmamos cambios
		COMMIT;
		SELECT "Propietario Modificado" AS mensaje;	
END//
DELIMITER ;
-- exportar propietario 
DELIMITER //

DROP PROCEDURE IF EXISTS sp_exportar_propietarios_json//

CREATE PROCEDURE sp_exportar_propietarios_json()
BEGIN

    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'id', id,
            'dni', DNI,
            'nombre', nombre,
            'telefono', telefono,
            'email', email
        )
    ) AS resultado
    FROM propietario;

END//

DELIMITER ;
-- --------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------
-- VIVIENDA
-- Insertar vivienda
DELIMITER //
DROP PROCEDURE IF EXISTS sp_crear_vivienda//
CREATE PROCEDURE sp_crear_vivienda(
	IN p_id_propietario INT ,
	IN P_codigo VARCHAR(10) ,
    IN p_tipo INT ,
    IN p_direccion VARCHAR(200) ,
    IN p_superficie INT ,
    IN p_precio_mes DECIMAL(10,2) ,
    IN p_descripcion VARCHAR(200) ,
    IN p_acepta_mascota BOOL 
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_id_prop INT DEFAULT 0;
        DECLARE v_codigo INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL INSERTAR LA VIVIENDA" AS mensaje;
		END;
		
		-- si existe este codigo no insertar nada
		SELECT COUNT(*) INTO v_codigo FROM vivienda WHERE codigo=UPPER(p_codigo);
		IF v_codigo >0 THEN
			SELECT "Este codigo ya existe." AS mensaje;
			LEAVE sp;
		END IF;
        -- si no existe este id de propietario mostrar un error
        SELECT COUNT(*) INTO v_id_prop FROM propietario WHERE id=p_id_propietario;
		IF v_id_prop =0 THEN
			SELECT "No existe este propietario" AS mensaje;
			LEAVE sp;
		END IF;
		-- iniciamos transaccion
		START TRANSACTION;
		-- insertamos la nueva vivienda
		INSERT INTO vivienda(id_propietario, codigo,tipo ,direccion, superficie, precio_mes, descripcion, acepta_mascota ) 
        VALUES(p_id_propietario, p_codigo,p_tipo ,p_direccion, p_superficie, p_precio_mes, p_descripcion, p_acepta_mascota );
		-- confirmamos cambios
		COMMIT;
		SELECT "Vivienda insertada" AS mensaje;	
END//
DELIMITER ;
-- -------------------------------------------------------------------------------------------------------------------
-- Procedimiento para eliminar una vivienda
DELIMITER //
DROP PROCEDURE IF EXISTS sp_eliminar_vivienda//
CREATE PROCEDURE sp_eliminar_vivienda(
	IN p_id INT
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_existe INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL ELIMINAR LA VIVIENDA " AS mensaje;
		END;
		
		-- verificar si existe este id o no 
		SELECT COUNT(*) INTO v_existe FROM vivienda WHERE id=p_id;
		IF v_existe =0 THEN
			SELECT "Esta vivienda no existe." AS mensaje;
			
		ELSE
			START TRANSACTION;
            -- eliminar los contratos de la vivienda
            DELETE FROM contrato WHERE id_vivienda=p_id; 
            -- eliminar la vivienda
            DELETE FROM vivienda WHERE id=p_id;
            
            COMMIT;
            SELECT "VIVIENDA ELIMINADA" AS mensaje; 
		END IF;
			
END//
DELIMITER ;
-- ------------------------------------------------------------------------------------
-- Modificar datos de la vivienda
DELIMITER //
DROP PROCEDURE IF EXISTS sp_modificar_vivienda//
CREATE PROCEDURE sp_modificar_vivienda(
	IN p_id INT,
	IN p_id_propietario INT ,
	IN P_codigo VARCHAR(10) ,
    IN p_tipo INT ,
    IN p_direccion VARCHAR(200) ,
    IN p_superficie INT ,
    IN p_precio_mes DECIMAL(10,2) ,
    IN p_descripcion VARCHAR(200) ,
    IN p_acepta_mascota BOOL 
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_id INT DEFAULT 0;
        DECLARE v_id_prop INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL MODIFICAR" AS mensaje;
		END;
		
		-- si no existe este id no modificar nada
		SELECT COUNT(*) INTO v_id FROM vivienda WHERE id=p_id;
		IF v_id =0 THEN
			SELECT "Esta vivienda no existe." AS mensaje;
			LEAVE sp;
		END IF;
        -- si no existe este id de propietario mostrar un error
        SELECT COUNT(*) INTO v_id_prop FROM propietario WHERE id=p_id_propietario;
		IF v_id_prop =0 THEN
			SELECT "No existe este propietario" AS mensaje;
			LEAVE sp;
		END IF;
		-- iniciamos transaccion
		START TRANSACTION;
        -- modificamos los datos
		UPDATE vivienda
		SET
			id_propietario=p_id_propietario, 
            codigo=p_codigo,
            tipo=p_tipo ,
            direccion=p_direccion,
            superficie=p_superficie,
            precio_mes=p_precio_mes, 
            descripcion=p_descripcion, 
            acepta_mascota=p_acepta_mascota
		WHERE id = p_id;
		
		-- confirmamos cambios
		COMMIT;
		SELECT "Vivienda Modificada" AS mensaje;	
END//
DELIMITER ;
-- ---------------------------------------------------------------------------------
-- Consultar una vivienda
DELIMITER //
DROP PROCEDURE IF EXISTS sp_consultar_vivienda//
CREATE PROCEDURE sp_consultar_vivienda(
	IN p_id INT
)
BEGIN
		-- declaracion variables
		DECLARE v_id INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			SELECT "ERROR AL CONSULTAR " AS mensaje;
		END;
		
		-- verificar si existe este id o no 
		SELECT COUNT(*) INTO v_id FROM vivienda WHERE id=p_id;
		IF v_id =0 THEN
			SELECT "Esta vivienda no existe." AS mensaje;
			
		ELSE
			 SELECT * FROM vivienda WHERE id = p_id;
		END IF;
			
END//
DELIMITER ;
-- exportar viviendas
DELIMITER //

DROP PROCEDURE IF EXISTS sp_exportar_viviendas_json//

CREATE PROCEDURE sp_exportar_viviendas_json()
BEGIN

    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'id', id,
            'id_propietario', id_propietario,
            'codigo', codigo,
            'tipo', tipo,
            'direccion', direccion,
            'superficie', superficie,
            'precio_mes', precio_mes
        )
    ) AS resultado
    FROM vivienda;

END//

DELIMITER ;
-- ------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------
-- INQUILINO 
-- Procedimiento para insertar un inquilino
DELIMITER //
DROP PROCEDURE IF EXISTS sp_crear_inquilino//
CREATE PROCEDURE sp_crear_inquilino(
	IN p_DNI CHAR(10),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_tiene_mascota BOOL
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_DNI INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL INSERTAR EL INQUILINO" AS mensaje;
		END;
		
		-- si existe este dni no insertar nada
		SELECT COUNT(*) INTO v_DNI FROM inquilino WHERE DNI=UPPER(p_DNI);
		IF v_DNI >0 THEN
			SELECT "Este DNI ya existe." AS mensaje;
			LEAVE sp;
		END IF;
		-- iniciamos transaccion
		START TRANSACTION;
		-- insertamos el nuevo inquilino
		INSERT INTO inquilino(DNI, nombre, telefono, email, tiene_mascota) VALUES(p_DNI, p_nombre, p_telefono,p_email, p_tiene_mascota);
		-- confirmamos cambios
		COMMIT;
		SELECT "Inquilino insertado" AS mensaje;	
END//
DELIMITER ;
-- -------------------------------------------------------------------------------------------------------------------
-- Procedimiento para eliminar un inquilino
DELIMITER //
DROP PROCEDURE IF EXISTS sp_eliminar_inquilino//
CREATE PROCEDURE sp_eliminar_inquilino(
	IN p_id INT
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_existe INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL ELIMINAR EL INQUILINO " AS mensaje;
		END;
		
		-- verificar si existe este id o no 
		SELECT COUNT(*) INTO v_existe FROM inquilino WHERE id=p_id;
		IF v_existe =0 THEN
			SELECT "Este inquilino no existe." AS mensaje;
			
		ELSE
			START TRANSACTION;
            -- eliminar los contratos de ese inquilino
            DELETE FROM contrato WHERE id_inquilino =p_id ; 
            -- eliminar el inquilino
			DELETE FROM inquilino WHERE id=p_id;	
            COMMIT;
            SELECT "INQUILINO ELIMINADO" AS mensaje; 
		END IF;
			
END//
DELIMITER ;
-- ---------------------------------------------------------------------------------
-- Consultar un inquilino
DELIMITER //
DROP PROCEDURE IF EXISTS sp_consultar_inquilino//
CREATE PROCEDURE sp_consultar_inquilino(
    IN p_id INT
)
BEGIN

    SELECT *
    FROM inquilino
    WHERE id = p_id;

END//
DELIMITER ;
-- --------------------------------------------------------------------------
-- Modificar datos del inquilino
DELIMITER //
DROP PROCEDURE IF EXISTS sp_modificar_inquilino//
CREATE PROCEDURE sp_modificar_inquilino(
	IN p_id INT,
	IN p_DNI CHAR(10),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_tiene_mascota BOOL
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_id INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL MODIFICAR" AS mensaje;
		END;
		
		-- si no existe este id no modificar nada
		SELECT COUNT(*) INTO v_id FROM inquilino WHERE id=p_id;
		IF v_id =0 THEN
			SELECT "Este inquilino no existe." AS mensaje;
			LEAVE sp;
		END IF;
		-- iniciamos transaccion
		START TRANSACTION;
        -- modificamos los datos
		UPDATE inquilino
		SET
			DNI = UPPER(p_dni),
            nombre = p_nombre,
            telefono=p_telefono,
            email = p_email,
            tiene_mascota=p_tiene_mascota
		WHERE id = p_id;
		
		-- confirmamos cambios
		COMMIT;
		SELECT "Inquilino Modificado" AS mensaje;	
END//
DELIMITER ;
-- exportar inquilinos 
DELIMITER //

DROP PROCEDURE IF EXISTS sp_exportar_inquilinos_json//

CREATE PROCEDURE sp_exportar_inquilinos_json()
BEGIN

    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'id', id,
            'dni', DNI,
            'nombre', nombre,
            'telefono', telefono,
            'email', email,
            'tiene_mascota', tiene_mascota
        )
    ) AS resultado
    FROM inquilino;

END//

DELIMITER ;
-- ----------------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------------
-- CONTRATO
-- Procedimiento para insertar un contrato
DELIMITER //
DROP PROCEDURE IF EXISTS sp_crear_contrato//
CREATE PROCEDURE sp_crear_contrato(
    IN p_id_vivienda INT,
    IN p_id_inquilino INT,
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_precio DECIMAL(10,2),
    IN p_estado INT
)
sp: BEGIN

    DECLARE v_inquilino INT DEFAULT 0;
    DECLARE v_vivienda INT DEFAULT 0;
    DECLARE v_estado INT DEFAULT 0;
    DECLARE v_solapamiento INT DEFAULT 0;
    DECLARE v_tiene_mascota BOOL DEFAULT FALSE;
    DECLARE v_acepta_mascota BOOL DEFAULT FALSE;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'ERROR AL INSERTAR EL CONTRATO' AS mensaje;
    END;

    SELECT COUNT(*) INTO v_inquilino
    FROM inquilino
    WHERE id = p_id_inquilino;

    IF v_inquilino = 0 THEN
        SELECT 'No existe este inquilino.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT COUNT(*) INTO v_vivienda
    FROM vivienda
    WHERE id = p_id_vivienda;

    IF v_vivienda = 0 THEN
        SELECT 'No existe esta vivienda.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT COUNT(*) INTO v_estado
    FROM tipoestado
    WHERE id = p_estado;

    IF v_estado = 0 THEN
        SELECT 'No existe este estado.' AS mensaje;
        LEAVE sp;
    END IF;

    IF p_fecha_fin < p_fecha_inicio THEN
        SELECT 'La fecha final no puede ser anterior a la fecha de inicio.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT tiene_mascota INTO v_tiene_mascota
    FROM inquilino
    WHERE id = p_id_inquilino;

    SELECT acepta_mascota INTO v_acepta_mascota
    FROM vivienda
    WHERE id = p_id_vivienda;

    IF v_tiene_mascota = TRUE AND v_acepta_mascota = FALSE THEN
        SELECT 'No se puede crear el contrato: el inquilino tiene mascota y la vivienda no acepta mascotas.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT COUNT(*) INTO v_solapamiento
    FROM contrato
    WHERE id_vivienda = p_id_vivienda
      AND p_fecha_inicio <= fecha_fin
      AND p_fecha_fin >= fecha_inicio;

    IF v_solapamiento > 0 THEN
        SELECT 'No se puede crear el contrato: la vivienda ya tiene un contrato en esas fechas.' AS mensaje;
        LEAVE sp;
    END IF;

    START TRANSACTION;

    INSERT INTO contrato (
        id_vivienda,
        id_inquilino,
        fecha_inicio,
        fecha_fin,
        precio,
        estado
    ) VALUES (
        p_id_vivienda,
        p_id_inquilino,
        p_fecha_inicio,
        p_fecha_fin,
        p_precio,
        p_estado
    );

    COMMIT;

    SELECT 'Contrato insertado' AS mensaje;

END//
DELIMITER ;
-- -------------------------------------------------------------------------------------------------------------------
-- Procedimiento para eliminar un contrato
DELIMITER //
DROP PROCEDURE IF EXISTS sp_eliminar_contrato//
CREATE PROCEDURE sp_eliminar_contrato(
	IN p_id INT
)
sp: BEGIN
	-- declaracion variables
		DECLARE v_existe INT DEFAULT 0;
		-- control general errores
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			ROLLBACK;
            SELECT "ERROR AL ELIMINAR EL CONTRATO " AS mensaje;
		END;
		
		-- verificar si existe este id o no 
		SELECT COUNT(*) INTO v_existe FROM contrato WHERE id_contrato=p_id;
		IF v_existe =0 THEN
			SELECT "Este contrato no existe." AS mensaje;
			
		ELSE
			START TRANSACTION;
			-- eliminar el contrato
			DELETE FROM contrato WHERE id_contrato=p_id;	
            COMMIT;
            SELECT "CONTRATO ELIMINADO" AS mensaje; 
		END IF;
			
END//
DELIMITER ;
-- ---------------------------------------------------------------------------------
-- Consultar un contrato
DELIMITER //
DROP PROCEDURE IF EXISTS sp_consultar_contrato//
CREATE PROCEDURE sp_consultar_contrato(
    IN p_id INT
)
BEGIN

    SELECT *
    FROM contrato
    WHERE id_contrato = p_id;

END//
DELIMITER ;
-- --------------------------------------------------------------------------
-- Modificar datos del contrato
DELIMITER //
DROP PROCEDURE IF EXISTS sp_modificar_contrato//
CREATE PROCEDURE sp_modificar_contrato(
    IN p_id_contrato INT,
    IN p_id_vivienda INT,
    IN p_id_inquilino INT,
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_precio DECIMAL(10,2),
    IN p_estado INT
)
sp: BEGIN

    DECLARE v_contrato INT DEFAULT 0;
    DECLARE v_vivienda INT DEFAULT 0;
    DECLARE v_inquilino INT DEFAULT 0;
    DECLARE v_estado INT DEFAULT 0;
    DECLARE v_tiene_mascota BOOL DEFAULT FALSE;
    DECLARE v_acepta_mascota BOOL DEFAULT FALSE;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'ERROR AL MODIFICAR EL CONTRATO' AS mensaje;
    END;

    SELECT COUNT(*) INTO v_contrato
    FROM contrato
    WHERE id_contrato = p_id_contrato;

    IF v_contrato = 0 THEN
        SELECT 'Este contrato no existe.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT COUNT(*) INTO v_vivienda
    FROM vivienda
    WHERE id = p_id_vivienda;

    IF v_vivienda = 0 THEN
        SELECT 'Esta vivienda no existe.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT COUNT(*) INTO v_inquilino
    FROM inquilino
    WHERE id = p_id_inquilino;

    IF v_inquilino = 0 THEN
        SELECT 'Este inquilino no existe.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT COUNT(*) INTO v_estado
    FROM tipoestado
    WHERE id = p_estado;

    IF v_estado = 0 THEN
        SELECT 'Este estado no existe.' AS mensaje;
        LEAVE sp;
    END IF;

    IF p_fecha_fin < p_fecha_inicio THEN
        SELECT 'La fecha final no puede ser anterior a la fecha de inicio.' AS mensaje;
        LEAVE sp;
    END IF;

    SELECT tiene_mascota INTO v_tiene_mascota
    FROM inquilino
    WHERE id = p_id_inquilino;

    SELECT acepta_mascota INTO v_acepta_mascota
    FROM vivienda
    WHERE id = p_id_vivienda;

    IF v_tiene_mascota = TRUE AND v_acepta_mascota = FALSE THEN
        SELECT 'No se puede asignar esta vivienda: el inquilino tiene mascota y la vivienda no acepta mascotas.' AS mensaje;
        LEAVE sp;
    END IF;

    START TRANSACTION;

    UPDATE contrato
    SET
        id_vivienda = p_id_vivienda,
        id_inquilino = p_id_inquilino,
        fecha_inicio = p_fecha_inicio,
        fecha_fin = p_fecha_fin,
        precio = p_precio,
        estado = p_estado
    WHERE id_contrato = p_id_contrato;

    COMMIT;

    SELECT 'Contrato modificado' AS mensaje;

END//
DELIMITER ;
-- exportar contratos
DELIMITER //

DROP PROCEDURE IF EXISTS sp_exportar_contratos_json//

CREATE PROCEDURE sp_exportar_contratos_json()
BEGIN

    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'id_contrato', id_contrato,
            'id_vivienda', id_vivienda,
            'id_inquilino', id_inquilino,
            'fecha_inicio', fecha_inicio,
            'fecha_fin', fecha_fin,
            'precio', precio,
            'estado', estado
        )
    ) AS resultado
    FROM contrato;

END//

DELIMITER ;
-- ------------------------------------------------------------------------------------------------------------
-- -----------------------------------------------------------------------------------------------------
-- Procedimientos para consultas avanzadas 
DELIMITER //

DROP PROCEDURE IF EXISTS sp_historico_alquileres_inquilino//

CREATE PROCEDURE sp_historico_alquileres_inquilino(
    IN p_id_inquilino INT
)
BEGIN

    SELECT
        c.id_contrato,
        v.codigo,
        v.direccion,
        c.fecha_inicio,
        c.fecha_fin,
        c.precio
    FROM contrato c
    INNER JOIN vivienda v
        ON c.id_vivienda = v.id
    WHERE c.id_inquilino = p_id_inquilino
    ORDER BY c.fecha_inicio;

END//
DELIMITER ;
DELIMITER //
DROP PROCEDURE IF EXISTS sp_viviendas_alquiladas_propietario//

CREATE PROCEDURE sp_viviendas_alquiladas_propietario(
    IN p_id_propietario INT
)
BEGIN

    SELECT
        v.codigo,
        v.direccion,
        i.nombre,
        c.fecha_inicio,
        c.fecha_fin
    FROM vivienda v
    INNER JOIN contrato c
        ON v.id = c.id_vivienda
    INNER JOIN inquilino i
        ON c.id_inquilino = i.id
    WHERE v.id_propietario = p_id_propietario;

END//

DELIMITER ;