package rr.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/restdb";
	private final static String USER = "root";
	private final static String PASSWORD = "root";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is now loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connectToDB(){
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection is successful");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error establishing the connection: " + e.getMessage());
		}
		return con; 
	}
	
	public static void terminate (PreparedStatement ps, ResultSet rs, Connection con) {
		try {
			if(ps != null)
			{
				ps.close();
			}
			if(rs != null)
			{
				rs.close();
			}
			if(con != null)
			{
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DBConnection.connectToDB();
	}
}
