package ua.globallogic.eastpaymentservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.globallogic.eastpaymentservice.domain.Permission;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.service.UserService;

import java.io.Serializable;
import java.util.Collection;

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    @Qualifier("securityUserService")
    UserService service;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object allowedPermission) {

        String email = authentication.getPrincipal().toString();
        User authorizedUser = service.getUser(email);

        if (authorizedUser == null) {
            return false;
        }

        Collection<Permission> userPermissions = authorizedUser.getPermissions();

        for (Permission p : userPermissions) {
            if (p.getName().equals(allowedPermission)) return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }
}