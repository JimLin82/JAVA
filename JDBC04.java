package Mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC04 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try {
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/eeit53",prop);
		//3. SQL statement
			String sql1 = "INSERT INTO cust (cname,tel,birthday) VALUES" 
					+"('Jim3','789','2001-01-10'),"
					+"('Jim3','789','2001-01-10'),"
					+"('Jim3','789','2001-01-10')";
			String sql2 = "DELETE FROM cust WHERE id >= 4";
			String sql3 = "UPDATE cust SET cname='eric', tel='777' WHERE id = 3";
			
			Statement stmt =conn.createStatement();
			int count =	stmt.executeUpdate(sql3);
			System.out.println(count);
			conn.close();
			System.out.println("OK2");
		} catch (SQLException e2) {
			System.out.println(e2);
		
		}
		

			
			
			
		
		}
	}


