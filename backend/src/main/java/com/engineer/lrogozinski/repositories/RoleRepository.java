package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
