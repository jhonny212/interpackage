package com.interpackage.users.controller;

import com.interpackage.users.model.Role;
import com.interpackage.users.service.RoleService;
import com.interpackage.users.util.CommonParams;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/users/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        var role = roleService.findById(id);
        if(role!=null){
            return ResponseEntity.ok(role);
        }
        return new ResponseEntity<>("Role not found",HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<Page<Role>> getRoles(CommonParams commonParams) {
        return ResponseEntity.ok(roleService.getAllRoles(commonParams.getPage(),commonParams.getMax(), commonParams.isPagination()));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Role entity){
        Role newEntity = null;
        try {
            newEntity = roleService.createRole(entity);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody Role usr, @PathVariable Long id){
        Role entity = null;
        try {
            entity = roleService.updateRole(usr,id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            roleService.deleteRole(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().build();
    }


}
