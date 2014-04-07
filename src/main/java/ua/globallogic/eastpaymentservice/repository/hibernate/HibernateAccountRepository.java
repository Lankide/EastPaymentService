package ua.globallogic.eastpaymentservice.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.globallogic.eastpaymentservice.repository.AccountRepository;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernateAccountRepository implements AccountRepository{
}
