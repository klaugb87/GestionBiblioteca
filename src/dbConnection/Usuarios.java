package dbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Usuarios {
    public static void eliminarUsuario(int id) {
    	try(Connection connection = DBConnection.getInstance().getConnection())
        {        	
            final String ELIMINAR_USUARIO_SQL = "CALL sp_eliminaUsuario(?)";
            CallableStatement statement = connection.prepareCall(ELIMINAR_USUARIO_SQL);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Usuario eliminado exitosamente");
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
 			e.printStackTrace();
        }
    }
    public static void obtenerUsuarios() {
    	final String OBTENER_USUARIO_SQL = "CALL sp_muestraUsuarios";
    	try(Connection connection = DBConnection.getInstance().getConnection()){
    		CallableStatement statement = connection.prepareCall(OBTENER_USUARIO_SQL);
        	ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(2) + " " + rs.getString(1));
            }
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
 			e.printStackTrace();
        }
    }
    public static void actualizaUsuario(int idUsuario, String nombreUsuario) {
    	try(Connection connection = DBConnection.getInstance().getConnection())
        {
            final String ACTUALIZAR_USUARIO_SQL = "CALL sp_actualizaUsuario(?,?)";
            CallableStatement statement = connection.prepareCall(ACTUALIZAR_USUARIO_SQL); {
            statement.setString(1, nombreUsuario);
            statement.setInt(2, idUsuario);
        	}
            statement.execute();
            System.out.println("El usuario se actualizo correctamente ");
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
 			e.printStackTrace();
        }
    }

    public static void insertaUsuario(String nombre) {
    	final String INSERTAR_LIBRO_SQL = "CALL sp_insertaUsuario(?)";
    	try(Connection connection = DBConnection.getInstance().getConnection();
    		CallableStatement statement = connection.prepareCall(INSERTAR_LIBRO_SQL)) {
    		statement.setString(1, nombre);
            statement.execute();
            System.out.println("Registro exitoso del usuario");
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
 			e.printStackTrace();
        }
    }
    
    public static void buscarUsuario(int ID, String nombre) {
    	String BUSCAR_USUARIO = "SELECT * FROM USUARIOS WHERE ID_USUARIO = " + ID;
    	if(ID ==0 && nombre != "") {
    		BUSCAR_USUARIO = "SELECT * FROM USUARIOS WHERE nombre LIKE '%" + nombre + "%'";}

    	try(Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_USUARIO)) {
        	System.out.println("Resultados--->");
        	ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("|ID Usuario= " + rs.getInt(2) + " |Nombre= " + rs.getString(1));
            }
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
 			e.printStackTrace();
        }
    }
    
}

