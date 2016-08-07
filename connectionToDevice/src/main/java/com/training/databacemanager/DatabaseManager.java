package com.training.databacemanager;

import java.awt.List;
import java.sql.Connection;

import com.training.databacemanager.exception.CRUDException;

/**
 * 
 * @author user
 *
 */
public interface DatabaseManager {

	/**
	 * connectToDatabase method to establish a database connection using JDBC.
	 * 
	 * @return - database Connection object.
	 */
	Connection connectToDatabase();

	/**
	 * disconnectToDataBase method to close a database connection using
	 * connectionObject.close()
	 * 
	 * @return - to check if the connection is closed or NOT.
	 */
	boolean disconnectToDataBase();

	/**
	 * insert method to insert objects into the database tables by executing
	 * "INSERT INTO Table_Name" Queries through JDBC connection.
	 * 
	 * @param object
	 *            - an object from model class such as EthernetProtocolEndpoint,
	 *            ACL, ..etc
	 * @throws CRUDException
	 */
	boolean insert(Object obj) throws CRUDException;

	boolean delete(Object obj);
	boolean edit(Object obj);
	/*Object getObj (int id);
	List getAllObj ();*/
	


}
