package com.training.deviceoperation.deviceconnection.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.training.deviceoperation.parser.EthernetProtocolEndpoint;

public class connectionToMySQL {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/interfaces";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "laila";
	static Connection conn = null;
	


	public connectionToMySQL() {
		 try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void insert(EthernetProtocolEndpoint ePE) throws SQLException {
		   Statement stmt = null;
		   stmt = conn.createStatement();
		   String sql = "INSERT INTO interface " +
                   "VALUES ('"+ ePE.getName() +"' , '"+ ePE.getAdminStatus() +"', '"+ePE.getOperStatus()+"', "+ePE.getMtu()+", '"+ePE.getDuplexMode()+"', '"+ePE.getIfSpeed()+"', '"+ePE.getMacAddress()+"')";
//		   String sql = "INSERT INTO interface VALUES ('reem', 'up' , 'down',1234,'full','1234kps','192.168.8.8' )";
		   stmt.executeUpdate(sql);
		   conn.close();
	}

	public static void select() {
		Statement stmt = null;
		   try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM interface ");
			 while ( rs.next() ) {
	             String name = rs.getString("name");
	             String adminStatus = rs.getString("adminStatus");
	             String operationalStatus = rs.getString("operationalStatus");
	             int MTU = rs.getInt("MTU");
	             String duplexMode = rs.getString("duplex");
	             String ifSpeed = rs.getString("speed");
	             String macAddress = rs.getString("macAddress");

	             System.out.println("**" + name + " || \t" + adminStatus + " || \t" + operationalStatus + " || \t" + MTU + " || \t" + duplexMode
					+ " || \t" + ifSpeed + " || \t" + macAddress);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		   try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
