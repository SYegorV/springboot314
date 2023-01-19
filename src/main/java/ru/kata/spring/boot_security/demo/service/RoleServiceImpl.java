package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role roleByID(Long id) {
        Optional<Role> roleById = roleRepository.findById(id);
        if (roleById.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Role with id %s not found", id));
        } else {
            return roleById.get();
        }
    }
    @Override
    public void addRole(Role role) {
        roleRepository.saveAndFlush(role);
    }
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
