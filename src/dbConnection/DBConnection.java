package dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	private static DBConnection instance;
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/DB_BIBLIOTECA";
	private String username = "root";
	private String password = "Autumn!87";
	
	private DBConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException exception) {
			throw new SQLException(exception.getMessage());
			//log de falló la conexión
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public static DBConnection getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBConnection();
		} else if(instance.getConnection().isClosed()) {
			instance = new DBConnection();
		}
		
		return instance;
	}
	
	
}
