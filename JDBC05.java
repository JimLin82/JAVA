package Mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC05 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try {
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eeit53",prop);
		//3. SQL statement
			String sql = "INSERT INTO cust (cname,tel,birthday) VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "mark");
			pstmt.setString(2, "122");
			pstmt.setString(3, "1997-01-02");
			pstmt.executeUpdate();
			
			pstmt.setString(1, "mark2");
			pstmt.setString(2, "333");
			pstmt.setString(3, "1997-01-02");
			pstmt.executeUpdate();
			
			conn.close();
			System.out.println("OK2");
		} catch (SQLException e2) {
			System.out.println(e2);
		
		}
		

			
			
			
		
		}
	}


