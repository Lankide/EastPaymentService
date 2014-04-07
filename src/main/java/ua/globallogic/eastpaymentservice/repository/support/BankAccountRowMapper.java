//package ua.globallogic.eastpaymentservice.repository.support;
//
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.BankAccount;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BankAccountRowMapper implements RowMapper<BankAccount>{
//    @Override
//    public BankAccount mapRow(ResultSet rs, int i) throws SQLException {
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.setId(rs.getInt("id"));
//        bankAccount.setIban(rs.getString("iban"));
//        bankAccount.setBankId(rs.getInt("bank_id"));
//        bankAccount.setBankProposalId(rs.getInt("bank_proposal_id"));
//        bankAccount.setActive(rs.getBoolean("active"));
//        return bankAccount;
//    }
//}
