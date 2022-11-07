package Mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JDBC08 {

	public static void main(String[] args) {
		

		
			
			Scanner scanner = new Scanner(System.in);
			System.out.print("page = ");
			int page = scanner.nextInt();
			
			Properties prop = new Properties();
			prop.put("user", "root");
			prop.put("password", "root");
			
			int rpp = 10;
			int start = (page -1) * rpp;
			
			
			try {
				Connection conn = 
						DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/eeit53", prop);
				
				String sql0 = "SELECT count(*) count FROM food";
				Statement stmt = conn.createStatement();
				ResultSet rs0 = stmt.executeQuery(sql0);
				rs0.next();
				
				
				
				String sql = String.format("SELECT* FROM food LIMIT %d, %d", start,rpp);
				PreparedStatement pstmt = conn.prepareStatement(sql);		
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					String name = rs.getString("name");
					String addr = rs.getString("addr");
					System.out.printf("%s : %s\n", name, addr);
				}

				rs.close();
				
				conn.close();
			} catch (Exception e) {
				
			}

		}




}
