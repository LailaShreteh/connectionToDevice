package com.training.databasemanager;

import org.junit.BeforeClass;

import com.training.databacemanager.JDBCConnection;

public class JDBCConnectionTest {
	static JDBCConnection con;

	@BeforeClass
	public static void createConnectionToSQL() {
		con = new JDBCConnection();
	}

	/*
	 * @Test public void testInsert() { con = new connectionToMySQL();
	 * 
	 * }
	 */

	/*
	 * @Test public void testUpdate() { con.update("GigabitEthernet0/0/3",
	 * "reemEthernet123"); }
	 * 
	 * @Test public void testDelete() { con.delete("GigabitEthernet0",
	 * TableName);
	 * 
	 * }
	 */
}
