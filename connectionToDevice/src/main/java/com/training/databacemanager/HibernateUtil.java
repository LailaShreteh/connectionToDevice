package com.training.databacemanager;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.training.deviceoperation.deviceconnection.model.*;


public class HibernateUtil {
	 private static final  SessionFactory sessionFactory;
     private static final  ServiceRegistry serviceRegistry;
     static {
          try {
               Configuration config = getConfiguration();
               serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                         config.getProperties()).build();
              // BasicConfigurator.configure();

               config.setSessionFactoryObserver(new SessionFactoryObserver() {
                    private static final long  serialVersionUID = 1L;



                    @Override
                    public void sessionFactoryCreated(SessionFactory factory) {
                    }



                    @Override
                    public void sessionFactoryClosed(SessionFactory factory) {
                         StandardServiceRegistryBuilder.destroy(serviceRegistry);
                    }
               });
               sessionFactory = config.buildSessionFactory(serviceRegistry);
          } catch (Throwable ex) {
               System.err.println("Initial SessionFactory creation failed." + ex);
               throw new ExceptionInInitializerError(ex);
          }
     }
     public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}



	public static  Session openSession() {
          return sessionFactory.openSession();
     }



     private static  Configuration getConfiguration() {
          Configuration cfg = new Configuration();
          cfg.addAnnotatedClass(PolicyMap.class );
          cfg.addAnnotatedClass(ACL.class );
          cfg.addAnnotatedClass(EthernetProtocolEndpoint.class );
          cfg.addAnnotatedClass(ClassMap.class );
          cfg.addAnnotatedClass(Interface_ACL.class );
          cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
          cfg.setProperty("hibernate.connection.url","jdbc:mysql://localhost/RouterData_ORM");
          cfg.setProperty("hibernate.connection.username", "root");
          cfg.setProperty("hibernate.connection.password", "laila");
          cfg.setProperty("hibernate.show_sql", "true");
          cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
          cfg.setProperty("hibernate.hbm2ddl.auto", "update");
          cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
          cfg.setProperty("hibernate.current_session_context_class", "thread");
          
         // System.out.println(cfg.toString());
          return cfg;
     }
}

