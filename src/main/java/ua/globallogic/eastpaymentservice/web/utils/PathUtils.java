package ua.globallogic.eastpaymentservice.web.utils;

import ua.globallogic.eastpaymentservice.domain.User;

public class PathUtils {

    public static String getPath(User user) {
        if (user != null) {
            String role = user.getRole();
            return role.substring(role.indexOf("_") + 1, role.lastIndexOf("_")).toLowerCase();
        }
        return null;
    }
}


//        Pattern pattern = Pattern.compile("^ROLE_(.*)_USER$");
//        Matcher matcher = pattern.matcher(role);
//        if(matcher.find()) {
//            System.out.println(matcher.group(1));
//        }

