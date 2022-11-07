package Mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC02 {

	public static void main(String[] args) {
		try {
			
		Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/eeit53","root","root");
		//3. SQL statement
			Statement stmt =conn.createStatement();
			stmt.execute(
					"INSERT INTO cust (cname,tel,birthday) VALUES ('Jim3','789','2001-01-10');");
			conn.close();
			System.out.println("OK2");
		} catch (SQLException e2) {
			System.out.println(e2);
		
		}
		

			
			
			
		
		}
	}


