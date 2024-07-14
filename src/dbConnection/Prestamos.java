package dbConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prestamos {
	final static int DUPLICATE_KEY_ERROR_CODE = 1062;
	    public static void registrarPrestamo(long idISBN, String FechaFin, int idUsuario) {
	    	try(Connection connection = DBConnection.getInstance().getConnection())
	        {        	
	            final String REGISTRA_PRESTAMO_SQL = "CALL sp_registraPrestamo(?,?,?)";
	            CallableStatement statement = connection.prepareCall(REGISTRA_PRESTAMO_SQL);
	            statement.setLong(1,idISBN);
	            statement.setString(2, FechaFin);
	            statement.setInt(3, idUsuario);
	            statement.execute();
	            System.out.println(" Prestamo Registrado exitosamente");
	            }
	         catch (SQLException e) {
	        	 if (((SQLException) e).getErrorCode() == DUPLICATE_KEY_ERROR_CODE) {
		                System.out.println("Este libro no está disponible");
		            } else {
			        	System.out.println("Fallo al conectar a la base de datos");
			 			e.printStackTrace();
		            }
	        }
	    }
	    public static void obtenerPrestamos() {
	    	final String CONSULTAR_PRESTAMOS_ALL_SQL = "SELECT * FROM vw_Prestamos";
	    	try(Connection connection = DBConnection.getInstance().getConnection();
	        	Statement st = connection.createStatement();
	    		CallableStatement statement = connection.prepareCall(CONSULTAR_PRESTAMOS_ALL_SQL)) {
	        	ResultSet rs = statement.executeQuery();
	        	int filas= 0;
	            while (rs.next()) {
	                System.out.println(rs.getLong(1) + "| " + rs.getString(2)+ " |" + rs.getString(3)+ " |" + rs.getInt(4));
	            }
	            if(filas== 0) {
	        		System.out.println("El usuario no tiene prestamos asignados");
	        	}
	        } catch (SQLException e) {
	        	System.out.println("Fallo al conectar a la base de datos");
	 			e.printStackTrace();
	        }
	    }
	    public static void obtenerPrestamosPorUsuario(int idUsuario_1) {
	    	final String CONSULTAR_PRESTAMOS_SQL = "CALL sp_ObtenerPrestamosUsuario(?)";
	    	try(Connection connection = DBConnection.getInstance().getConnection()){
	    		CallableStatement statement = connection.prepareCall(CONSULTAR_PRESTAMOS_SQL);
	    		statement.setInt(1,idUsuario_1);
	        	ResultSet rs = statement.executeQuery();
	        	int filas= 0;
	        	while (rs.next()) {
	                System.out.println("|ID LIBRO= " + rs.getLong(2) + " |FECHA SALIDA= " + rs.getString(3)+ " |FECHA ENTRADA= " + rs.getString(5));
	                filas++;
	            }
	        	if(filas== 0) {
	        		System.out.println("El usuario no tiene prestamos asignados");
	        	}
	        } catch (SQLException e) {
	        	System.out.println("Fallo al conectar a la base de datos");
	 			e.printStackTrace();
	        }
	    }
	    public static void registraDevolucion(long idISBN, String FechaDevolucion, int idUsuario) {
	    	try(Connection connection = DBConnection.getInstance().getConnection())
	        {
	            final String REGISTRA_PRESTAMO_SQL = "CALL sp_registraDevolucion(?,?,?)";
	            CallableStatement statement = connection.prepareCall(REGISTRA_PRESTAMO_SQL);
	            statement.setLong(1,idISBN);
	            statement.setString(2, FechaDevolucion);
	            statement.setInt(3, idUsuario);
	            statement.execute();
	            System.out.println("Devolucion Registrada");
	        } catch (SQLException e) {
	        	System.out.println("Fallo al conectar a la base de datos");
	 			e.printStackTrace();
	        }
	    }
	    //checar disponibilidad de un libro
	    public static boolean consultaDisponibilidadLibro(long ISBN) {
	    	String VERIFICA_DISPONIBILIDAD = "CALL sp_consultaDisponibilidadLibro(?, ?, ?)";	    	
	    	try(Connection connection = DBConnection.getInstance().getConnection();
	    		Statement st = connection.createStatement();
	    		CallableStatement statement = connection.prepareCall(VERIFICA_DISPONIBILIDAD)) {
	    		statement.setLong(1,ISBN);
	    		statement.registerOutParameter(2, java.sql.Types.INTEGER);
	    		statement.registerOutParameter(3, java.sql.Types.INTEGER);
	    		statement.execute();
	    		int prestamosAbiertos = statement.getInt(2);
	    		int existe = statement.getInt(3);

	        	return prestamosAbiertos == 0 && existe > 0;
	            
	    	} catch (SQLException e) {
	            if (((SQLException) e).getErrorCode() == DUPLICATE_KEY_ERROR_CODE) {
	                // Handle duplicate key violation
	                System.out.println("Valor duplicado, por favor intente otro");
	            } else {
	                // Print exception message
	                System.out.println(e.getMessage());}
	        }
	    	return false;
	    }
	}

