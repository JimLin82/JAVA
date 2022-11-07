package Mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC03 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try {
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eeit53","root","root");
		//3. SQL statement
			String sql1 = "INSERT INTO cust (cname,tel,birthday) VALUES ('Jim3','789','2001-01-10');";
			String sql2 = "SELECT * FROM cust";
			
			
			
			Statement stmt =conn.createStatement();
			boolean b =	stmt.execute(
					sql2);
			System.out.println(b);
			conn.close();
			System.out.println("OK2");
		} catch (SQLException e2) {
			System.out.println(e2);
		
		}
		

			
			
			
		
		}
	}


