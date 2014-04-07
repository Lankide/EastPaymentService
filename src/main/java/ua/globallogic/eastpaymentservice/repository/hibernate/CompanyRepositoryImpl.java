package ua.globallogic.eastpaymentservice.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.globallogic.eastpaymentservice.domain.Company;
import ua.globallogic.eastpaymentservice.repository.CompanyRepository;


import java.util.Collection;

@Repository
@Transactional (propagation = Propagation.REQUIRED)
public class CompanyRepositoryImpl implements CompanyRepository {

    private HibernateTemplate template;

    @Autowired
    public CompanyRepositoryImpl(SessionFactory sessionFactory){
        this.template = new HibernateTemplate(sessionFactory);
    }

    private Session getSession() {
        return template.getSessionFactory().getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public Collection<Company> getAllCompanies(){
        return getSession().createQuery("from Company ").list();
    }
}
