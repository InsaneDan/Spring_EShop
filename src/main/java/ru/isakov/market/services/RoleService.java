package ru.isakov.market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isakov.market.models.entities.Role;
import ru.isakov.market.models.repositories.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findRoleByName(String role){
        return roleRepository.findRoleByName(role);
    }
}