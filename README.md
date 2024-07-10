///script base de datos
/*CREATE DATABASE DB_BIBLIOTECA;
USE DB_BIBLIOTECA;

CREATE TABLE INVENTARIO(
titulo varchar(110),
autor varchar(100),
ISBN INT AUTO_INCREMENT PRIMARY KEY
);

SELECT * FROM INVENTARIO where titulo like '%loco%' or autor like '%%';
SELECT * FROM INVENTARIO WHERE ISBN = 10;
UPDATE INVENTARIO SET autor = 'djkhj dafasdsdas', titulo= 'el polaco' WHERE ISBN = 3
INSERT INTO INVENTARIO(titulo,autor) VALUES ('La biblioteca de media noche','Matt Haig');


CREATE TABLE USUARIOS(
nombre varchar(180),
ID_USUARIO INT AUTO_INCREMENT PRIMARY KEY
);

SELECT * FROM USUARIOS;
/*INSERT INTO USUARIOS(nombre) VALUES ('Cosme Fulanito');*/

CREATE TABLE PRESTAMOS (
    ISBN_ID int NOT NULL,
    FechaInicioPrestamo date NOT NULL,
    FechaFinPrestamo date NOT NULL,
    Usuario_ID int,
    PRIMARY KEY (ISBN_ID),
    FOREIGN KEY (Usuario_ID) REFERENCES USUARIOS(ID_USUARIO)
    
);

SELECT * FROM PRESTAMOS;
/*INSERT INTO PRESTAMOS VALUES(10,'08-07-24','09-07-24',3); */
SELECT * FROM PRESTAMOS WHERE ISBN_ID = 11 AND ('2024-07-11'BETWEEN FechaInicioPrestamo AND FechaFinPrestamo);
SELECT CURRENT_DATE()

