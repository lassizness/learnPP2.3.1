package pp.util;

import pp.entity.UsersEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class HiberCon {
    private static String loginDb = "postgres";
    private static String paswwdDb = "3444";
    private static String selectDb = "pp231";
    private static String pathDb = "jdbc:postgresql://localhost:5432/" + selectDb + "?user=" + loginDb + "&password=" + paswwdDb;
    //не красиво, но и хрен с ним.
    private static SessionFactory sessionFactory;//сессия кибернейта

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
                configuration.addAnnotatedClass(UsersEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}