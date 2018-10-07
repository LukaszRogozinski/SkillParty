package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Role;
import com.engineer.lrogozinski.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Role> getAllRoles(){
        return roleService.findAll();
    }
}
