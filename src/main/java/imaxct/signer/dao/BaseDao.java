package imaxct.signer.dao;

import imaxct.signer.misc.SessionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Base DAO
 * Created by maxct on 2016/8/16.
 */
public class BaseDao<T> {
    private static final Logger logger = Logger.getLogger(BaseDao.class);

    public boolean update(String hql, Object... objects){
        Session session = SessionUtil.getSession();
        try{
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (objects != null && objects.length > 0)
                for (int i=0; i<objects.length; ++i)
                    query.setParameter(i, objects[i]);
            int tot = query.executeUpdate();
            t.commit();
            return tot > 0;
        }catch (Exception e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean create(T obj){
        Session session = SessionUtil.getSession();
        try{
            Transaction t = session.beginTransaction();
            session.persist(obj);
            t.commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean createOrUpdate(T obj){
        Session session = SessionUtil.getSession();
        try{
            Transaction t = session.beginTransaction();
            session.saveOrUpdate(obj);
            t.commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean update(T obj){
        Session session = SessionUtil.getSession();
        try{
            Transaction t = session.beginTransaction();
            session.update(obj);
            t.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    public void merge(T obj){
        Session session = SessionUtil.getSession();
        try{
            Transaction t = session.beginTransaction();
            session.merge(obj);
            t.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public boolean delete(T obj){
        Session session = SessionUtil.getSession();
        try{
            Transaction t = session.beginTransaction();
            session.delete(obj);
            t.commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public T find(Class<? extends T>clazz, Serializable id){
        Session session = SessionUtil.getSession();
        try{
            session.beginTransaction();
            return (T) session.get(clazz, id);
        }finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> listSql(Class<? extends T>clazz, String sql){
        Session session = SessionUtil.getSession();
        try{
            session.beginTransaction();
            return session.createSQLQuery(sql).addEntity(clazz).list();
        }finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> listSql(Class<? extends T>clazz, String sql, int start, int pageListNum){
        Session session = SessionUtil.getSession();
        try{
            session.beginTransaction();
            return session.createSQLQuery(sql)
                    .addEntity(clazz)
                    .setFirstResult(start)
                    .setMaxResults(pageListNum)
                    .list();
        }finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> listHql(String hql) {
        Session session = SessionUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            return session.createQuery(hql).list();
        } finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> listHql(String hql, Object... objects){
        Session session = SessionUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            if (objects != null)
                for (int i=0; i<objects.length; ++i)
                    query.setParameter(i, objects[i]);
            return query.list();
        } finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> listHql(String hql, int start, int pageListNum) {
        Session session = SessionUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            return session.createQuery(hql)
                    .setFirstResult(start)
                    .setMaxResults(pageListNum)
                    .list();
        } finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> list(Class clazz, int start, int pageListNum) {
        Session session = SessionUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            return session.createQuery("from " + clazz.getName() + " ")
                    .setFirstResult(start)
                    .setMaxResults(pageListNum)
                    .list();
        } finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public T uniqueResult(String hql, Object... objects) {
        Session session = SessionUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            if (objects != null)
                for (int i=0; i<objects.length; ++i)
                    query.setParameter(i, objects[i]);
            return (T) query.uniqueResult();
        } finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public T uniqueResultSql(String sql, Object... objects) {
        Session session = SessionUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            if (objects != null)
                for (int i=0; i<objects.length; ++i)
                    query.setParameter(i, objects[i]);
            return (T) query.uniqueResult();
        } finally {
            session.getTransaction().commit();
        }
    }

    public void template(SessionProcessor sp) {
        Session session = SessionUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            sp.process(session);
        } finally {
            session.getTransaction().commit();
        }
    }
}
