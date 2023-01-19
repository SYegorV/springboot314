package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User getById(long id) {
        //return null;
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow();
    }
    @Override
    public void addUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole("ROLE_USER");
        userRepository.saveAndFlush(newUser); // .save(newUser);
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public void updateUser(long id, User userForUpdate) {
        userRepository.saveAndFlush(userForUpdate); // .save(userForUpdateData);
    }
    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }
}

