package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Integer id);

    Role save(Role object);

    void delete(Role object);

    void deleteById(Integer id);

    Role findByRole(String role);
}
