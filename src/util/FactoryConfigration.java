package util;

import entity.Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class FactoryConfigration {
    private static FactoryConfigration factoryConfiguration;
    private final SessionFactory sessionFactory;

    public FactoryConfigration() {
        Configuration configuration = new Configuration();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        configuration.addAnnotatedClass(Student.class).addAnnotatedClass(Course.class);
        sessionFactory=configuration.buildSessionFactory(serviceRegistry);
    }

    public static FactoryConfigration getInstance(){
        return factoryConfiguration=factoryConfiguration==null?new FactoryConfigration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
