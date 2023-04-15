package com.interpackage.users.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.interpackage.users.pk.PKRolePermission;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "role_permission")
@IdClass (PKRolePermission.class)
public class RolePermission {

    @Id
    @ManyToOne
    @JoinColumn (name = "id_role", nullable = false)
    @JsonBackReference
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn (name = "id_permission", nullable = false)
    private Permission permission;

    @Column (nullable = false)
    private Boolean reading;

    @Column (nullable = false)
    private Boolean writing;

    @Column (nullable = false)
    private Boolean edition;

    @Column (nullable = false)
    private Boolean elimination;

    @Column (nullable = false)
    private Boolean export;


    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Boolean isReading() {
        return this.reading;
    }

    public Boolean getReading() {
        return this.reading;
    }

    public void setReading(Boolean reading) {
        this.reading = reading;
    }

    public Boolean isWriting() {
        return this.writing;
    }

    public Boolean getWriting() {
        return this.writing;
    }

    public void setWriting(Boolean writing) {
        this.writing = writing;
    }

    public Boolean isEdition() {
        return this.edition;
    }

    public Boolean getEdition() {
        return this.edition;
    }

    public void setEdition(Boolean edition) {
        this.edition = edition;
    }

    public Boolean isElimination() {
        return this.elimination;
    }

    public Boolean getElimination() {
        return this.elimination;
    }

    public void setElimination(Boolean elimination) {
        this.elimination = elimination;
    }

    public Boolean isExport() {
        return this.export;
    }

    public Boolean getExport() {
        return this.export;
    }

    public void setExport(Boolean export) {
        this.export = export;
    }

}
