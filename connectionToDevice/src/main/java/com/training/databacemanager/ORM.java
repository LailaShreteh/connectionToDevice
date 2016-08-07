package com.training.databacemanager;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
		Session session = HibernateUtil.openSession();
		try {
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			/*
			 * try { throw new CRUDException(e.getMessage()); } catch
			 * (CRUDException e1) {
			 * 
			 * e1.printStackTrace(); }
			 */
		}
		return true;
	}

	@Override
	public boolean insert(Object obj) throws CRUDException {
		String className = obj.getClass().getSimpleName();
		String databaseTableName = TableName.valueOf(className).getTableName();
		try {
			DataTypesORM.valueOf(databaseTableName).insert(obj);
		} catch (CRUDException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// Logic
		}
		return true;

	}

	@Override
	public boolean delete(Object obj) {// here !!
		sf.getCurrentSession().delete(obj);
		return false;
	}

	@Override
	public boolean edit(Object obj) {
		sf.getCurrentSession().update(obj);
		return false;
	}

}
