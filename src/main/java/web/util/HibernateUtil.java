package web.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(standardRegistry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Failed to create sessionFactory object: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
