//package ua.globallogic.eastpaymentservice.repository.support;
//
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.Proposal;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ProposalRowMapper implements RowMapper<Proposal> {
//
//    @Override
//    public Proposal mapRow(ResultSet rs, int i) throws SQLException {
//        Proposal proposal = new Proposal();
//        proposal.setId(rs.getInt("id"));
//        proposal.setBankId(rs.getInt("bank_id"));
//        proposal.setCommission(rs.getDouble("commission"));
//        proposal.setCurrencyCode(rs.getInt("currency_code"));
//        proposal.setCurrencyName(rs.getString("name"));
////        return proposal;
//    }
//}
