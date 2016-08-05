package com.training.databacemanager;

import java.sql.Connection;

import com.training.databacemanager.exception.CRUDException;

public interface DatabaseManager {
Connection connectToDatabase();
boolean disconnectToDataBase();
boolean insert(Object obj)throws CRUDException;
void delete(Object obj,String tableName);
	
}
