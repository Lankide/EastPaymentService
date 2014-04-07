package ua.globallogic.eastpaymentservice.repository;

import ua.globallogic.eastpaymentservice.domain.User;
import ua.globallogic.eastpaymentservice.domain.UserSearchCriteria;

import java.util.Collection;

public interface UserRepository {
    public User getUser(int id);

    public void deleteUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

    public void addUser(User user);

    public User getUser(String email);

    public Collection<User> getUsers();

    User getUser(Integer id);

    Collection<User> findUsers(UserSearchCriteria criteria);

    User findUser(String name);
}
