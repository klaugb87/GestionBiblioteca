package dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prestamos {
	    public static void registrarPrestamo(int idISBN, String FechaInicio, String FechaFin, int idUsuario) {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87"))
	        {        	
	            final String REGISTRA_PRESTAMO_SQL = "INSERT INTO PRESTAMOS (ISBN_ID, FechaInicioPrestamo,FechaFinPrestamo,Usuario_ID) VALUES (?, ?, ?, ?)";
	            PreparedStatement preparedStatement2 = connection.prepareStatement(REGISTRA_PRESTAMO_SQL); {
	        	}
	            preparedStatement2.setInt(1,idISBN);
	            preparedStatement2.setString(2, FechaInicio);
	            preparedStatement2.setString(3, FechaFin);
	            preparedStatement2.setInt(4, idUsuario);

	            int rowAffected = preparedStatement2.executeUpdate();
	            System.out.println(rowAffected + " Prestamo Registrado exitosamente");
	            }
	         catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void obtenerPrestamos() {
	    	final String CONSULTAR_PRESTAMOS_ALL_SQL = "SELECT * FROM PRESTAMOS ";
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
	        	Statement st = connection.createStatement();
	        	PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PRESTAMOS_ALL_SQL)) {
	        	ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                System.out.println(rs.getInt(1) + "| " + rs.getString(2)+ " |" + rs.getString(3)+ " |" + rs.getInt(4));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void obtenerPrestamosPorUsuario(int idUsuario) {
	    	final String CONSULTAR_PRESTAMOS_SQL = "SELECT * FROM PRESTAMOS WHERE ISBN = " + idUsuario;
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
	        	Statement st = connection.createStatement();
	        	PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PRESTAMOS_SQL)) {
	        	ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                System.out.println(rs.getInt(1) + "| " + rs.getString(2)+ " |" + rs.getString(3)+ " |" + rs.getInt(4));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void registraDevolucion(int idISBN, String FechaDevolucion, int idUsuario) {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87"))
	        {
	            final String ACTUALIZAR_LIBRO_SQL_2 = "UPDATE PRESTAMOS SET FechaFinPrestamo = ? WHERE ISBN = ? AND Usuario_ID =?";
	            PreparedStatement preparedStatement2 = connection.prepareStatement(ACTUALIZAR_LIBRO_SQL_2); {
	            preparedStatement2.setString(3, FechaDevolucion);
	        	}
	            int rowAffected2 = preparedStatement2.executeUpdate();
	            System.out.println("Rows affected: " + rowAffected2);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    //checar disponibilidad de un libro
	    public static void consultaDisponibilidadLibro(int ISBN) {
	    	//
	    	String VERIFICA_DISPONIBILIDAD = "SELECT * FROM PRESTAMOS WHERE ISBN = " + ISBN + "AND (SELECT CURRENT_DATE() BETWEEN FechaInicioPrestamo AND FechaFinPrestamo";	    	
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_BIBLIOTECA", "root", "Autumn!87");
	            PreparedStatement preparedStatement = connection.prepareStatement(VERIFICA_DISPONIBILIDAD)) {
	        	System.out.println("Resultados--->");
	        	ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	System.out.println("Libro no disponible--->");
	                System.out.println("|ID LIBRO= " + rs.getInt(1) + " |Fecha inicio prestamo= " + rs.getString(2) + " |Fecha inicio prestamo= " + rs.getString(3));
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	}

