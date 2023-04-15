package com.interpackage.users.service;

import com.interpackage.users.model.Role;
import com.interpackage.users.model.RolePermission;
import com.interpackage.users.repository.PermissionRepository;
import com.interpackage.users.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepo;
    private final PermissionService permissionService;


    public RoleService(RoleRepository roleRepo, PermissionService permissionService) {
        this.roleRepo = roleRepo;
        this.permissionService = permissionService;
    }

    public Role createRole(Role role) throws Exception{
      if(findRoleByName(role.getName())!=null){
          throw new Exception("Role name already exists");
      }
        for (RolePermission rolePermission: role.getRolePermissions()
             ) {
            rolePermission.setRole(role);
            rolePermission.setPermission(permissionService.findById(rolePermission.getPermission().getIdPermission()));
        }
        return  roleRepo.save(role);
    }

    public Page<Role> getAllRoles(int page, int size, boolean pagination){
        return  roleRepo.findAll(pagination? PageRequest.of(page, size) : Pageable.unpaged());
    }


    public Role findRoleByName(String name){
        return roleRepo.findByName(name).orElse(null);
    }

    public Role findById(Long id){
        return  roleRepo.findById(id).orElse(null);
    }

    public Role updateRole(Role role, Long id) throws Exception{
        var roleDb = findById(id);
        if(roleDb!=null){
            for (RolePermission rolePermission: role.getRolePermissions()
            ) {
                rolePermission.setRole(role);
                rolePermission.setPermission(permissionService.findById(rolePermission.getPermission().getIdPermission()));
            }
            roleDb.merge(role);

            return roleRepo.save(roleDb);
        }
        throw new Exception("Role not found");
    }

    public void deleteRole(Long id) throws Exception{
        var roleDb = findById(id);
        if(roleDb!=null){
            roleRepo.delete(roleDb);
        }else{
            throw new Exception("Role not found");
        }
    }




}
