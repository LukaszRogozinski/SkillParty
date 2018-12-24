package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Role;
import com.engineer.lrogozinski.exceptions.ServiceException;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Integer id) throws ServiceException;

    Role save(Role object);

    void delete(Role object);

    void deleteById(Integer id);

    Role findByRole(String role);
}
