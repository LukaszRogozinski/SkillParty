package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Role;

public interface RoleService extends CrudService<Role,Integer> {
    Role findByRole(String role);
}
