package ua.globallogic.eastpaymentservice.web.form;

import ua.globallogic.eastpaymentservice.domain.Country;
import ua.globallogic.eastpaymentservice.domain.Permission;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.service.UserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class UserForm implements Serializable {

    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;
    private Country country;
    private String city;
    private String zipCode;
    private String address;
    private String phoneNumber;
    private String[] permissions;
    private UserService userService;

    public UserForm() {
    }

    public User toUser() {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setCountry(country);
        newUser.setCity(city);
        newUser.setZipCode(zipCode);
        newUser.setAddress(address);
        newUser.setPhoneNumber(phoneNumber);
        Collection<Permission> ps = userService.getPermissions();
        Collection<Permission> cp = new ArrayList<>();
        for (String permission : permissions) {
            for (Permission p : ps) {
                if (p.getName().equals(permission)){
                    cp.add(p);
                    break;
                }
            }
        }
        newUser.setPermissions(cp);
        return newUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", country=" + country +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", permissions=" + Arrays.toString(permissions) +
                ", userService=" + userService +
                '}';
    }
}
