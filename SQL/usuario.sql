DROP USER IF EXISTS 'alquilaria'@'localhost';
CREATE USER 'alquilaria'@'localhost'
IDENTIFIED BY 'alquilaria123';

GRANT ALL PRIVILEGES
ON alquiler_viviendas.*
TO 'alquilaria'@'localhost';

FLUSH PRIVILEGES;
-- combrobar funcionamiento
SHOW GRANTS FOR 'alquilaria'@'localhost';

SHOW PROCEDURE STATUS
WHERE Db = 'alquiler_viviendas';