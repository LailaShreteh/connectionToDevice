package com.training.databacemanager;

import java.sql.Connection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.databacemanager.exception.CRUDException;

@Repository
public class ORM implements DatabaseManager {

	@Autowired
	private SessionFactory sf;

	@Override
	public Connection connectToDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean disconnectToDataBase() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Object obj) throws CRUDException {
		sf.getCurrentSession().save(obj);
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		sf.getCurrentSession().delete(obj);
		return false;
	}

	@Override
	public boolean edit(Object obj) {
		sf.getCurrentSession().update(obj);
		return false;
	}

}
