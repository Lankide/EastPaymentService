//package ua.globallogic.eastpaymentservice.repository.support;
//
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import ua.globallogic.eastpaymentservice.domain.User;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class UserPreparedStatementCreator implements PreparedStatementCreator {
//
//    private String sql;
//    private User user;
//
//    public UserPreparedStatementCreator(String sql, User user) {
//        this.sql = sql;
//        this.user = user;
//    }
//
//    @Override
//    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//
//
//        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//        ps.setString(1, user.getEmail());
//        ps.setString(2, user.getPassword());
//        ps.setString(3, user.getName());
//        ps.setString(4, user.getSurname());
//        ps.setString(5, "ROLE_INTERNAL_USER");
//
//        ps.setString(6, user.getCountry().getCode());
//        ps.setString(7, user.getCity());
//        ps.setString(8, user.getZipCode());
//        ps.setString(9, user.getAddress());
//        ps.setString(10, user.getPhoneNumber());
//
//        return ps;
//    }
//}
