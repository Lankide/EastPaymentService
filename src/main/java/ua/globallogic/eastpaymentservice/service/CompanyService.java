package ua.globallogic.eastpaymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.globallogic.eastpaymentservice.domain.Company;
import ua.globallogic.eastpaymentservice.repository.CompanyRepository;


import java.util.Collection;

@Service
public class CompanyService {

    @Autowired
    @Qualifier ("companyRepositoryImpl")
    CompanyRepository companyRepository;

    public Collection<Company> getAllCompanies(){
        return companyRepository.getAllCompanies();
    }

}
