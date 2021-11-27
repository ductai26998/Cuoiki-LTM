package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	
	public static final String URL = "jdbc:mysql://localhost:3306/Cuoiki?characterEncoding=UTF8";
	public static final String USER = "root";
	public static final String PASS = "";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASS);
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("Error connecting to the database", e);
		}
	}

}
