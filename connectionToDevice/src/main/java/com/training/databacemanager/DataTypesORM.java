package com.training.databacemanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.model.*;

public enum DataTypesORM {
	policymap {

		@Override
		public void insert(Object object) throws CRUDException {
			// TODO Auto-generated method stub
			SessionFactory sf = HibernateUtil.getSessionfactory();
			Session session = sf.openSession();
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
				// session.flush();
				session.close();
			}
		}

		@Override
		public Object getObj(int id) {
			SessionFactory sf = HibernateUtil.getSessionfactory();
			return sf.getCurrentSession().get(PolicyMap.class, id);
		}

	},
	interface_acl {

		@Override
		public void insert(Object object) throws CRUDException {
			// TODO Auto-generated method stub
			SessionFactory sf = HibernateUtil.getSessionfactory();
			Session session = sf.openSession();
			Transaction tx = null;

			try {
				tx = session.beginTransaction();
				Interface_ACL interACL = (Interface_ACL) object;
				session.save(interACL);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				// session.flush();
				session.close();
			}
		}

		@Override
		public Object getObj(int id) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	interface_ethernetprotocolendpoint {

		@Override
		public void insert(Object object) throws CRUDException {
			// TODO Auto-generated method stub
			SessionFactory sf = HibernateUtil.getSessionfactory();
			Session session = sf.openSession();
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
				// session.flush();
				session.close();
			}
		}

		@Override
		public Object getObj(int id) {
			SessionFactory sf = HibernateUtil.getSessionfactory();
			return sf.getCurrentSession().get(EthernetProtocolEndpoint.class, id);
		}
	},
	classmap {

		@Override
		public void insert(Object object) throws CRUDException {
			// TODO Auto-generated method stub
			SessionFactory sf = HibernateUtil.getSessionfactory();
			Session session = sf.openSession();
			Transaction tx = null;

			try {
				tx = session.beginTransaction();
				ClassMap classMap = (ClassMap) object;
				// System.out.println(DataTypesORM.classmap.getObj(10));

				session.save(classMap);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				// session.flush();
				session.close();
			}
		}

		@Override
		public Object getObj(int id) {
			SessionFactory sf = HibernateUtil.getSessionfactory();
			return sf.getCurrentSession().get(ClassMap.class, id);
		}
	},
	acl {

		@Override
		public void insert(Object object) throws CRUDException {
			// TODO Auto-generated method stub
			SessionFactory sf = HibernateUtil.getSessionfactory();
			Session session = sf.openSession();
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
				// session.flush();
				session.close();
			}
		}

		@Override
		public Object getObj(int id) {
			SessionFactory sf = HibernateUtil.getSessionfactory();
			return sf.getCurrentSession().get(ACL.class, id);
		}

	};

	public abstract void insert(Object object) throws CRUDException;

	public abstract Object getObj(int id);

}