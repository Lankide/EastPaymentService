package ua.globallogic.eastpaymentservice.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.globallogic.eastpaymentservice.domain.Bank;
import ua.globallogic.eastpaymentservice.domain.Currency;
import ua.globallogic.eastpaymentservice.repository.BankRepository;

import java.util.Collection;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernateBankRepository implements BankRepository {

    HibernateTemplate template;

    @Autowired
    public HibernateBankRepository(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    private Session getSession() {
        return template.getSessionFactory().getCurrentSession();
    }

    @Override
    public Bank getBank(int id) {
        return (Bank) getSession().get(Bank.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Bank> getBanks() {
        return getSession().createQuery("from Bank ").list();
    }

    @Override
    public void saveBank(Bank bank) {
        getSession().save(bank);
    }

    @Override
    public void deactivateBank(Bank bank) {

    }

    @Override
    public void updateBank(Bank bank) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Currency> getCurrencies() {
        return getSession().createQuery("from Country ").list();
    }

    @Override
    public String getCountryCode2(Integer bankId) {
        return null;
    }
}
