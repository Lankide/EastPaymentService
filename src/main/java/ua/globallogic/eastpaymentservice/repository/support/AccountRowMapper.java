//package ua.globallogic.eastpaymentservice.repository.support;
//
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.Account;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class AccountRowMapper implements RowMapper<Account>{
//    @Override
//    public Account mapRow(ResultSet rs, int i) throws SQLException {
//        Account account = new Account();
//        account.setId(rs.getInt("id"));
//        account.setBankProposalId(rs.getInt("bank_proposal_id"));
//        account.setIban(rs.getString("iban"));
//        account.setActive(rs.getBoolean("active"));
//        return account;
//    }
//}
