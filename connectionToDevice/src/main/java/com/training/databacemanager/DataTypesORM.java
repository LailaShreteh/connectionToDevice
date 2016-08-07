package com.training.databacemanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.model.*;

public enum DataTypesORM {
	policymap {

		@Override
		public void insert(Object object) throws CRUDException {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.openSession();
			Transaction tx = null;

			try {
				tx = session.beginTransaction();
				PolicyMap policymap = (PolicyMap) object;
				session.save(policymap);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				//session.flush();
				session.close();
			}
		}

	},
	interface_ethernetprotocolendpoint{

		@Override
		public void insert(Object object) throws CRUDException {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.openSession();
			Transaction tx = null;

			try {
				tx = session.beginTransaction();
				EthernetProtocolEndpoint ePE = (EthernetProtocolEndpoint) object;
				session.save(ePE);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				//session.flush();
				session.close();
			}
		}
		},
		acl {

			@Override
			public void insert(Object object) throws CRUDException {
				// TODO Auto-generated method stub
				Session session = HibernateUtil.openSession();
				Transaction tx = null;

				try {
					tx = session.beginTransaction();
					ACL acl = (ACL) object;
					session.save(acl);
					tx.commit();
				} catch (HibernateException e) {
					if (tx != null)
						tx.rollback();
					e.printStackTrace();
				} finally {
					//session.flush();
					session.close();
				}
			}

		};

	
	

	public abstract void insert(Object object) throws CRUDException;

}