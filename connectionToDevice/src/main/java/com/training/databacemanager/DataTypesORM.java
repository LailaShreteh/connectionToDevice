package com.training.databacemanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;

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
				// System.out.println(ePE);
				session.save(policymap);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.flush();
				session.close();
			}
		}

	};

	public abstract void insert(Object object) throws CRUDException;
}
