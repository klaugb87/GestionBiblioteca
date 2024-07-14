package dbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Inventario {
    public static void eliminarLibro(int idISBN) {
    	try(Connection connection = DBConnection.getInstance().getConnection())
        {        	
            final String ELIMINAR_LIBRO_SQL = "CALL sp_eliminaLibro(?)";
            {
            CallableStatement statement = connection.prepareCall(ELIMINAR_LIBRO_SQL);
            statement.setInt(1, idISBN);
            statement.execute();
            System.out.println("Libro eliminado exitosamente");
            }
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
			e.printStackTrace();
        }
    }
    public static void obtenerLibros() {
    	final String CONSULTAR_LIBRO_SQL_1 = "SELECT * FROM vw_Libros";
    	try(Connection connection = DBConnection.getInstance().getConnection();
        	Statement st = connection.createStatement();
    		CallableStatement statement = connection.prepareCall(CONSULTAR_LIBRO_SQL_1)) {
        	ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(3) + " " + rs.getString(1)+ " " + rs.getString(2));
            }
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
			e.printStackTrace();
        }
    }
    public static void actualizaLibro(String nTitulo, String nAutor,int idISBN) {
    	try(Connection connection = DBConnection.getInstance().getConnection())
        {
            final String ACTUALIZAR_LIBRO_SQL_2 = "CALL sp_actualizaLibro(?,?,?) ";
            CallableStatement statement = connection.prepareCall(ACTUALIZAR_LIBRO_SQL_2); {
            statement.setString(1, nTitulo);
            statement.setString(2, nAutor);
            statement.setInt(3, idISBN);
        	}
            statement.execute();
            System.out.println("El registro se actualizo correctamente ");
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
			e.printStackTrace();
        }
    }

    public static void insertarLibro(String titulo, String autor) {
    	final String INSERTAR_LIBRO_SQL = "CALL sp_inserta_libro(?,?)";
    	try(Connection connection = DBConnection.getInstance().getConnection())
            {
        	CallableStatement statement = connection.prepareCall(INSERTAR_LIBRO_SQL);
        	statement.setString(1, titulo);
        	statement.setString(2, autor);
            statement.execute();
            System.out.println("Registro exitoso");

        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
			e.printStackTrace();
        }
    }
    
    public static void buscarLibro(int ISBN, String titulo, String autor) {
    	String BUSCAR_LIBROS = "SELECT * FROM INVENTARIO WHERE ISBN = ?";
    	if(ISBN ==0 && titulo != "") {
    		BUSCAR_LIBROS = "SELECT * FROM INVENTARIO WHERE titulo LIKE ?";}
    	else if(autor != "") {
    		BUSCAR_LIBROS = "SELECT * FROM INVENTARIO WHERE autor LIKE ?";
    	}
    	try(Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_LIBROS)) {
    		// Set parameter values using placeholder
    		if(ISBN!=0) {
    			preparedStatement.setInt(1, ISBN);
    		}
    		else if(titulo!= ""){
    			preparedStatement.setString(1, "%" + titulo + "%");
    		}
    		else if(autor!="") {
    			preparedStatement.setString(1, "%" + autor + "%");
    		}
    		
        	try (ResultSet rs = preparedStatement.executeQuery()){
        		int nFilas=0;
        		System.out.println("Resultados--->");
	            while (rs.next()) {
	                System.out.println("|ID= " + rs.getInt(3) + " |Titulo= " + rs.getString(1) + " |Autor= " + rs.getString(2));
	                nFilas=nFilas + 1;
	            }
	            if (nFilas==0) {
	            	System.out.println("No se encontro libro");
	            }
        	}
        } catch (SQLException e) {
        	System.out.println("Fallo al conectar a la base de datos");
			e.printStackTrace();
        }
    }
    
}

