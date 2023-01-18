package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

	public static Connection openConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emart", "root", "swanand@123");
		return con;
	}

}

//  ("jdbc:mysql://localhost:3306/emart","root","swanand@123") 
//  Connection is a interface we create reference and DriverManager is a class in which
//  
//  getconnection is a static method and tries to establish connection betweeto the database with provided url
//  
//  Drivermanager class implicity loads the classes with the help of sql jar
//  drivers
  
  
 
