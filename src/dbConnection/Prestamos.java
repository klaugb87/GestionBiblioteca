package dbConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prestamos {
	    public static void registrarPrestamo(int idISBN, String FechaInicio, String FechaFin, int idUsuario) {
	    	try(Connection connection = DBConnection.getInstance().getConnection())
	        {        	
	            final String REGISTRA_PRESTAMO_SQL = "CALL sp_registraPrestamo(?,?,?,?)";
	            CallableStatement statement = connection.prepareCall(REGISTRA_PRESTAMO_SQL);
	            statement.setInt(1,idISBN);
	            statement.setString(2, FechaInicio);
	            statement.setString(3, FechaFin);
	            statement.setInt(4, idUsuario);
	            statement.execute();
	            System.out.println(" Prestamo Registrado exitosamente");
	            }
	         catch (SQLException e) {
	        	System.out.println("Fallo al conectar a la base de datos");
	 			e.printStackTrace();
	        }
	    }
	    public static void obtenerPrestamos() {
	    	final String CONSULTAR_PRESTAMOS_ALL_SQL = "CALL sp_consultaPrestamos";
	    	try(Connection connection = DBConnection.getInstance().getConnection();
	        	Statement st = connection.createStatement();
	    		CallableStatement statement = connection.prepareCall(CONSULTAR_PRESTAMOS_ALL_SQL)) {
	        	ResultSet rs = statement.executeQuery();
	        	int filas= 0;
	            while (rs.next()) {
	                System.out.println(rs.getInt(1) + "| " + rs.getString(2)+ " |" + rs.getString(3)+ " |" + rs.getInt(4));
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
	    	System.out.println("var sp=" + CONSULTAR_PRESTAMOS_SQL + idUsuario_1);
	    	try(Connection connection = DBConnection.getInstance().getConnection()){
	    		CallableStatement statement = connection.prepareCall(CONSULTAR_PRESTAMOS_SQL);
	    		statement.setInt(1,idUsuario_1);
	        	ResultSet rs = statement.executeQuery();
	        	int filas= 0;
	        	while (rs.next()) {
	                System.out.println("|ID LIBRO= " + rs.getInt(1) + " |FECHA SALIDA= " + rs.getString(2)+ " |FECHA ENTRADA= " + rs.getString(3)+ " |ID USUARIO= " + rs.getInt(4));
	            }
	        	if(filas== 0) {
	        		System.out.println("El usuario no tiene prestamos asignados");
	        	}
	        } catch (SQLException e) {
	        	System.out.println("Fallo al conectar a la base de datos");
	 			e.printStackTrace();
	        }
	    }
	    public static void registraDevolucion(int idISBN, String FechaDevolucion, int idUsuario) {
	    	try(Connection connection = DBConnection.getInstance().getConnection())
	        {
	            final String REGISTRA_PRESTAMO_SQL = "CALL sp_registraDevolucion(?,?,?)";
	            CallableStatement statement = connection.prepareCall(REGISTRA_PRESTAMO_SQL);
	            statement.setInt(1,idISBN);
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
	    public static void consultaDisponibilidadLibro(int ISBN) {
	    	//
	    	String VERIFICA_DISPONIBILIDAD = "CALL sp_consultaDisponibilidadLibro(?)";	    	
	    	try(Connection connection = DBConnection.getInstance().getConnection();
	    		Statement st = connection.createStatement();
	    		CallableStatement statement = connection.prepareCall(VERIFICA_DISPONIBILIDAD)) {
	    		statement.setInt(1,ISBN);
	    		ResultSet rs = statement.executeQuery();
	        	System.out.println(VERIFICA_DISPONIBILIDAD);
	        	System.out.println("Resultados--->");
	        	int numFilas=0;
	        	while (rs.next()) {
	            	System.out.println("Libro no disponible--->");
	                System.out.println("|ID LIBRO= " + rs.getInt(1) + " |Fecha inicio prestamo= " + rs.getString(2) + " |Fecha inicio prestamo= " + rs.getString(3));
	                numFilas=numFilas + 1;
	            }
	        	if(numFilas== 0) {
	        		System.out.println("Libro disponible, no hay prestamo registrado");
	        	}
	            
	        } catch (SQLException e) {
	        	System.out.println("Fallo al conectar a la base de datos");
	 			e.printStackTrace();
	        }
	    }
	    
	}

