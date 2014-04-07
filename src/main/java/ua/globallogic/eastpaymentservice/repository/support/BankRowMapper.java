//package ua.globallogic.eastpaymentservice.repository.support;
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.Bank;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BankRowMapper implements RowMapper<Bank> {
//
//    @Override
//    public Bank mapRow(ResultSet rs, int i) throws SQLException {
//        Bank bank = new Bank();
//        bank.setId(rs.getInt("id"));
//        bank.setName(rs.getString("name"));
//        bank.setCountryName(rs.getString("country_name"));
//        bank.setCountryCode(rs.getString("country_code"));
//        bank.setActive(rs.getBoolean("active"));
//        return bank;
//    }
//}
