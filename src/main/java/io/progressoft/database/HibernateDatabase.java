//package io.progressoft.database;
//
//import com.mysql.cj.Session;
//import com.mysql.cj.xdevapi.SessionFactory;
//import org.hibernate.boot.Metadata;
//
//import java.lang.module.Configuration;
//
//public class HibernateDatabase {
//
//    private static SessionFactory sessionFactory;
//
//    private HibernateDatabase() {
//        sessionFactory = new Metadata()
//    }
//
//    public static SessionFactory getSessionFactory() {
//        try {
//            if (session == null) {
//
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Unable to get session factory", e);
//        }
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        // Close caches and connection pools
//        if (sessionFactory != null) {
//            sessionFactory.close();
//        }
//    }
//}
