package ua.globallogic.eastpaymentservice.web.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.globallogic.eastpaymentservice.domain.Country;
import ua.globallogic.eastpaymentservice.domain.Role;
import ua.globallogic.eastpaymentservice.domain.UserSearchCriteria;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.service.UserService;
import ua.globallogic.eastpaymentservice.web.form.UserForm;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("mvcUserService")
    UserService userService;

    @RequestMapping(value = "/status")
    public void read(String status, Integer id) {
        // todo: add some code here
    }


    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission(null, 'create_user')")
    public ModelAndView userForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles", getRoleList());
        mav.addObject("permissions", userService.getPermissions());
        mav.addObject("countries", userService.getCountries());
        mav.addObject("userForm", new UserForm());
        mav.setViewName("user/internal/createForm");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    @PreAuthorize("hasPermission(null, 'create_user')")
    public
    @ResponseBody
    ModelMap userSubmit(@ModelAttribute UserForm form) {
        ModelMap modelMap = new ModelMap();

        form.setUserService(userService);
        userService.addUser(form.toUser());

        modelMap.addAttribute("message", "User has been created successfully");
        return modelMap;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission(#user, 'edit_user')")
    public ModelAndView userEditForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("user/internal/editForm");
        mav.addObject("user", userService.getUser(id));
        mav.addObject("roles", getRoleList());
        mav.addObject("permissions", userService.getPermissions());
        mav.addObject("countries", userService.getCountries());
        return mav;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasPermission(#user, 'edit_user')")
    public ModelAndView userEditFormSubmit(/*@Valid*/ User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("user/internal/editForm", "globalMessage", "User cannot be updated").addObject("alertCode", "FAIL");
        }
        //userService.updateUser(user); // todo: fix js
        return new ModelAndView("user/internal/editForm").
                addObject("roles", getRoleList()).
                addObject("permissions", userService.getPermissions()).
                addObject("countries", userService.getCountries()).
                addObject("globalMessage", "User <strong>" + user.getName() + " " + user.getSurname() + "</strong> updated successfully").
                addObject("alertCode", "OK");
    }


    @RequestMapping(value = "/advancedSearch", method = RequestMethod.GET)
    @PreAuthorize("hasPermission(#user, 'search_user')")
    public ModelAndView searchForm() {
        ModelAndView mav = new ModelAndView("user/internal/searchForm");
        Collection<Country> countries = userService.getCountries();
        ((List) countries).add(0, new Country("", "", "")); // todo: fix this evil hack
        return mav.addObject("countries", countries).addObject("criteria", new UserSearchCriteria());
    }


    @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#user, 'search_user')")
    public ModelAndView searchSubmit(UserSearchCriteria criteria, BindingResult result) {
        ModelAndView mav = new ModelAndView("user/internal/home");
        if (result.hasErrors()) {
            return mav.addObject("globalMessage", "Wrong criteria").addObject("alertCode", "FAIL");
        }
        Collection<User> users = userService.findUsers(criteria);
        if (users != null) {
            mav.addObject("users", users).addObject("globalMessage", "<strong>" + users.size() + "</strong> " + ((users.size() > 1) ? "users" : "user") + " found").addObject("alertCode", "OK");
        } else {
            mav.addObject("globalMessage", "User not found").addObject("alertCode", "FAIL");
        }
        return mav;
    }

    @RequestMapping("/search")
    @PreAuthorize("hasPermission(#user, 'search_user')")
    public ModelAndView searchSubmit(@RequestParam(value = "name", required = false) String name) {
        Collection<User> users = userService.findUsers(new UserSearchCriteria(name));
        ModelAndView mav = new ModelAndView("user/internal/home").addObject("users", users);
        if (users != null) {
            mav.addObject("globalMessage", "<strong>" + users.size() + "</strong> " + ((users.size() > 1) ? "users" : "user") + " found").addObject("alertCode", "OK");
        } else {
            mav.addObject("globalMessage", "User not found").addObject("alertCode", "FAIL");
        }
        return mav;
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

    private List<Role> getRoleList() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.ROLE_INTERNAL_USER);
        roles.add(Role.ROLE_EXTERNAL_USER);
        return roles;
    }
}