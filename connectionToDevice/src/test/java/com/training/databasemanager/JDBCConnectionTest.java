package com.training.databasemanager;

import org.junit.BeforeClass;

import com.training.databacemanager.JDBC;

/**
 * 
 * @author user
 *
 */
public class JDBCConnectionTest {
	static JDBC con;

	@BeforeClass
	public static void createConnectionToSQL() {
		con = new JDBC();
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
