package com.interpackage.users.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "permission")
public class Permission {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_permission",nullable = false)
    private Long idPermission;

    @Column (nullable = false, length = 75)
    private String name;

    @Column (nullable = false, length = 500)
    private String description;

    public void merge(Permission permission){
        description = permission.description;
    }


    public Long getIdPermission() {
        return this.idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
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

}
