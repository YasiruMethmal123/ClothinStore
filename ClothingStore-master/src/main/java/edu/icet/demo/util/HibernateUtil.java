package edu.icet.demo.util;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import edu.icet.demo.entity.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.jaxb.spi.Binding;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory session = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addXmlBinding((Binding<?>) ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .buildMetadata();

        return (SessionFactory) metadata.getSessionFactoryBuilder().build();
    }
    public static Session getSession(){
        return session.getSession();
    }
}
