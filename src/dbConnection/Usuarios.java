package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Usuarios {
    public static void eliminarUsuario(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87"))
        {        	
            final String ELIMINAR_USUARIO_SQL = "DELETE FROM USUARIOS WHERE ID_USUARIO =" +id ;
            PreparedStatement preparedStatement2 = connection.prepareStatement(ELIMINAR_USUARIO_SQL); {
        	}
            int rowsDeleted = preparedStatement2.executeUpdate();
            if (rowsDeleted != 0) {
                System.out.println("Usuario eliminado exitosamente");
            } else {
                System.out.println("No se pudo eliminar el usuario seleccionado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void obtenerUsuarios() {
    	final String OBTENER_USUARIO_SQL = "SELECT * FROM USUARIOS";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
        	Statement st = connection.createStatement();
        	PreparedStatement preparedStatement = connection.prepareStatement(OBTENER_USUARIO_SQL)) {
        	ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(2) + " " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void actualizaUsuario(int idUsuario, String nombreUsuario) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87"))
        {
            final String ACTUALIZAR_USUARIO_SQL = "UPDATE USUARIOS SET nombre = ? WHERE ID_USUARIO = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(ACTUALIZAR_USUARIO_SQL); {
            preparedStatement2.setString(1, nombreUsuario);
            preparedStatement2.setInt(2, idUsuario);
        	}
            int rowAffected2 = preparedStatement2.executeUpdate();
            System.out.println("Rows affected: " + rowAffected2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertaUsuario(String nombre) {
    	final String INSERTAR_LIBRO_SQL = "INSERT INTO USUARIOS (nombre) VALUES (?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_LIBRO_SQL)) {

            preparedStatement.setString(1, nombre);

            int rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected + "Registro exitoso del usuario");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void buscarUsuario(int ID, String nombre) {
    	String BUSCAR_USUARIO = "SELECT * FROM USUARIOS WHERE ID_USUARIO = " + ID;
    	if(ID ==0 && nombre != "") {
    		BUSCAR_USUARIO = "SELECT * FROM USUARIOS WHERE nombre LIKE '%" + nombre + "%'";}

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
            PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_USUARIO)) {
        	System.out.println("Resultados--->");
        	ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("|ID Usuario= " + rs.getInt(2) + " |Nombre= " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

