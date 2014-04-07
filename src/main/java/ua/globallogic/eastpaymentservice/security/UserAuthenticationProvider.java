package ua.globallogic.eastpaymentservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.security.utils.MD5Utils;
import ua.globallogic.eastpaymentservice.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("securityUserService")
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pass = authentication.getCredentials().toString();

        if (email.equals("") || pass.equals("")){
            throw new BadCredentialsException("Oops!");
        }

        User user = userService.getUser(email);
        String hash = MD5Utils.encode(pass);

        if (!hash.equals(user.getPassword()))
            throw new BadCredentialsException("Wrong password");
        if (!email.equals(user.getEmail()))
            throw new BadCredentialsException("Email password");

        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority(user.getRole()));
        return new UsernamePasswordAuthenticationToken(email, hash, auths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
