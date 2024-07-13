CREATE DATABASE DB_BIBLIOTECA;
USE DB_BIBLIOTECA;

CREATE TABLE INVENTARIO(
titulo varchar(110),
autor varchar(100),
ISBN INT AUTO_INCREMENT PRIMARY KEY
);
SELECT * FROM INVENTARIO ;


CREATE TABLE USUARIOS(
nombre varchar(180),
ID_USUARIO INT AUTO_INCREMENT PRIMARY KEY
);

SELECT * FROM USUARIOS;

CREATE TABLE PRESTAMOS (
    ISBN_ID int NOT NULL,
    FechaInicioPrestamo date NOT NULL,
    FechaFinPrestamo date NOT NULL,
    Usuario_ID int,
    PRIMARY KEY (ISBN_ID),
    FOREIGN KEY (Usuario_ID) REFERENCES USUARIOS(ID_USUARIO)
    ON DELETE CASCADE
    
);

SELECT * FROM PRESTAMOS;

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminaLibro`(
ISBN_2 int)
BEGIN
DELETE FROM INVENTARIO WHERE ISBN= ISBN_2;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `inserta_libro`(
titulo_2 char(110), 
    autor_2 char(100))
BEGIN
INSERT INTO INVENTARIO (titulo, autor) VALUES (titulo_2, autor_2);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizaLibro`(
titulo_n char(110),
autor_n char(100),
isbn_n int
)
BEGIN
UPDATE INVENTARIO SET autor = autor_n , titulo = titulo_n WHERE ISBN = isbn_n; 
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_muestraLibros`(
)
BEGIN
SELECT * FROM INVENTARIO;
END
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertaUsuario`(
nombre_n char(180))
BEGIN
INSERT INTO USUARIOS (nombre) VALUES (nombre_n);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizaUsuario`(
nombre_n char(180),
idUsuario_n int)
BEGIN
UPDATE USUARIOS SET nombre = nombre_n WHERE ID_USUARIO = idUsuario_n; 
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_muestraUsuarios`()
BEGIN
SELECT * FROM USUARIOS;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminaUsuario`(
ID_USUARIO_N int)
BEGIN
DELETE FROM USUARIOS WHERE ID_USUARIO= ID_USUARIO_N;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registraPrestamo`(
idISBN_n int,
FechaInicio_n date,
FechaFin_n date,
idUsuario_n int)
BEGIN
INSERT INTO PRESTAMOS (ISBN_ID, FechaInicioPrestamo,FechaFinPrestamo,Usuario_ID) VALUES (idISBN_n, FechaInicio_n,FechaFin_n,idUsuario_n);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registraDevolucion`(
idISBN int,
FechaDev date,
idUsuario_n int)
BEGIN
 UPDATE PRESTAMOS SET FechaFinPrestamo = FechaDev WHERE ISBN_ID =idISBN AND Usuario_ID =idUsuario_n;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consultaPrestamos`()
BEGIN
SELECT * FROM PRESTAMOS;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ObtenerPrestamosUsuario`(
idUsuario_n int)
BEGIN
SELECT * FROM PRESTAMOS WHERE Usuario_ID= idUsuario_n;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consultaDisponibilidadLibro`(
idISBN_n int)
BEGIN
SELECT * FROM PRESTAMOS WHERE ISBN_ID = idISBN_n AND (SELECT CURRENT_DATE() BETWEEN FechaInicioPrestamo AND FechaFinPrestamo);
END



