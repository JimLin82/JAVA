package Mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC01 {

	public static void main(String[] args) {
		//1. Load Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("ok");
		} catch (ClassNotFoundException e) {
			
			System.out.println(e);
		//2. create connection
	
		try {
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/eeit53?user=root%password=root");
			
		//3. SQL statement
			Statement stmt =conn.createStatement();
			stmt.execute(
					"INSERT INTO cust (cname,tel,birthday) VALUES ('Jim3','789','2001-01-10');");
			conn.close();
			System.out.println("OK2");
		} catch (SQLException e2) {
			System.out.println(e);
		
		}
		

			
			
			
		
		}
	}

}
