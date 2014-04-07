package ua.globallogic.eastpaymentservice.repository.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.globallogic.eastpaymentservice.domain.UserSearchCriteria;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.repository.UserRepository;
import ua.globallogic.eastpaymentservice.security.utils.MD5Utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernateUserRepository implements UserRepository {

    HibernateTemplate template;

    @Autowired
    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    private Session getSession() {
        return template.getSessionFactory().getCurrentSession();
    }

    @Override
    public User getUser(int id) {
        return (User) getSession().get(User.class, id);
    }

    @Override
    public void deleteUser(User user) {
        getSession().delete(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = getUser(id);
        if (user != null) getSession().delete(user);
    }

    @Override
    public void updateUser(User user) {
        getSession().update(user);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(MD5Utils.encode(user.getPassword()));
        System.out.println(" id = " + getSession().save(user));
    }

    @Override
    public User getUser(String email) {
        return (User) getSession().createQuery("from User where email =:email").setParameter("email", email).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<User> getUsers() {
        return getSession().createQuery("from User ").list();
    }

    @Override
    public User getUser(Integer id) {
        return (User) getSession().get(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<User> findUsers(UserSearchCriteria criteria) {

        if (criteria.isEmpty()) {
            return null;
        }

        Criteria hibernateCriteria = getSession().createCriteria(User.class).addOrder(Order.asc("name"));

        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            hibernateCriteria.add(Restrictions.like("name", criteria.getName(), MatchMode.ANYWHERE));
        }
        if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
            hibernateCriteria.add(Restrictions.like("email", criteria.getEmail(), MatchMode.ANYWHERE));
        }
        if (criteria.getPhoneNumber() != null && !criteria.getPhoneNumber().isEmpty()) {
            hibernateCriteria.add(Restrictions.like("phoneNumber", criteria.getPhoneNumber(), MatchMode.ANYWHERE));
        }
        if (criteria.getCountry() != null) {
            hibernateCriteria.add(Restrictions.eq("country", criteria.getCountry()));
        }

        return new HashSet<User>(hibernateCriteria.list());
    }


    @Override
    public User findUser(String name) { // the method for the real pervert
        Criteria criteria = getSession().createCriteria(User.class).add(Restrictions.like("name", name, MatchMode.ANYWHERE)).addOrder(Order.asc("id")).setMaxResults(1);
        List<User> users = criteria.list();
        return users.isEmpty() ? null : users.get(0);
    }
}
