package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventario {
    public static void eliminarLibro(int idISBN) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87"))
        {        	
            final String ELIMINAR_LIBRO_SQL_2 = "DELETE FROM INVENTARIO WHERE ISBN =" +idISBN ;
            PreparedStatement preparedStatement2 = connection.prepareStatement(ELIMINAR_LIBRO_SQL_2); {
        	}
            int rowsDeleted = preparedStatement2.executeUpdate();
            if (rowsDeleted != 0) {
                System.out.println("Libro eliminado exitosamente");
            } else {
                System.out.println("No se pudo eliminar el libro seleccionado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void obtenerLibros() {
    	final String CONSULTAR_LIBRO_SQL_1 = "SELECT * FROM INVENTARIO";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
        	Statement st = connection.createStatement();
        	PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_LIBRO_SQL_1)) {
        	ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(3) + " " + rs.getString(1)+ " " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void actualizaLibro(int idISBN, String nTitulo, String nAutor) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87"))
        {
            final String ACTUALIZAR_LIBRO_SQL_2 = "UPDATE INVENTARIO SET autor = ? , titulo = ? WHERE ISBN = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(ACTUALIZAR_LIBRO_SQL_2); {
            preparedStatement2.setString(1, nTitulo);
            preparedStatement2.setString(2, nAutor);
            preparedStatement2.setInt(3, idISBN);
        	}
            int rowAffected2 = preparedStatement2.executeUpdate();
            System.out.println("Rows affected: " + rowAffected2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarLibro(String titulo, String autor) {
    	final String INSERTAR_LIBRO_SQL = "INSERT INTO INVENTARIO (titulo, autor) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_LIBRO_SQL)) {

            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, autor);

            int rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected + "Registro exitoso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void buscarLibro(int ISBN, String titulo, String autor) {
    	String BUSCAR_LIBROS = "SELECT * FROM INVENTARIO WHERE ISBN = " + ISBN;
    	if(ISBN ==0 && titulo != "") {
    		BUSCAR_LIBROS = "SELECT * FROM INVENTARIO WHERE titulo LIKE '%" + titulo + "%'";}
    	else if(autor != "") {
    		BUSCAR_LIBROS = "SELECT * FROM INVENTARIO WHERE autor LIKE '%" + autor + "%'";
    	}
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
            PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_LIBROS)) {
        	System.out.println("Resultados--->");
        	ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("|ID= " + rs.getInt(3) + " |Titulo= " + rs.getString(1) + " |Autor= " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

