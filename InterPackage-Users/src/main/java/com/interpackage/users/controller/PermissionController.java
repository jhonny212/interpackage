package com.interpackage.users.controller;

import com.interpackage.users.model.Permission;
import com.interpackage.users.model.Role;
import com.interpackage.users.repository.PermissionRepository;
import com.interpackage.users.service.PermissionService;
import com.interpackage.users.util.CommonParams;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/users/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        var entity = permissionService.findById(id);
        if(entity!=null){
            return ResponseEntity.ok(entity);
        }
        return new ResponseEntity<>("Permission not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<Page<Permission>> getAll(CommonParams commonParams) {
        return ResponseEntity.ok(permissionService.getAll(commonParams.getPage(),commonParams.getMax(), commonParams.isPagination()));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody Permission entity){
        Permission newEntity = null;
        try {
            newEntity = permissionService.create(entity);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody Permission entity, @PathVariable Long id){
        Permission entityUpdated = null;
        try {
            entityUpdated = permissionService.update(entity,id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(entityUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            permissionService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().build();
    }

}
