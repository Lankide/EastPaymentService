package ua.globallogic.eastpaymentservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.service.UserService;
import ua.globallogic.eastpaymentservice.web.utils.PathUtils;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Scope("session")
public class LoginController {

    @Autowired
    @Qualifier("mvcUserService")
    UserService userService;

    private User user;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected String home(Principal principal) {
        if (user == null && principal != null) {
            user = userService.getUser(principal.getName());
        }
        if (user != null) {
            return "redirect:/" + PathUtils.getPath(user);
        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(Principal principal) {
        if (user == null && principal != null) {
            user = userService.getUser(principal.getName());
            return "redirect:/" + PathUtils.getPath(user);
        } else if (user != null && principal != null) {
            return "redirect:/" + PathUtils.getPath(user);
        } else {
            return "login";
        }

    }

    @RequestMapping("/internal")
    @PreAuthorize("hasRole('ROLE_INTERNAL_USER')")
    public String internal(Principal principal, HttpSession session) {
        putUserToSession(principal, session);
        return "user/internal/home";
    }

    @RequestMapping("/external")
    @PreAuthorize("hasRole('ROLE_EXTERNAL_USER')")
    public String external(Principal principal, HttpSession session) {
        putUserToSession(principal, session);
        return "user/external/home";
    }

    private void putUserToSession(Principal principal, HttpSession session) {
        String email = principal.getName();
        User authorizedUser = userService.getUser(email);
        session.setAttribute("authorizedUser", authorizedUser);
    }
}
