package ua.globallogic.eastpaymentservice.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.globallogic.eastpaymentservice.domain.Bank;
import ua.globallogic.eastpaymentservice.domain.Country;
import ua.globallogic.eastpaymentservice.domain.Currency;
import ua.globallogic.eastpaymentservice.repository.AccountRepository;
import ua.globallogic.eastpaymentservice.repository.BankRepository;
import ua.globallogic.eastpaymentservice.repository.CountryRepository;
import ua.globallogic.eastpaymentservice.repository.ProposalRepository;

import java.util.Collection;

@Service
public class BankService {

    @Autowired
    @Qualifier("hibernateBankRepository")
    BankRepository bankRepository;

    @Autowired
    @Qualifier("hibernateProposalRepository")
    ProposalRepository proposalRepository;

    @Autowired
    @Qualifier("hibernateAccountRepository")
    AccountRepository accountRepository;

    @Autowired
    @Qualifier("hibernateCountryRepository")
    CountryRepository countryRepository;

//    public Bank getAllBankInfo(Integer bankId){
//        return bankRepository.getAllBankInfo(bankId);
//    }
//
//    public void save(Bank bank) {
//        bankRepository.save(bank);
//        Bank bankTmp = bankRepository.getOnlyBank(bank);
//        Proposal proposal = new Proposal();
//        proposal.setBankId(bankTmp.getId());
//        proposal.setCurrency(bank.getCurrency());
//        proposal.setCommission(0.0);
//        proposalRepository.addProposal(proposal);
//    }
//
//    public void deactivateBank(Bank bank){
//        bankRepository.deactivateBank(bank);
//    }

    Logger log = Logger.getLogger(BankService.class);
    public Collection<Bank> getBanks() {
        return bankRepository.getBanks();
    }

    public void save(Bank bank) {
        bankRepository.saveBank(bank);
    }

//    public List<Proposal> getBankProposals(Integer bankId){
//        return proposalRepository.getProposalsByBankId(bankId);
//    }

    public Collection<Currency> getCurrencies() {
        return bankRepository.getCurrencies();
    }

    public Collection<Country> getCountries() {
        return countryRepository.getCountries();
    }

    public Bank getBank(int id) {
        return bankRepository.getBank(id);
    }

    public Country getCountryByName(String name) {
        return countryRepository.getCountryByName(name);
    }

}
