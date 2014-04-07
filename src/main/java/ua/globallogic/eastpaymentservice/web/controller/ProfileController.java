package ua.globallogic.eastpaymentservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.globallogic.eastpaymentservice.domain.Country;
import ua.globallogic.eastpaymentservice.domain.Permission;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.service.UserService;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.LinkedHashSet;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    @Qualifier("mvcUserService")
    UserService userService;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission(null, 'edit_user')")
    public ModelAndView form(Principal principal) {
        User user = userService.getUser(principal.getName());
        return new ModelAndView("user/profile").
                addObject("user", user).
                addObject("countries", userService.getCountries());
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission(null, 'edit_user')")
    public ModelAndView edit(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("user/profile").
                    addObject("countries", userService.getCountries()).
                    addObject("globalMessage", "User cannot be updated").
                    addObject("alertCode", "FAIL");
        } else {
            LinkedHashSet<Permission> ps = new LinkedHashSet<>();
            for (Permission permission : user.getPermissions()) {
                ps.add(userService.getPermission(permission.getName()));
            }
            user.setPermissions(ps);
            userService.updateUser(user);
            return new ModelAndView("user/profile").
                    addObject("countries", userService.getCountries()).
                    addObject("globalMessage", "User profile updated successfully").
                    addObject("alertCode", "OK");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Country.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String name) throws IllegalArgumentException {
                setValue(userService.getCountryByName(name));
            }
        });
    }
}