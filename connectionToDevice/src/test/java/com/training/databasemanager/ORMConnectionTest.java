package com.training.databasemanager;

import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.training.databacemanager.DataTypesORM;
import com.training.databacemanager.HibernateUtil;
import com.training.databacemanager.ORM;
import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.model.*;
import com.training.deviceoperation.parser.*;

/**
 * 
 * @author user
 *
 */
public class ORMConnectionTest {
	private Exception exception;
	private EthernetProtocolEndpoint ePEObject;
	private PolicyMap policymap;
	private ACL acl;
	private ORM ORMConnection;
	private ClassMap classmap;
	private Interface_ACL interACL;
	SessionFactory sf;
	Session session;

	@Before
	public void setup() {
		ORMConnection = new ORM();
		ePEObject = new EthernetProtocolEndpoint("GigabitEthernet0/0/0", Status.up, Status.up, 1500, "1000",
				DuplexMode.Full, "503d.e596.7400");
		policymap = new PolicyMap("policy1", "calss1");
		acl = new ACL("Standard", 1, 10, "any", "", "", "");
		classmap = new ClassMap("class1", "match-all", "first class", "none", "");
		interACL = new Interface_ACL(Direction.out, "11", "11");
		sf = HibernateUtil.getSessionfactory();
		session = sf.openSession();

	}

	@Test
	public void testInsertToDatabase() {

		exception = null;

		try {
			// send the data to the dataBase
			// ORMConnection.insert(policymap);
			// ORMConnection.insert(ePEObject);
			ORMConnection.insert(acl);
			ORMConnection.insert(classmap);
			// ORMConnection.insert(interACL);

			session.beginTransaction();
			Set<ClassMap> classm = new HashSet<ClassMap>();
			classm.add(classmap);
			acl.setClassMapList(classm);
			session.save(acl);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		}
		assertNull("Insert is not successfull", exception);
	}

}
