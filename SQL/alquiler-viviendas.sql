-- ---------------------------------------------------------
-- Proyecto fin de curso ---------------------------------------
-- Script creacion Alquiler viviendas -------------------------------
-- alquiler_viviendas.sql--------------------------------------------
-- ----------------------------------------------------------------
-- Creamos la base de datos
DROP DATABASE IF EXISTS alquiler_viviendas;
CREATE DATABASE alquiler_viviendas;
USE alquiler_viviendas;
-- ------------------------------------------------------------------
-- Creamos la tabla propietario
DROP TABLE IF EXISTS propietario;
CREATE TABLE propietario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    DNI CHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
    
);
-- ---------------------------------------------------------------------
-- Creamos la tabla tipoVivienda
DROP TABLE IF EXISTS tipovivienda;
CREATE TABLE tipovivienda(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);
-- insertar datos
INSERT INTO tipovivienda(id, nombre ) VALUES
(1,"Apartamento"),
(2,"Ático"),
(3,"Casa");
-- ------------------------------------------------------------------------
-- Creamos la tabla vivienda
DROP TABLE IF EXISTS vivienda;
CREATE TABLE vivienda(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_propietario INT NOT NULL,
	codigo VARCHAR(10) NOT NULL UNIQUE,
    tipo INT NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    superficie INT NOT NULL,
    precio_mes DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    acepta_mascota BOOL NOT NULL,
    FOREIGN KEY (tipo) REFERENCES tipovivienda(id),
    FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);
-- ---------------------------------------------------------------------
-- Creamos la tabla inquilino 
DROP TABLE IF EXISTS inquilino;
CREATE TABLE inquilino(
    id INT AUTO_INCREMENT PRIMARY KEY,
    DNI CHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tiene_mascota BOOL
);
-- ---------------------------------------------------------------------
-- Creamos la tabla tipoestado
DROP TABLE IF EXISTS tipoestado;
CREATE TABLE tipoestado(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);
-- insertar datos
INSERT INTO tipoestado(id, nombre ) VALUES
(1,"Pendiente"),
(2,"Vencido"),
(3,"Activo");
-- ---------------------------------------------------------------------------------
-- creamos la tabla contrato
DROP TABLE IF EXISTS contrato;
CREATE TABLE contrato(
	id_contrato INT AUTO_INCREMENT PRIMARY KEY,
    id_vivienda INT NOT NULL,
    id_inquilino INT NOT NULL,
    fecha_inicio DATE NOT NULL ,
    fecha_fin DATE NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    estado INT NOT NULL,
    FOREIGN KEY(id_vivienda) REFERENCES vivienda(id),
        FOREIGN KEY (id_inquilino) REFERENCES inquilino(id),
        FOREIGN KEY (estado) REFERENCES tipoestado(id)
);
-- ----------------------------------------------------------------------------------
-- ---------------------------------------------------------
-- PROPIETARIOS
INSERT INTO propietario (DNI, nombre, telefono, email) VALUES
('12345678A', 'Juan García', '600111111', 'juan@gmail.com'),
('23456789B', 'María López', '600222222', 'maria@gmail.com'),
('34567890C', 'Pedro Sánchez', '600333333', 'pedro@gmail.com'),
('45678901D', 'Ana Martínez', '600444444', 'ana@gmail.com');

-- ---------------------------------------------------------
-- VIVIENDAS
INSERT INTO vivienda (
    id_propietario,
    codigo,
    tipo,
    direccion,
    superficie,
    precio_mes,
    descripcion,
    acepta_mascota
) VALUES
(1, 'V001', 1, 'Calle Mayor 10, Madrid', 75, 850.00,
 'Apartamento céntrico con balcón', TRUE),

(1, 'V002', 2, 'Avenida Europa 25, Valencia', 120, 1400.00,
 'Ático con terraza y vistas al mar', TRUE),

(2, 'V003', 3, 'Calle Sol 15, Alicante', 180, 1800.00,
 'Casa independiente con jardín', FALSE),

(3, 'V004', 1, 'Calle Luna 8, Murcia', 60, 650.00,
 'Apartamento reformado recientemente', TRUE),

(4, 'V005', 3, 'Calle Jardines 22, Sevilla', 200, 2100.00,
 'Casa amplia con piscina', TRUE);

-- ---------------------------------------------------------
-- INQUILINOS
INSERT INTO inquilino (
    DNI,
    nombre,
    telefono,
    email,
    tiene_mascota
) VALUES
('56789012E', 'Carlos Ruiz', '611111111',
 'carlos@gmail.com', TRUE),

('67890123F', 'Laura Gómez', '622222222',
 'laura@gmail.com', FALSE),

('78901234G', 'Miguel Torres', '633333333',
 'miguel@gmail.com', TRUE),

('89012345H', 'Sofía Navarro', '644444444',
 'sofia@gmail.com', FALSE),

('90123456I', 'David Romero', '655555555',
 'david@gmail.com', TRUE);

-- ---------------------------------------------------------
-- CONTRATOS
INSERT INTO contrato (
    id_vivienda,
    id_inquilino,
    fecha_inicio,
    fecha_fin,
    precio,
    estado
) VALUES
(1, 1, '2026-01-01', '2026-12-31', 850.00, 3),
(2, 2, '2025-01-01', '2025-12-31', 1400.00, 2),
(3, 3, '2026-03-01', '2027-02-28', 1800.00, 3),
(4, 4, '2026-07-01', '2027-06-30', 650.00, 1),
(5, 5, '2026-04-15', '2027-04-14', 2100.00, 3);