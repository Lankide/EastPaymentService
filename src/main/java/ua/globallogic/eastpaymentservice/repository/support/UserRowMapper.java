//package ua.globallogic.eastpaymentservice.repository.support;
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.globallogic.eastpaymentservice.domain.User;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserRowMapper implements RowMapper<User> {
//
//    @Override
//    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//        User user = new User();
//        user.setEmail(rs.getString("email"));
//        user.setPassword(rs.getString("password"));
//        user.setName(rs.getString("name"));
//        user.setSurname(rs.getString("surname"));
//        //user.setRole(rs.getString("role"));
//        //user.setCountryCode(rs.getString("country_code"));
//        user.setCity(rs.getString("city"));
//        user.setZipCode(rs.getString("zip_code"));
//        user.setAddress(rs.getString("address"));
//        user.setPhoneNumber(rs.getString("phone_number"));
//        user.setZipCode(rs.getString("zip_code"));
//        user.setActive(rs.getBoolean("active"));
//        return user;
//    }
//}
