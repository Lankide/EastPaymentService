package ua.globallogic.eastpaymentservice.repository;

import ua.globallogic.eastpaymentservice.domain.Country;

import java.util.Collection;

public interface CountryRepository {

    public Object getCountryByCode(String code);

    public Country getCountryByCode2(String code);

    public Country getCountryByName(String name);

    public Collection<Country> getCountries();
}
