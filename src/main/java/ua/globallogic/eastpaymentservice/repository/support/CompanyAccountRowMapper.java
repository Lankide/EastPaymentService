//package ua.globallogic.eastpaymentservice.repository.support;
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.CompanyAccount;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CompanyAccountRowMapper implements RowMapper<CompanyAccount> {
//    @Override
//    public CompanyAccount mapRow(ResultSet rs, int i) throws SQLException {
//        CompanyAccount companyAccount = new CompanyAccount();
//        companyAccount.setId(rs.getInt("id"));
//        companyAccount.setIban(rs.getString("iban"));
//        companyAccount.setCompanyId(rs.getInt("company_id"));
//        companyAccount.setBankProposalId(rs.getInt("bank_proposal_id"));
//        companyAccount.setActive(rs.getBoolean("active"));
//        return companyAccount;
//    }
//}
