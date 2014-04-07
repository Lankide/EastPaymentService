//package ua.globallogic.eastpaymentservice.repository.support;
//
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.Currency;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CurrencyRowMapper implements RowMapper<Currency>{
//    @Override
//    public Currency mapRow(ResultSet rs, int i) throws SQLException {
//        Currency currency = new Currency();
//        currency.setCurrencyCode(rs.getInt("code"));
//        currency.setCurrencyName(rs.getString("name"));
//        return currency;
//    }
//}
