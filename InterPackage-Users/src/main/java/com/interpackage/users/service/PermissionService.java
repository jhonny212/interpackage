package com.interpackage.users.service;

import com.interpackage.users.model.Permission;
import com.interpackage.users.model.Role;
import com.interpackage.users.repository.PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepo;

    public PermissionService(PermissionRepository permissionRepo) {
        this.permissionRepo = permissionRepo;
    }


    public Permission create(Permission permission) throws Exception{
        if(findByName(permission.getName())!=null){
            throw new Exception("Permission name already registered");
        }
        return  permissionRepo.save(permission);
    }

    public Page<Permission> getAll(int page, int size, boolean pagination){
        return  permissionRepo.findAll(pagination? PageRequest.of(page, size) : Pageable.unpaged());
    }


    public Permission findByName(String name){
        return permissionRepo.findByName(name).orElse(null);
    }

    public Permission findById(Long id){
        return  permissionRepo.findById(id).orElse(null);
    }

    public Permission update(Permission entity, Long id) throws Exception{
        var entityDb = findById(id);
        if(entityDb!=null){
            entityDb.merge(entity);
            return permissionRepo.save(entityDb);
        }
        throw new Exception("Permission not found");
    }

    public void delete(Long id) throws Exception{
        var entityDb = findById(id);
        if(entityDb!=null){
            permissionRepo.delete(entityDb);
        }else{
            throw new Exception("Permission not found");
        }
    }

}
