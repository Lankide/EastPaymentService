package ua.globallogic.eastpaymentservice.repository;


import ua.globallogic.eastpaymentservice.domain.Company;

import java.util.Collection;

public interface CompanyRepository {
    public Collection<Company> getAllCompanies();
}
