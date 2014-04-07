//package ua.globallogic.eastpaymentservice.repository.support;
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.Country;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CountryRowMapper implements RowMapper<Country> {
//
//    @Override
//    public Country mapRow(ResultSet rs, int i) throws SQLException {
//        Country country = new Country();
//        country.setCode(rs.getString("code"));
//        country.setName(rs.getString("name"));
//        return country;
//    }
//}
