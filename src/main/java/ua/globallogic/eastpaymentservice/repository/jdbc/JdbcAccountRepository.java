//package ua.globallogic.eastpaymentservice.repository.jdbc;
//
//import org.intellij.lang.annotations.Language;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import ua.globallogic.eastpaymentservice.domain.Account;
//import ua.globallogic.eastpaymentservice.repository.AccountRepository;
//import ua.globallogic.eastpaymentservice.repository.support.AccountRowMapper;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Repository
//public class JdbcAccountRepository implements AccountRepository {
//
//    @Language("SQL")
//    private final String ADD_ACCOUNT_WITHOUT_PROPOSAL = "INSERT INTO bank_account (iban) VALUES (?)";
//    @Language("SQL")
//    private final String ADD_ACCOUNT_WITH_PROPOSAL = "INSERT INTO bank_account (bank_proposal_id, iban) VALUES (?, ?)";
//    @Language("SQL")
//    private final String GET_ACCOUNT_BY_ID = "SELECT * FROM bank_account WHERE id = ?";
//    @Language("SQL")
//    private final String GET_ACCOUNT_BY_IBAN = "SELECT * FROM bank_account WHERE iban = ?";
//    @Language("SQL")
//    private final String GET_ALL_ACCOUNTS = "SELECT * FROM bank_account";
//    @Language("SQL")
//    private final String DEACTIVATE_ACCOUNT_BY_ID = "UPDATE bank_account SET active = FALSE WHERE id = ?";
//    @Language("SQL")
//    private final String ADD_BANK_ACCOUNT_OF_BANK = "INSERT INTO bank_account_of_bank (bank_id, bank_account_id) VALUES (?, ?)";
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcAccountRepository (DataSource dataSource){
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public Account getAccountById(Integer id){
//        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ID, new Object[]{id}, new AccountRowMapper());
//    }
//
//    @Override
//    public List<Account> getAccountByIban(String iban) {
//        return jdbcTemplate.query(GET_ACCOUNT_BY_IBAN, new Object[]{iban}, new AccountRowMapper());
//    }
//
//    @Override
//    public List<Account> getAllAccounts (){
//        return jdbcTemplate.query(GET_ALL_ACCOUNTS, new AccountRowMapper());
//    }
//
//    @Override
//    public void addAccount(String iban){
//        jdbcTemplate.update(ADD_ACCOUNT_WITHOUT_PROPOSAL, iban);
//    }
//
//    @Override
//    public void addAccount(String iban, Integer bankProposalId){
//        jdbcTemplate.update(ADD_ACCOUNT_WITH_PROPOSAL, bankProposalId, iban);
//    }
//
//    @Override
//    public void deactivateAccount(Integer id){
//        jdbcTemplate.update(DEACTIVATE_ACCOUNT_BY_ID, id);
//    }
//
//    @Override
//    public void addBankAccountOfBank(Integer bankId, Integer accountId){
//        jdbcTemplate.update(ADD_BANK_ACCOUNT_OF_BANK, bankId, accountId);
//    }
//}
