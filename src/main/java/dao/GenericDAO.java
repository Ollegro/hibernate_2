package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class GenericDAO<T> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public GenericDAO(Class<T> clazzToSet, SessionFactory sessionFactory) {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final int id){
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int count){
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);

        return query.getResultList();
    }

    public List<T> findAll(){
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public void save(final T entity){
        getCurrentSession().saveOrUpdate(entity);
    }

    public T update(final T entity){

        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity){
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int entityId){
        final T entity = getById(entityId);
        delete(entity);
    }

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();

    }
}
