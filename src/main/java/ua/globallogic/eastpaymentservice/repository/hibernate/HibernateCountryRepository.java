package ua.globallogic.eastpaymentservice.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.globallogic.eastpaymentservice.domain.Country;
import ua.globallogic.eastpaymentservice.repository.CountryRepository;

import java.util.Collection;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernateCountryRepository implements CountryRepository {

    HibernateTemplate template;

    @Autowired
    public HibernateCountryRepository(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    private Session getSession() {
        return template.getSessionFactory().getCurrentSession();
    }

    @Override
    public Country getCountryByCode(String code) {
        return (Country) getSession().get(Country.class, code);
    }

    @Override
    public Country getCountryByCode2(String code2) {
        return (Country) getSession().createQuery("from Country where code2 =:code2").setParameter("code2", code2).uniqueResult();
    }

    @Override
    public Country getCountryByName(String name) {
        return (Country) getSession().createQuery("from Country where name =:name").setParameter("name", name).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Country> getCountries() {
        return getSession().createQuery("from Country ").list();
    }
}