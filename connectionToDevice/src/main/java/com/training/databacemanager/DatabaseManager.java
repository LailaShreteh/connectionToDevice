package com.training.databacemanager;

public interface DatabaseManager {
boolean connectToDatabase();
boolean insert(Object obj,String tableName);
void delete(Object obj,String tableName);
	
}
