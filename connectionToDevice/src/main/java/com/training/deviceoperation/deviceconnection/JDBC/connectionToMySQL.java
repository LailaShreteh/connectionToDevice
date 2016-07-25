package com.training.deviceoperation.deviceconnection.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class connectionToMySQL {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "laila";

	public static void main(String[] args) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			System.out.println("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	  static void insert(String sqlSen)throws SQLException
      {
         Connection conn = null;
	   Statement stmt = null;
        long res = -1;
		try {

               //STEP 3: Open a connection
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

               //STEP 4: Execute a query
               System.out.println("Creating statement...");
              stmt = conn.createStatement();
              String sql =sqlSen;
			
			stmt.executeUpdate(sql);
                     PreparedStatement getLastInsertId = conn.prepareStatement("SELECT *from interface");  
                     ResultSet rs = getLastInsertId.executeQuery();
                     
//			if (rs.next())  
//			{  
//			 res = rs.getLong("last_insert_id()");
//			}
			rs.close();
			getLastInsertId.close();
			stmt.close();
			
		}
		catch(SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");
			  
		}
}
	  static void select(String sqlSen)
      {
        Connection conn = null;
	   Statement stmt = null;
         String res="";
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql =sqlSen;

	      ResultSet rs = stmt.executeQuery(sql);
            if(table!=null)
               
	      table.setModel(DbUtils.resultSetToTableModel(rs));
//	      //STEP 5: Extract data from result set
//	      while(rs.next()){
//	         //Retrieve by column name
//	         int supplierID = rs.getInt("supplierID");
//               int status =rs.getInt("status");
//	         String companyName  = rs.getString("companyName");
//               
//               res +="supplierID: " + supplierID+", status: "+status+", companyName: " + companyName+"\n";
//          
//               
//	         //Display values
//	        // System.out.print("supplierID: " + supplierID);
//	         //System.out.println(", companyName: " + companyName);
//	
//	      }
//	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("\nGoodbye!"); 
}
}
