CREATE DATABASE DB_BIBLIOTECA;
USE DB_BIBLIOTECA;

CREATE TABLE INVENTARIO(
titulo varchar(110),
autor varchar(100),
ISBN bigint not null PRIMARY KEY
);
SELECT * FROM INVENTARIO;


CREATE TABLE USUARIOS(
nombre varchar(180),
ID_USUARIO INT AUTO_INCREMENT PRIMARY KEY
);

SELECT * FROM USUARIOS;

CREATE TABLE PRESTAMOS (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    ISBN_ID bigint NOT NULL,
    FechaInicioPrestamo date NOT NULL,
    FechaFinPrestamo date NOT NULL,
    FechaDevolucion date NOT NULL,
    Usuario_ID int,
    FOREIGN KEY (Usuario_ID) REFERENCES USUARIOS(ID_USUARIO)
    ON DELETE CASCADE    
);

SELECT * FROM PRESTAMOS;
/*libros*/
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminaLibro`(
ISBN_2 bigint)
BEGIN
DELETE FROM INVENTARIO WHERE ISBN= ISBN_2;
END//
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inserta_libro`(
titulo_2 char(110), 
    autor_2 char(100),
    id bigint)
BEGIN
INSERT INTO INVENTARIO (titulo, autor, ISBN) VALUES (titulo_2, autor_2, id);
END//
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizaLibro`(
titulo_n char(110),
autor_n char(100),
isbn_n bigint
)
BEGIN
UPDATE INVENTARIO SET autor = autor_n , titulo = titulo_n WHERE ISBN = isbn_n; 
END//
DELIMITER ;

CREATE VIEW vw_Libros AS 
(SELECT * FROM INVENTARIO);

/*usuarios*/
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertaUsuario`(
nombre_n char(180))
BEGIN
INSERT INTO USUARIOS (nombre) VALUES (nombre_n);
END//
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizaUsuario`(
nombre_n char(180),
idUsuario_n int)
BEGIN
UPDATE USUARIOS SET nombre = nombre_n WHERE ID_USUARIO = idUsuario_n; 
END //
DELIMITER ;

CREATE VIEW vw_Usuarios AS 
(SELECT * FROM USUARIOS);

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminaUsuario`(
ID_USUARIO_N int)
BEGIN
DELETE FROM USUARIOS WHERE ID_USUARIO= ID_USUARIO_N;
END //
DELIMITER ;

/*prestamos*/
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registraPrestamo`(
idISBN_n bigint,
FechaFin_n date,
idUsuario_n int)
BEGIN
INSERT INTO PRESTAMOS (ISBN_ID, FechaInicioPrestamo,FechaFinPrestamo,Usuario_ID) VALUES (idISBN_n, (SELECT CURRENT_DATE()),FechaFin_n,idUsuario_n);
END //
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registraDevolucion`(
idISBN bigint,
FechaDev date,
idUsuario_n int)
BEGIN
 UPDATE PRESTAMOS SET FechaDevolucion = FechaDev WHERE ISBN_ID =idISBN AND Usuario_ID =idUsuario_n;
END //
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consultaPrestamos`()
BEGIN
SELECT * FROM PRESTAMOS;
END //
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ObtenerPrestamosUsuario`(
idUsuario_n int)
BEGIN
SELECT * FROM PRESTAMOS WHERE Usuario_ID = idUsuario_n;
END //
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consultaDisponibilidadLibro`(
idISBN_n bigint,
out n int,
out exist int)
BEGIN
	SELECT
		count(*) into n
	FROM INVENTARIO i
	JOIN PRESTAMOS p ON i.ISBN = p.ISBN_ID
	WHERE
		i.ISBN = idISBN_n
		AND p.FechaDevolucion IS NULL;
	SELECT
		count(ISBN) into exist
	FROM INVENTARIO i
    WHERE ISBN = idISBN_n;
END //
DELIMITER ;

CREATE VIEW vw_Prestamos AS 
(SELECT * FROM PRESTAMOS);



