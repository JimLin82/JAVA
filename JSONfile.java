package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONfile {

	public static void main(String[] args) {

		try {
			URL url = new URL("https://media.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//				System.out.println(conn);
			conn.connect();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF8");
			BufferedReader reader = new BufferedReader(isr);
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			sb.delete(0, 1); // �R���Ĥ@���r ? �ݸ�
			parseJSON(sb.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void parseJSON(String json) {
		try {
			JSONObject row = new JSONObject(json);
			JSONArray info = row.getJSONObject("XML_Head").getJSONObject("Infos").getJSONArray("Info");
			toSql(info);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void toSql(JSONArray info) {
		String sql = "INSERT INTO tainan (name,tel,addr,city,Opentime,Toldescribe,picture1)" + 
					" VALUES (?,?,?,?,?,?,?)";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/travel?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
					prop);
			PreparedStatement pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < info.length(); i++) {
				JSONObject row = info.getJSONObject(i);
//				System.out.println(row.get("Region"));
				if (row.get("Region") == "臺南市") {
					
							pstmt.setString(1, row.getString("Name"));
							pstmt.setString(2, row.getString("Tel"));
						
							pstmt.setString(3, row.getString("Add"));
						
						
							pstmt.setString(4, row.getString("Region"));
						
						
							pstmt.setString(5, row.getString("Opentime"));
						
						
							pstmt.setString(6, row.getString("Toldescribe"));
						
						
							pstmt.setString(7, row.getString("Picture1"));
						
							pstmt.executeUpdate();
					
				}
			}
			System.out.println("ok");
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
