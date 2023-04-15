package com.interpackage.users.model;

import java.util.*;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false)
    private Long idRole;

    @Column(nullable = false, length = 75)
    private String name;

    @Column(nullable = true, length = 75)
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<RolePermission> rolePermissions = new ArrayList<>();

    public void merge(Role role) {
        description = role.description;
        rolePermissions.clear();
        rolePermissions.addAll(role.getRolePermissions());
    }


    public Long getIdRole() {
        return this.idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions.addAll(rolePermissions);
    }
}
