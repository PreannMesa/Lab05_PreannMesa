package database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pojo.Product;
import pojo.User;

public class MySQLHibernateConnUtils {
    private static final SessionFactory FACTORY;
    static {
        Configuration configuration =new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        FACTORY = configuration.buildSessionFactory(registry);
    }
    public static SessionFactory getFactory() {
        return FACTORY;
    }
}
