package imaxct.signer.misc;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.function.ClassicAvgFunction;
import org.hibernate.dialect.function.ClassicCountFunction;
import org.hibernate.dialect.function.ClassicSumFunction;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Session Utility for hibernate
 * Created by maxct on 2016/8/16.
 */
public class SessionUtil {
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();
    private static SessionFactory sessionFactory;
    private static Configuration configuration = new Configuration();
    private static ServiceRegistry serviceRegistry;
    private static Logger logger = Logger.getLogger(SessionUtil.class);
    static {
        try {
            configuration.configure();
            configuration.addSqlFunction("avg", new ClassicAvgFunction());
            configuration.addSqlFunction("count", new ClassicCountFunction());
            configuration.addSqlFunction("sum", new ClassicSumFunction());
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e){
            logger.error("session utility initialize failed.");
            e.printStackTrace();
        }
    }

    private SessionUtil(){

    }

    public static Session getSession() throws HibernateException{
        Session session = (Session) threadLocal.get();
        if (session == null || !session.isOpen()){
            if (sessionFactory == null)
                ;
            session = (sessionFactory !=null) ? sessionFactory.openSession():null;
            threadLocal.set(session);
        }
        return session;
    }

    public static void rebuildSessionFactory(){
        try{
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e){
            logger.error("rebuild session factory failed.");
            e.printStackTrace();
        }
    }

    public static void closeSession() throws HibernateException{
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);
        if (session != null)
            session.close();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static Configuration getConfiguration(){
        return configuration;
    }
}
