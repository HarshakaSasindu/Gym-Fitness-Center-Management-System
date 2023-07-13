package pro.pro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnecter {
public static Connection connect() throws SQLException {
		
		Connection con=null;
		try {
			Class.forName("org.sqlite.JDBC");
			con= DriverManager.getConnection("jdbc:sqlite:Solo.db");
			System.out.println("connect");
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	
		return con;
}
}