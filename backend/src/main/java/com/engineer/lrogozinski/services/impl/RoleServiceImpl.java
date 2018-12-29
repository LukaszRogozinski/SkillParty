package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.Role;
import com.engineer.lrogozinski.exceptions.ServiceException;
import com.engineer.lrogozinski.repositories.RoleRepository;
import com.engineer.lrogozinski.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.exceptions.ExceptionsMessage.CANNOT_FIND_ROLE_WITH_PROVIDED_ID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new ServiceException(CANNOT_FIND_ROLE_WITH_PROVIDED_ID));
    }

    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void delete(Role object) {
        roleRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
