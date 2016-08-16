package imaxct.signer.dao;

import imaxct.signer.misc.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Base DAO
 * Created by maxct on 2016/8/16.
 */
public class BaseDao<T> {

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
            session.getTransaction().rollback();
            return false;
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
    //TODO
}
