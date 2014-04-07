package ua.globallogic.eastpaymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.globallogic.eastpaymentservice.domain.Country;
import ua.globallogic.eastpaymentservice.domain.Permission;
import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.domain.UserSearchCriteria;
import ua.globallogic.eastpaymentservice.repository.CountryRepository;
import ua.globallogic.eastpaymentservice.repository.PermissionRepository;
import ua.globallogic.eastpaymentservice.repository.UserRepository;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    @Qualifier("hibernateUserRepository")
    UserRepository userRepository;

    @Autowired
    @Qualifier("hibernatePermissionRepository")
    PermissionRepository permissionRepository;

    @Autowired
    @Qualifier("hibernateCountryRepository")
    CountryRepository countryRepository;

    public User getUser(String email) {
        return userRepository.getUser(email);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public Collection<Permission> getPermissions() {
        return permissionRepository.getPermissions();
    }

    public Permission getPermission(int id) {
        return permissionRepository.getPermission(id);
    }

    public Permission getPermission(String name) {
        return permissionRepository.getPermission(name);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public User getUser(Integer id) {
        return userRepository.getUser(id);
    }

    public Collection<User> findUsers(UserSearchCriteria criteria) {
        return userRepository.findUsers(criteria);
    }

    public User findUser (String name){
        return userRepository.findUser(name);
    }


    public Collection<Country> getCountries() {
        return countryRepository.getCountries();
    }

    public Country getCountryByCode(String code) {
        return (Country) countryRepository.getCountryByCode(code);
    }

    public Country getCountryByName (String name){
        return countryRepository.getCountryByName(name);
    }
}

