package ua.globallogic.eastpaymentservice.repository;

import ua.globallogic.eastpaymentservice.domain.Bank;
import ua.globallogic.eastpaymentservice.domain.Currency;

import java.util.Collection;

public interface BankRepository {

    public Bank getBank(int id);
    public Collection<Bank> getBanks();
    public void saveBank(Bank bank);
    public void deactivateBank(Bank bank);
    public void updateBank(Bank bank);
    public Collection<Currency> getCurrencies();
    public String getCountryCode2(Integer bankId);
}
