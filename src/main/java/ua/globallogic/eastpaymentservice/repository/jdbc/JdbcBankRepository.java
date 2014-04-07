//package ua.globallogic.eastpaymentservice.repository.jdbc;
//
//import org.intellij.lang.annotations.Language;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import ua.globallogic.eastpaymentservice.domain.*;
//import ua.globallogic.eastpaymentservice.repository.BankRepository;
//import ua.globallogic.eastpaymentservice.repository.support.*;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Repository
//public class JdbcBankRepository implements BankRepository {
//
//    @Language("SQL")
//    private final String GET_BANK_BY_COUNTRY_CODE =
//            "SELECT bank.*, country.name AS country_name " +
//            "FROM bank, country " +
//            "WHERE (bank.name = ? AND bank.country_code = ?) " +
//            "AND country.code = ?";
//    @Language("SQL")
//    private final String GET_BANK_BY_ID =
//            "SELECT bank.*, country.name AS country_name " +
//                    "FROM bank, country " +
//                    "WHERE (bank.id = ? AND bank.country_code = country.code)";
//    @Language("SQL")
//    private final String GET_COUNTRY_CODE2_BY_BANK_ID =
//            "SELECT country.code2 " +
//                    "FROM country, bank " +
//                    "WHERE bank.id = ? AND bank.country_code = country.code";
//    @Language("SQL")
//    private final String GET_ALL_BANKS =
//            "SELECT bank.*, country.name AS country_name " +
//            "FROM bank " +
//            "INNER JOIN country " +
//            "ON bank.country_code = country.code";
//    @Language("SQL")
//    private final String SAVE_BANK = "INSERT INTO bank (name, country_code) VALUES (?, ?)";
//    @Language("SQL")
//    private final String DEACTIVATE_BANK = "UPDATE bank SET active = FALSE WHERE name = ? AND country_code = ?";
//    @Language("SQL")
//    private final String GET_COUNTRY_BY_NAME = "SELECT * FROM country WHERE name = ?";
//    @Language("SQL")
//    private final String GET_BANK_ACCOUNTS =
//            "SELECT * " +  //bank_account.id, bank_account.iban, bank_account.bank_proposal_id, bank_account.active " +
//            "FROM bank_account_of_bank, bank_account " +
//            "WHERE bank_account_of_bank.bank_id = ? AND bank_account.id = bank_account_of_bank.bank_account_id " ;
////            "INNER JOIN  " +
////            "ON bank_account.id = bank_account_id";
//    @Language("SQL")
//    private final String GET_BANK_PROPOSALS =
//            "SELECT bank_proposal.*, currency.name " +
//                    "FROM bank_proposal, currency " +
//                    "WHERE bank_proposal.bank_id = ? AND bank_proposal.currency_code = currency.code" ;
////                    "INNER JOIN  " +
////                    "ON currency_code = code";
//    @Language("SQL")
//    private final String GET_ALL_CURRENCIES = "SELECT * FROM currency";
//
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcBankRepository(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public Bank getOnlyBank(Bank bank) {
//        return jdbcTemplate.queryForObject(GET_BANK_BY_COUNTRY_CODE, new Object[]{bank.getName(), bank.getCountryCode(), bank.getCountryCode()}, new BankRowMapper());
//    }
//
//    @Override
//    public Bank getAllBankInfo(Integer bankId) {
//        Bank bankForReturn = jdbcTemplate.queryForObject(GET_BANK_BY_ID, new Object[]{bankId}, new BankRowMapper());
//        bankForReturn.setAccounts(jdbcTemplate.query(GET_BANK_ACCOUNTS, new Object[]{bankId}, new BankAccountRowMapper()));
//        bankForReturn.setProposals(jdbcTemplate.query(GET_BANK_PROPOSALS, new Object[]{bankId}, new ProposalRowMapper()));
//        return bankForReturn;
//    }
//
//    @Override
//    public List<Bank> getBanks() {
//        return jdbcTemplate.query(GET_ALL_BANKS, new BankRowMapper());
//    }
//
//    @Override
//    public void saveBank(Bank bank) {
//        jdbcTemplate.update(SAVE_BANK, bank.getParams());
//    }
//
//    @Override
//    public void deactivateBank(Bank bank) {
//        bank.setActive(false);
//        Country country = jdbcTemplate.queryForObject(GET_COUNTRY_BY_NAME, new Object[]{bank.getCountryName()}, new CountryRowMapper());
//        bank.setCountryCode(country.getCode());
//        jdbcTemplate.update(DEACTIVATE_BANK, bank.getParams());
//    }
//
//    @Override
//    public void updateBank(Bank bank) {
//        //todo
//    }
//
//    @Override
//    public List<Currency> getCurrencies(){
//        return jdbcTemplate.query(GET_ALL_CURRENCIES, new CurrencyRowMapper());
//    }
//
//    @Override
//    public String getCountryCode2(Integer bankId){
//        return jdbcTemplate.queryForObject(GET_COUNTRY_CODE2_BY_BANK_ID, String.class, bankId);
//    }
//}
