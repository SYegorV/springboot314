package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;
public interface UserService {
    User getById(long id);
    void addUser(User newUser);
    void deleteUser(Long id);
    void updateUser(long id, User userForUpdate);
    List<User> getUsersList();
}
