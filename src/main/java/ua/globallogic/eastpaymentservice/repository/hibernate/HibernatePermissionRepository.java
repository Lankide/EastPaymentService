package ua.globallogic.eastpaymentservice.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.globallogic.eastpaymentservice.domain.Permission;
import ua.globallogic.eastpaymentservice.repository.PermissionRepository;

import java.util.Collection;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernatePermissionRepository implements PermissionRepository {

    HibernateTemplate template;

    @Autowired
    public HibernatePermissionRepository(SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }

    private Session getSession() {
        return template.getSessionFactory().getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Permission> getPermissions() {
        return getSession().createQuery("from Permission").list();
    }

    @Override
    public Permission getPermission(int id) {
        return (Permission) getSession().get(Permission.class, id);
    }

    @Override
    public Permission getPermission(String name) {
        return (Permission) getSession().createQuery("from Permission where name =:name").setParameter("name", name).uniqueResult();
    }
}
